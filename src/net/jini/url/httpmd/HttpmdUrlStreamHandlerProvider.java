package net.jini.url.httpmd;

import java.net.URLStreamHandler;
import java.net.spi.URLStreamHandlerProvider;

import net.jini.url.httpmd.Handler;

public class HttpmdUrlStreamHandlerProvider extends URLStreamHandlerProvider
{

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol)
    {
        if (!"httpmd".equals(protocol)) {
            return null;
        }
        
        return new Handler();
    }
}
