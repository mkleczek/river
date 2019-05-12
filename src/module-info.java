module net.codespaces.river.fiddler
{
    
    exports org.apache.river.fiddler.start;
    
    requires net.codespaces.river.fiddler.dl;
    requires net.codespaces.river.util.server;
    requires net.codespaces.river.activation;
    requires net.codespaces.river.jeri.tcp;
    
    requires java.logging;
}