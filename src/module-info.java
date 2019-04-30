import net.jini.security.TrustVerifier;
import net.jini.security.proxytrust.ProxyTrustVerifier;

module net.codespaces.river.io
{

    exports net.jini.io.context;
    exports net.jini.security.proxytrust;
    exports net.jini.io;
    exports net.jini.loader;

    requires java.logging;
    requires java.rmi;
    requires transitive net.codespaces.river.core;

    provides TrustVerifier with ProxyTrustVerifier;

}