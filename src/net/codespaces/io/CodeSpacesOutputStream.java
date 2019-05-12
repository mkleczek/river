package net.codespaces.io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import net.codespaces.CodeSpaces;
import net.codespaces.core.ClassAnnotation;

public class CodeSpacesOutputStream extends ObjectOutputStream
{

    public CodeSpacesOutputStream(OutputStream out) throws IOException
    {
        super(out);
    }
    
    @Override
    protected final void annotateClass(Class<?> cl) throws IOException
    {
        writeAnnotation(CodeSpaces.getClassAnnotation(cl));
    }

    @Override
    protected final void annotateProxyClass(Class<?> cl) throws IOException
    {
        annotateClass(cl);
    }

    protected void writeAnnotation(ClassAnnotation classAnnotation) throws IOException
    {
        writeObject(classAnnotation);
    }

}
