module net.codespaces.river.reggie.dl
{

    exports org.apache.river.reggie.dl to net.codespaces.river.reggie;

    requires java.logging;

    requires transitive net.codespaces.river.services.api;
    requires transitive net.codespaces.river.base;

}