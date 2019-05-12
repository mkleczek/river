package org.apache.river.jeri.tcp.services;

import net.jini.config.Configuration;
import net.jini.export.Exporter;
import net.jini.jeri.BasicILFactory;
import net.jini.jeri.BasicJeriExporter;
import net.jini.jeri.tcp.TcpServerEndpoint;
import net.jini.lookup.ExporterFactory;

public class SdmExporterFactory implements ExporterFactory
{
    @Override
    public Exporter create(Configuration config)
    {
        return new BasicJeriExporter(TcpServerEndpoint.getInstance(0), new BasicILFactory(), false, false);
    }
}
