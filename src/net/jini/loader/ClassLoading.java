/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.jini.loader;

import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.rmi.server.RMIClassLoader;
import java.rmi.server.RMIClassLoaderSpi;
import java.security.AccessController;
import java.security.Guard;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.codespaces.CodeSpaces;
import net.codespaces.core.ClassAnnotation;
import net.codespaces.core.ClassResolver;
import net.jini.security.Security;

/**
 * Provides static methods for loading classes using {@link RMIClassLoaderSpi} with optional verification that the
 * codebase URIs used to load classes provide content integrity (see {@link Security#verifyCodebaseIntegrity
 * Security.verifyCodebaseIntegrity}).
 * <p>
 * Traditionally a class extending {@link RMIClassLoaderSpi} is determined by setting the system property
 * "java.rmi.server.RMIClassLoaderSpi", or alternatively, {@link RMIClassLoaderSpi} may also be defined by
 * {@link RMIClassLoader} using a provider visible to the {@link ClassLoader} returned by
 * {@link ClassLoader#getSystemClassLoader} with {@link ServiceLoader}.
 * </p>
 * <p>
 * As explained in River-336 this isn't always practical for IDE's or other frameworks. To solve River-336, ClassLoading
 * now uses {@link ServiceLoader} to determine a {@link RMIClassLoaderSpi} provider, however unlike
 * {@link RMIClassLoader}, by default it uses ClassLoading's {@link ClassLoader#getResources} instance to find
 * providers.
 * </p>
 * <p>
 * To define a new RMIClassLoaderSpi for River to utilize, create a file in your providers jar file called:
 * </p>
 * <p>
 * META-INF/services/java.rmi.server.RMIClassLoaderSpi
 * </p>
 * <p>
 * This file should contain a single line with the fully qualified name of your RMIClassLoaderSpi implementation.
 * </p>
 * <p>
 * ClassLoading will iterate through all RMIClassLoaderSpi implementations found until it finds one defined by the
 * system property:
 * </p>
 * <p>
 * System.getProperty("net.jini.loader.ClassLoading.provider");
 * </p>
 * <p>
 * If this System property is not defined, ClassLoading will load
 * <code>net.jini.loader.pref.PreferredClassProvider</code>, alternatively <code>java.rmi.server.RMIClassLoader</code>
 * delegates all calls to {@link RMIClassLoader}.
 * </p>
 * <p>
 * If a provider is not found, it will not be updated.
 * </p>
 * <p>
 * <h1>History</h1>
 * <p>
 * Gregg Wonderly originally reported River-336 and provided a patch containing a new CodebaseAccessClassLoader to
 * replace {@link RMIClassLoader}, later Sim Isjkes created RiverClassLoader that utilized ServiceLoader. Both
 * implementations contained methods identical to {@link RMIClassLoaderSpi}, however new implementations were required
 * to extend new provider implementations, creating a compatibility issue with existing implementations extending
 * {@link RMIClassLoaderSpi}. For backward compatibility with existing implementations, {@link RMIClassLoaderSpi} has
 * been retained as the provider, avoiding the need to recompile client code. The abilities of both implementations, to
 * use ServiceLoader, or to define a provider using a method call have been retained, with the restriction that
 * implementations are to be obtained via ServiceLoader.
 * </p>
 * <p>
 * Instead, all that is required for utilization of existing service provider {@link RMIClassLoaderSpi} implementations
 * is to set the system property "net.jini.loader.ClassLoading.provider".
 * </p>
 * 
 * @author Sun Microsystems, Inc.
 * @since 2.0
 **/
public final class ClassLoading
{

    public static ClassAnnotation getClassAnnotation(Class<?> cl)
    {
        return CodeSpaces.getClassAnnotation(cl);
    }

    /**
     * Loads a class using {@link RMIClassLoaderSpi#loadClass(String,String,ClassLoader)}, optionally verifying that the
     * RFC3986 compliant codebase URIs provide content integrity.
     * <p>
     * If <code>verifyCodebaseIntegrity</code> is <code>true</code> and <code>codebase</code> is not <code>null</code>,
     * then this method invokes {@link Security#verifyCodebaseIntegrity Security.verifyCodebaseIntegrity} with
     * <code>codebase</code> as the first argument and <code>verifierLoader</code> as the second argument (this
     * invocation may be skipped if a previous invocation of this method or {@link #loadProxyClass loadProxyClass} has
     * already invoked <code>Security.verifyCodebaseIntegrity</code> with the same value of <code>codebase</code> and
     * the same effective value of <code>verifierLoader</code> as arguments without it throwing an exception). If
     * <code>Security.verifyCodebaseIntegrity</code> throws a <code>SecurityException</code>, then this method proceeds
     * as if <code>codebase</code> were <code>null</code>. If <code>Security.verifyCodebaseIntegrity</code> throws any
     * other exception, then this method throws that exception.
     * <p>
     * This method then invokes {@link RMIClassLoaderSpi#loadClass(String,String,ClassLoader)
     * RMIClassLoaderSpi.loadClass} with <code>codebase</code> as the first argument (or <code>null</code> if in the
     * previous step <code>Security.verifyCodebaseIntegrity</code> was invoked and it threw a
     * <code>SecurityException</code>), <code>name</code> as the second argument, and <code>defaultLoader</code> as the
     * third argument. If <code>RMIClassLoaderSpi.loadClass</code> throws a <code>ClassNotFoundException</code>, then
     * this method throws a <code>ClassNotFoundException</code>; if <code>RMIClassLoaderSpi.loadClass</code> throws any
     * other exception, then this method throws that exception; otherwise, this method returns the <code>Class</code>
     * returned by <code>RMIClassLoaderSpi.loadClass</code>.
     *
     * @param codebase
     *                                the list of URLs (separated by spaces) to load the class from, or
     *                                <code>null</code>
     * @param name
     *                                the name of the class to load
     * @param defaultLoader
     *                                the class loader value (possibly <code>null</code>) to pass as the
     *                                <code>defaultLoader</code> argument to <code>RMIClassLoaderSpi.loadClass</code>
     * @param verifyCodebaseIntegrity
     *                                if <code>true</code>, verify that the RFC3986 compliant codebase URIs provide
     *                                content integrity
     * @param verifierLoader
     *                                the class loader value (possibly <code>null</code>) to pass to
     *                                <code>Security.verifyCodebaseIntegrity</code>, if
     *                                <code>verifyCodebaseIntegrity</code> is <code>true</code>
     * @return the <code>Class</code> object representing the loaded class
     * @throws MalformedURLException
     *                                if <code>Security.verifyCodebaseIntegrity</code> or
     *                                <code>RMIClassLoaderSpi.loadClass</code> throws a
     *                                <code>MalformedURLException</code>
     * @throws ClassNotFoundException
     *                                if <code>RMIClassLoaderSpi.loadClass</code> throws a
     *                                <code>ClassNotFoundException</code>
     * @throws NullPointerException
     *                                if <code>name</code> is <code>null</code>
     **/
    public static Class<?> loadClass(ClassAnnotation codebase,
                                     String name,
                                     ClassResolver classResolver)
            throws ClassNotFoundException
    {
        return classResolver.loadClass(codebase, name);
    }

    /**
     * Loads a dynamic proxy class using {@link RMIClassLoaderSpi#loadProxyClass(String,String[],ClassLoader)},
     * optionally verifying that the RFC3986 compliant codebase URIs provide content integrity.
     * <p>
     * If <code>verifyCodebaseIntegrity</code> is <code>true</code> and <code>codebase</code> is not <code>null</code>,
     * then this method invokes {@link Security#verifyCodebaseIntegrity Security.verifyCodebaseIntegrity} with
     * <code>codebase</code> as the first argument and <code>verifierLoader</code> as the second argument (this
     * invocation may be skipped if a previous invocation of this method or {@link #loadClass loadClass} has already
     * invoked <code>Security.verifyCodebaseIntegrity</code> with the same value of <code>codebase</code> and the same
     * effective value of <code>verifierLoader</code> as arguments without it throwing an exception). If
     * <code>Security.verifyCodebaseIntegrity</code> throws a <code>SecurityException</code>, then this method proceeds
     * as if <code>codebase</code> were <code>null</code>. If <code>Security.verifyCodebaseIntegrity</code> throws any
     * other exception, then this method throws that exception.
     * <p>
     * This method invokes {@link RMIClassLoaderSpi#loadProxyClass(String,String[],ClassLoader)} with
     * <code>codebase</code> as the first argument (or <code>null</code> if in the previous step
     * <code>Security.verifyCodebaseIntegrity</code> was invoked and it threw a <code>SecurityException</code>),
     * <code>interfaceNames</code> as the second argument, and <code>defaultLoader</code> as the third argument. If
     * <code>RMIClassLoaderSpi.loadProxyClass</code> throws a <code>ClassNotFoundException</code>, then this method
     * throws a <code>ClassNotFoundException</code>; if <code>RMIClassLoaderSpi.loadProxyClass</code> throws any other
     * exception, then this method throws that exception; otherwise, this method returns the <code>Class</code> returned
     * by <code>RMIClassLoaderSpi.loadProxyClass</code>.
     *
     * @param codebase
     *                                the list of URLs (separated by spaces) to load classes from, or <code>null</code>
     * @param interfaceNames
     *                                the names of the interfaces for the proxy class to implement
     * @param defaultLoader
     *                                the class loader value (possibly <code>null</code>) to pass as the
     *                                <code>defaultLoader</code> argument to <code>RMIClassLoader.loadProxyClass</code>
     * @param verifyCodebaseIntegrity
     *                                if <code>true</code>, verify that the codebase URLs provide content integrity
     * @param verifierLoader
     *                                the class loader value (possibly <code>null</code>) to pass to
     *                                <code>Security.verifyCodebaseIntegrity</code>, if
     *                                <code>verifyCodebaseIntegrity</code> is <code>true</code>
     * @return the <code>Class</code> object representing the loaded dynamic proxy class
     * @throws MalformedURLException
     *                                if <code>Security.verifyCodebaseIntegrity</code> or
     *                                <code>RMIClassLoaderSpi.loadProxyClass</code> throws a
     *                                <code>MalformedURLException</code>
     * @throws ClassNotFoundException
     *                                if <code>RMIClassLoaderSpi.loadProxyClass</code> throws a
     *                                <code>ClassNotFoundException</code>
     * @throws NullPointerException
     *                                if <code>interfaceNames</code> is <code>null</code> or if any element of
     *                                <code>interfaceNames</code> is <code>null</code>
     **/
    public static Class<?> loadProxyClass(ClassAnnotation codebase,
                                          String[] interfaceNames,
                                          ClassResolver classResolver)
            throws ClassNotFoundException
    {
        return classResolver.loadProxyClass(codebase, interfaceNames);
    }

    private ClassLoading()
    {
        throw new AssertionError();
    }
}
