import org.apache.river.discovery.DiscoveryFormatProvider;

module net.codespaces.river.jeri.kerberos
{
    exports net.jini.jeri.kerberos;

    requires java.logging;
    requires java.rmi;
    requires java.security.jgss;
    requires net.codespaces.core;
    requires net.codespaces.river.base;
    requires net.codespaces.river.core;
    requires net.codespaces.river.io;
    requires net.codespaces.river.jeri;
    requires jdk.security.jgss;
    
    provides DiscoveryFormatProvider with net.jini.jeri.kerberos.internal.DiscoveryClient, net.jini.jeri.kerberos.internal.DiscoveryServer;
    
}