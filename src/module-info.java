module net.codespaces.river.services.api
{

    exports net.jini.event;
    exports net.jini.entry;
    exports net.jini.core.event;
    exports net.jini.core.lease;
    exports org.apache.river.admin;
    exports net.jini.space;
    exports net.jini.core.transaction;
    exports net.jini.core.transaction.server;

    requires transitive net.codespaces.river.core;
    requires transitive java.rmi;

}