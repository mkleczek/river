package net.codespaces.io;

import java.io.IOException;

import net.codespaces.core.ClassAnnotation;

public interface AnnotationReader
{
    ClassAnnotation readAnnotation() throws IOException, ClassNotFoundException;
}