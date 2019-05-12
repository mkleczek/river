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

package org.apache.river.proxy;

import net.codespaces.CodeSpaces;
import net.codespaces.core.ClassAnnotation;
import net.codespaces.core.ClassResolver;

/**
 * Provided only for proxy binary backward compatibility with River versions
 * before 3.0
 * @author peter
 * @since 3.0
 */
public final class CodebaseProvider
{
    private CodebaseProvider()
    {
        throw new AssertionError();
    }

    public static ClassAnnotation getClassAnnotation(final Class<?> clas)
    {
        return CodeSpaces.getClassAnnotation(clas);
    }

    public static Class<?> loadClass(final ClassAnnotation codebase,
                                     final String name)
            throws ClassNotFoundException
    {
        return loadClass(codebase, name, CodeSpaces.globalClassResolver());
    }

    public static Class<?> loadClass(final ClassAnnotation codebase,
                                     final String name,
                                     final ClassResolver classResolver)
            throws ClassNotFoundException
    {
        return classResolver.loadClass(codebase, name);
    }

    public static Class<?> loadProxyClass(final ClassAnnotation codebase,
                                          final String[] interfaceNames)
            throws ClassNotFoundException
    {
        return loadProxyClass(codebase, interfaceNames, CodeSpaces.globalClassResolver());
    }

    public static Class<?> loadProxyClass(final ClassAnnotation codebase,
                                          final String[] interfaceNames,
                                          final ClassResolver classResolver)
            throws ClassNotFoundException
    {
        return classResolver.loadProxyClass(codebase, interfaceNames);
    }
}
