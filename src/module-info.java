import net.jini.jeri.BasicJeriTrustVerifier;
import net.jini.security.TrustVerifier;

module net.codespaces.river.jeri
{

    exports net.jini.jeri;
    exports net.jini.jeri.connection;

    exports org.apache.river.jeri.internal.runtime;
    exports org.apache.river.discovery.internal;
    exports org.apache.river.jeri.internal.connection;

    requires java.logging;
    requires transitive java.rmi;
    requires transitive net.codespaces.river.base;
    requires net.codespaces.core;
    requires net.codespaces.access;

    provides TrustVerifier with BasicJeriTrustVerifier;

}