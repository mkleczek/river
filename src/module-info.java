import net.jini.constraint.ConstraintTrustVerifier;
import net.jini.security.IntegrityVerifier;
import net.jini.security.TrustVerifier;
import net.jini.url.file.FileIntegrityVerifier;
import net.jini.url.httpmd.HttpmdIntegrityVerifier;
import net.jini.url.https.HttpsIntegrityVerifier;

module net.codespaces.river.core.security
{

    exports net.jini.core.constraint;
    exports net.jini.security;
    exports net.jini.security.policy;

    exports org.apache.river.logging;

    requires java.logging;
    requires java.rmi;
    requires java.security.jgss;

    provides TrustVerifier with ConstraintTrustVerifier;
    provides IntegrityVerifier with FileIntegrityVerifier, HttpmdIntegrityVerifier, HttpsIntegrityVerifier;

}