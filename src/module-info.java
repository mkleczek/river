import org.apache.river.jeri.tcp.services.SdmExporterFactory;

import net.jini.lookup.ExporterFactory;

module net.codespaces.river.jeri.tcp
{

    exports net.jini.jeri.tcp;

    requires java.logging;

    requires transitive net.codespaces.river.jeri;

    provides ExporterFactory with SdmExporterFactory;
}