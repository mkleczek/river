module net.codespaces.river.mercury.dl
{
    exports org.apache.river.mercury.dl to net.codespaces.river.mercury;

    requires transitive net.codespaces.river.services.api;
    requires net.codespaces.river.base;
    requires net.codespaces.river.landlord;
    
    requires java.logging;
    
}