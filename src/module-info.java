module net.codespaces.river.norm
{
    
    exports org.apache.river.norm.start;
    opens org.apache.river.norm.start;

    requires java.logging;

    requires net.codespaces.river.jeri.tcp;
    requires net.codespaces.river.activation;
    requires net.codespaces.river.landlord;
    requires net.codespaces.river.services.api;

    requires net.codespaces.river.util.server;
    
    requires net.codespaces.river.norm.dl;

}