import net.jini.security.TrustVerifier;
import net.jini.security.proxytrust.ProxyTrustVerifier;

module net.codespaces.river.core
{
    
    exports net.jini.security;
    exports net.jini.io.context;
    exports net.jini.security.policy;
    exports net.jini.security.proxytrust;
    exports net.jini.io;

    requires java.logging;
    requires java.rmi;
    requires net.codespaces.river.core;
    
    provides TrustVerifier with ProxyTrustVerifier;
}