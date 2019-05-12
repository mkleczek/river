package net.codespaces.io;
/*
 * Copyright 2019 Michal Kleczek (michal@kleczek.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.StreamCorruptedException;
import java.util.Objects;

import net.codespaces.core.ClassAnnotation;
import net.codespaces.core.ClassResolver;

/**
 * @author Michal Kleczek <a href="mailto:michal@kleczek.org">({@literal michal@kleczek.org})</a>
 */
public class CodeSpacesInputStream extends ObjectInputStream implements AnnotationReader
{

    private static final int MAX_LEVEL = 2;

    private final ClassResolver classResolver;
    private volatile int level;

    /**
     * @param in
     * @throws IOException
     */
    public CodeSpacesInputStream(final InputStream in,
                                 final ClassResolver classResolver)
            throws IOException
    {
        super(in);
        this.classResolver = Objects.requireNonNull(classResolver);
    }

    protected AnnotationReader annotationInput()
    {
        return this;
    }
    
    @Override
    protected Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException
    {
        return classResolver.loadProxyClass(annotationInput().readAnnotation(), interfaces);
    }

    @Override
    protected Class<?> resolveClass(final ObjectStreamClass desc) throws IOException, ClassNotFoundException
    {
        return classResolver.loadClass(annotationInput().readAnnotation(), desc.getName());
    }
    
    @Override
    public final ClassAnnotation readAnnotation() throws IOException, ClassNotFoundException
    {
        level++;
        try {
            if (level > MAX_LEVEL) {
                throw new StreamCorruptedException("Annotation annotated by annotations too deeply :)");
            }
            return (ClassAnnotation) readObject();
        }
        finally {
            level--;
        }
    }

}
