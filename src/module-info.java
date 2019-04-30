import net.jini.constraint.ConstraintTrustVerifier;
import net.jini.security.TrustVerifier;

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

}