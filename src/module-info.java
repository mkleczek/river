module net.codespaces.river.fiddler.dl
{
    exports org.apache.river.fiddler.dl to net.codespaces.river.fiddler;
    
    requires transitive net.codespaces.river.services.api;
    
    requires transitive net.codespaces.river.base;
}