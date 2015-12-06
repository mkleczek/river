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
package com.sun.jini.start;

import java.net.URL;
import java.util.Arrays;
import net.jini.id.Uuid;
import net.jini.id.UuidFactory;
import net.jini.loader.pref.PreferredClassLoader;

/**
 * A simple subclass of <code>PreferredClassLoader</code> that overrides
 * <code>getURLs</code> to return the <code>URL</code>s of the provided export
 * codebase. <code>getURLs</code> is called by the RMI subsystem in order to
 * annotate objects leaving the virtual machine.
 */
/*
 * Implementation note. Subclasses of this class that override
 * getClassAnnotation might need to override getURLs because getURLs
 * uses a "cached" version of the export annotation.
 */
public class ExportClassLoader extends PreferredClassLoader {

    /**
     * Cached value of the provided export codebase <code>URL</code>s
     */
    private final URL[] exportURLs;
    /**
     * Id field used to make toString() unique
     */
    private final Uuid id = UuidFactory.generate();

    /**
     * Trivial constructor that calls
     * <pre>
     * super(importURLs, parent, urlsToPath(exportURLs), false);
     * </pre> and assigns <code>exportURLs</code> to an internal field.
     */
    public ExportClassLoader(URL[] importURLs, URL[] exportURLs, ClassLoader parent) {
        super(importURLs, parent, urlsToPath(exportURLs), false);
        // Not safe to call getClassAnnotation() w/i cons if subclassed,
        // so need to redo "super" logic here.
        if (exportURLs == null) {
            this.exportURLs = importURLs;
        } else {
            this.exportURLs = exportURLs;
        }
    }

    //Javadoc inherited from super type
    public URL[] getURLs() {
        return (URL[]) exportURLs.clone();
    }

    // Javadoc inherited from supertype
    public String toString() {
        URL[] urls = super.getURLs();
        return this.getClass().getName() + "[importURLs=" + (urls == null ? null : Arrays.asList(urls)) + "," + "exportURLs=" + (exportURLs == null ? null : Arrays.asList(exportURLs)) + "," + "parent=" + getParent() + "," + "id=" + id + "]";
    }

    /**
     * Utility method that converts a <code>URL[]</code> into a corresponding,
     * space-separated string with the same array elements.
     *
     * Note that if the array has zero elements, the return value is null, not
     * the empty string.
     */
    private static String urlsToPath(URL[] urls) {
//TODO - check if spaces in file paths are properly escaped (i.e.% chars)	
        if (urls == null) {
            return null;
        } else if (urls.length == 0) {
            return "";
        } else if (urls.length == 1) {
            return urls[0].toExternalForm();
        } else {
            StringBuffer path = new StringBuffer(urls[0].toExternalForm());
            for (int i = 1; i < urls.length; i++) {
                path.append(' ');
                path.append(urls[i].toExternalForm());
            }
            return path.toString();
        }
    }

}
