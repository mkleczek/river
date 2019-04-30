package net.jini.lookup;

import net.jini.config.Configuration;
import net.jini.config.ConfigurationException;
import net.jini.export.Exporter;

public interface ExporterFactory
{

    Exporter create(Configuration config) throws ConfigurationException;

}
