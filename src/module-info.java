module net.codespaces.river.io
{

    exports net.jini.io.context;
    exports net.jini.security.proxytrust;
    exports net.jini.io;
    exports net.jini.loader;

    requires java.logging;
    requires java.rmi;
    requires net.codespaces.river.core;

    requires net.codespaces.access;

}