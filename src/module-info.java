module net.codespaces.river.mercury
{
    exports org.apache.river.mercury.start;
    
    requires net.codespaces.river.mercury.dl;
    
    requires net.codespaces.river.base;
    requires net.codespaces.river.landlord;
    
    requires net.codespaces.river.util.server;
    requires java.logging;
    requires net.codespaces.river.jeri.tcp;
    requires net.codespaces.river.activation;
}