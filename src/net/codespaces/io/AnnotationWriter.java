package net.codespaces.io;

import java.io.IOException;

import net.codespaces.core.ClassAnnotation;

public interface AnnotationWriter
{
    void writeAnnotation(ClassAnnotation annotation) throws IOException;
}