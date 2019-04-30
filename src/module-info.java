import org.apache.river.discovery.DiscoveryConstraintTrustVerifier;

import net.jini.discovery.ConstrainableLookupLocatorTrustVerifier;
import net.jini.security.IntegrityVerifier;
import net.jini.security.TrustVerifier;
import net.jini.url.file.FileIntegrityVerifier;
import net.jini.url.httpmd.HttpmdIntegrityVerifier;
import net.jini.url.https.HttpsIntegrityVerifier;

module net.codespaces.river.base
{

    exports net.jini.core.lookup;
    exports net.jini.core.discovery;
    exports net.jini.core.event;
    exports net.jini.core.lease;

    exports net.jini.entry;
    exports net.jini.admin;
    exports net.jini.id;
    exports net.jini.config;
    exports net.jini.discovery;
    exports net.jini.export;
    exports net.jini.lookup;
    exports net.jini.lookup.entry;
    exports net.jini.lease;

    exports org.apache.river.thread;
    exports org.apache.river.resource;
    exports org.apache.river.config;
    exports org.apache.river.proxy;
    exports org.apache.river.lease;
    exports org.apache.river.action;
    exports org.apache.river.constants;
    exports org.apache.river.discovery;
    exports org.apache.river.start;

    exports org.apache.river.lookup.entry;

    requires java.logging;
    requires java.rmi;
    requires java.security.jgss;

    requires transitive net.codespaces.river.io;
    requires java.desktop;

    provides TrustVerifier with ConstrainableLookupLocatorTrustVerifier, DiscoveryConstraintTrustVerifier;
    provides IntegrityVerifier with FileIntegrityVerifier, HttpmdIntegrityVerifier, HttpsIntegrityVerifier;

    uses net.jini.lookup.ExporterFactory;

}