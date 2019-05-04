import java.net.spi.URLStreamHandlerProvider;

import net.jini.constraint.ConstraintTrustVerifier;
import net.jini.security.IntegrityVerifier;
import net.jini.security.TrustVerifier;
import net.jini.url.file.FileIntegrityVerifier;
import net.jini.url.httpmd.HttpmdIntegrityVerifier;
import net.jini.url.httpmd.HttpmdUrlStreamHandlerProvider;
import net.jini.url.https.HttpsIntegrityVerifier;

module net.codespaces.river.core
{

    exports net.jini.core.entry;
    exports net.jini.core.constraint;
    exports net.jini.security;
    exports net.jini.security.policy;
    exports net.jini.constraint;

    exports org.apache.river.logging;
    exports org.apache.river.api.net;
    exports org.apache.river.api.common;
    exports org.apache.river.api.util;
    exports org.apache.river.collection;

    requires java.logging;
    requires transitive java.rmi;
    requires java.security.jgss;

    provides TrustVerifier with ConstraintTrustVerifier;
    provides IntegrityVerifier with FileIntegrityVerifier, HttpmdIntegrityVerifier, HttpsIntegrityVerifier;
    provides URLStreamHandlerProvider with HttpmdUrlStreamHandlerProvider;

}