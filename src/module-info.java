import org.apache.river.discovery.DiscoveryFormatProvider;

module net.codespaces.river.jeri.ssl
{
    exports net.jini.jeri.ssl;

    requires java.logging;
    requires java.rmi;
    requires net.codespaces.core;
    requires net.codespaces.river.base;
    requires net.codespaces.river.core;
    requires net.codespaces.river.io;
    requires net.codespaces.river.jeri;

    provides DiscoveryFormatProvider
            with net.jini.jeri.ssl.internal.DiscoveryClient, net.jini.jeri.ssl.internal.DiscoveryServer;
}