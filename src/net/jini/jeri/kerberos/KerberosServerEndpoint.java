/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.jini.jeri.kerberos;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.security.auth.AuthPermission;
import javax.security.auth.Subject;
import javax.security.auth.kerberos.KerberosPrincipal;

import com.sun.security.jgss.GSSUtil;

import net.jini.io.UnsupportedConstraintException;
import net.jini.jeri.ServerEndpoint;
import net.jini.jeri.kerberos.internal.KerberosEndpoint;
import net.jini.jeri.kerberos.internal.KerberosServerEndpointImpl;
import net.jini.jeri.kerberos.internal.KerberosTrustVerifier;

/**
 * A {@link ServerEndpoint} implementation that uses Kerberos as the
 * underlying network security protocol to support security related
 * invocation constraints for remote requests.  Instances of this
 * class are referred to as the server endpoints of the Kerberos
 * provider, while instances of {@link KerberosEndpoint} are referred
 * to as the endpoints of the provider. <p>
 *
 * Instances of this class are intended to be created for use with the
 * {@link net.jini.jeri.BasicJeriExporter} class.  Calls to {@link
 * #enumerateListenEndpoints enumerateListenEndpoints} return
 * instances of {@link KerberosEndpoint}. <p>
 *
 * This class supports at least the following standard constraints:
 * <p>
 *
 * <ul>
 * <li>{@link net.jini.core.constraint.Integrity#YES}
 * <li>{@link net.jini.core.constraint.Confidentiality}
 * <li>{@link net.jini.core.constraint.ClientAuthentication#YES}
 * <li>{@link net.jini.core.constraint.ConnectionAbsoluteTime},
 *     trivially, since this only takes effect on the client side
 * <li>{@link net.jini.core.constraint.ConnectionRelativeTime},
 *     trivially, since this only takes effect on the client side
 * <li>{@link net.jini.core.constraint.ServerAuthentication#YES}
 * <li>{@link net.jini.core.constraint.ClientMaxPrincipal}, when it
 *     contains at least one {@link KerberosPrincipal}
 * <li>{@link net.jini.core.constraint.ClientMaxPrincipalType}, when
 *     it contains the <code>KerberosPrincipal</code> class
 * <li>{@link net.jini.core.constraint.ClientMinPrincipal}, when it
 *     contains exactly one <code>KerberosPrincipal</code>
 * <li>{@link net.jini.core.constraint.ClientMinPrincipalType}, when
 *     it contains only the <code>KerberosPrincipal</code> class
 * <li>{@link net.jini.core.constraint.ServerMinPrincipal}, when it
 *     contains exactly one <code>KerberosPrincipal</code>
 * <li>{@link net.jini.core.constraint.Delegation}
 * <li>{@link net.jini.core.constraint.ConstraintAlternatives}, if the
 *     elements all have the same actual class and at least one
 *     element is supported
 * </ul>
 *
 * To get an instance of <code>KerberosServerEndpoint</code>, one of
 * the <code>getInstance</code> methods of the class has to be
 * invoked.  The returned server endpoint instance encapsulates a set
 * of properties that it will later use to receive and dispatch
 * inbound requests.  The following describes how some of these
 * properties are chosen: <p>
 *
 * <ul>
 * <li><code>serverSubject</code> - The {@link Subject} that contains
 *     the principal and credential to be used by the server endpoint
 *     to authenticate itself to its remote callers.  The subject is
 *     either provided by the caller of the <code>getInstance</code>
 *     method as a non-<code>null</code> argument, or extracted from
 *     the access control context of the current thread when the
 *     <code>getInstance</code> method is called.  The later value is
 *     also referred to as the default server subject.
 * <li><code>serverPrincipal</code> - The
 *     <code>KerberosPrincipal</code> that the server endpoint will
 *     authenticate itself as to all clients.  If the caller of the
 *     <code>getInstance</code> method provides a
 *     non-<code>null</code> <code>serverPrincipal</code> argument, it
 *     is used without any further checking; otherwise the default
 *     server principal will be used.  The default server principal
 *     can be any <code>KerberosPrincipal</code> instance in the
 *     <code>serverSubject</code>'s principal set, whose corresponding
 *     <code>KerberosKey</code> is found in the subject's private
 *     credential set and is still valid, provided the caller has been
 *     granted the {@link net.jini.security.AuthenticationPermission}
 *     with the principal as its local principal and
 *     <code>listen</code> as its action.
 * <li><code>serverHost</code> - The host name that will be
 *     encapsulated in <code>KerberosEndpoint</code> instances created
 *     by the server endpoint.  If a non-<code>null serverHost</code>
 *     is provided by the caller, it will be used; otherwise the
 *     default value is used, which is the IP address of the local
 *     host, as obtained from {@link InetAddress#getLocalHost
 *     InetAddress.getLocalHost}.  The host name does not affect the
 *     behavior of the listen operation itself, which always listens
 *     on all of the local system's network addresses, unless a
 *     <code>ServerSocketFactory</code> is provided by the caller, in
 *     which case the factory will be in charge.
 * </ul>
 *
 * This class permits specifying a {@link SocketFactory} for creating
 * the {@link Socket} instances that the associated
 * <code>KerberosEndpoint</code> instances use to make remote
 * connections back to the server, and a {@link ServerSocketFactory}
 * for creating the {@link ServerSocket} instances that the server
 * endpoint uses to accept remote connections. <p>
 *
 * A <code>SocketFactory</code> used with instances of this class
 * should be serializable, and should implement {@link Object#equals
 * Object.equals} to return <code>true</code> when passed an instance
 * that represents the same (functionally equivalent) socket
 * factory. A <code>ServerSocketFactory</code> used with instances of
 * this class should implement <code>Object.equals</code> to return
 * <code>true</code> when passed an instance that represents the same
 * (functionally equivalent) server socket factory. <p>
 *
 * This class uses the <a
 * href="../connection/doc-files/mux.html">Jini extensible remote
 * invocation (Jini ERI) multiplexing protocol</a> to map outgoing
 * requests to the underlying secure connection streams. <p>
 *
 * The secure connection streams in this provider are implemented
 * using the Kerberos Version 5 GSS-API Mechanism, defined in <a
 * href="http://www.ietf.org/rfc/rfc1964.txt">RFC 1964</a>, over
 * socket connections between client and server endpoints. <p>
 *
 * Note that, because Kerberos inherently requires client authentication,
 * this transport provider does not support distributed garbage collection
 * (DGC); if DGC is enabled using {@link net.jini.jeri.BasicJeriExporter},
 * all DGC remote calls through this provider will silently fail.
 *
 * @org.apache.river.impl <!-- Implementation Specifics -->
 *
 * This class uses the following {@link Logger} to log information
 * at the following logging levels: <p>
 * 
 * <table border="1" cellpadding="5" summary="Describes logging to the
 *     server logger performed by endpoint classes in this package at
 *     different logging levels">
 * 
 *     <caption halign="center" valign="top"><b><code>
 * 	net.jini.jeri.kerberos.server</code></b></caption>
 * 
 *     <tr> <th scope="col"> Level <th scope="col"> Description
 *     <tr> <td> {@link java.util.logging.Level#WARNING WARNING}
 *          <td> unexpected failure while accepting connections on the created
 *               <code>ServerSocket</code>.
 *     <tr> <td> {@link org.apache.river.logging.Levels#FAILED FAILED}
 * 
 *          <td> problems with permission checking, server principal and
 *               Kerberos key presence checking, {@link
 *               org.ietf.jgss.GSSCredential} creation, socket connect
 *               acception, {@link org.ietf.jgss.GSSContext}
 *               establishment, credential expiration, or wrap/unwrap
 *               GSS tokens
 *     <tr> <td> {@link org.apache.river.logging.Levels#HANDLED HANDLED}
 *          <td> failure to set TCP no delay or keep alive properties on
 *               sockets
 *     <tr> <td> {@link java.util.logging.Level#FINE FINE}
 *          <td> server endpoint creation, {@link
 *               net.jini.jeri.ServerCapabilities#checkConstraints
 *               checkConstraints} results, server socket creation,
 *               socket connect acceptance, server connection
 *               creation/destruction, <code>GSSContext</code>
 *               establishment
 *     <tr> <td> {@link java.util.logging.Level#FINEST FINEST}
 *          <td> data message encoding/decoding using
 *               <code>GSSContext</code>
 * </table> <p>
 *
 * When the <code>ListenEndpoint.listen</code> method of this
 * implementation is invoked, a search is conducted on the private
 * credentials of the <code>serverSubject</code>, the first valid
 * <code>KerberosKey</code> whose principal equals to the
 * <code>serverPrincipal</code> is chosen as the server credential for
 * the listen operation.  The presence of this server credential in
 * the <code>serverSubject</code> as well as its validity are checked
 * both when a new incoming connection is received and a new request
 * arrives on an established connection; if the checks fail, the
 * listen operation or the connection will be aborted permanently. <p>
 *
 * This implementation uses the standard <a
 * href="http://www.ietf.org/rfc/rfc2853.txt">Java(TM) GSS-API</a>.
 * Additionally, for each inbound connection established, it invokes
 * {@link GSSUtil#createSubject GSSUtil.createSubject} to construct a
 * <code>Subject</code> instance, which encapsulates the principal and
 * delegated credential, if any, of the corresponding remote caller.
 *
 * 
 * @see KerberosEndpoint
 * @see KerberosTrustVerifier
 * @since 2.0
 */
public abstract class KerberosServerEndpoint implements ServerEndpoint {

    /**
     * Returns a <code>KerberosServerEndpoint</code> instance with the
     * specified port, using the default server subject, server
     * principal, and server host.
     *
     * @param port the port this server endpoint will listen on, 0 to
     *        use any free port
     * @return a <code>KerberosServerEndpoint</code> instance
     * @throws UnsupportedConstraintException if the caller has not
     *         been granted the right
     *         <code>AuthenticationPermission</code>, or there is no
     *         default server subject
     *         (<code>serverSubject.getSubject(AccessController.getContext())
     *         </code> returns <code>null</code>), or no appropriate
     *         Kerberos principal and corresponding Kerberos key can
     *         be found in the server subject
     * @throws SecurityException if there is a security manager and
     *         the following condition is true:
     *
     *         <ul>
     *         <li>The caller has been granted
     *             {@link AuthPermission}<code>("getSubject") </code>,
     *             but no <code>listen</code>
     *             <code>AuthenticationPermission</code> whose local
     *             principal is a principal in the server subject's
     *             principal set, which is required for accessing any
     *             private credentials corresponding to the principal
     *             in the server subject.
     *         </ul>
     *
     * @throws IllegalArgumentException if <code>serverPort</code> is
     *         not in the range of <code>0</code> to
     *         <code>65535</code>
     */
    public static KerberosServerEndpoint getInstance(int port)
	throws UnsupportedConstraintException
    {
	return new KerberosServerEndpointImpl(null, null, null, port, null, null);
    }

    /**
     * Returns a <code>KerberosServerEndpoint</code> instance with the
     * specified server host and port, using the default server
     * subject and server principal.
     *
     * @param serverHost the name or IP address of the server host the
     *        <code>KerberosEndpoint</code> instances created by this
     *        server endpoint will connect to. If <code>null</code>,
     *        the default server host will be used.
     * @param port the port this server endpoint will listen on, 0 to
     *        use any free port
     * @return a <code>KerberosServerEndpoint</code> instance
     * @throws UnsupportedConstraintException if the caller has not
     *         been granted the right
     *         <code>AuthenticationPermission</code>, or there is no
     *         default server subject
     *         (<code>serverSubject.getSubject(AccessController.getContext())
     *         </code> returns <code>null</code>), or no appropriate
     *         Kerberos principal and corresponding Kerberos key can
     *         be found in the server subject
     * @throws SecurityException if there is a security manager and
     *         the following condition is true:
     *
     *         <ul>
     *         <li>The caller has been granted
     *             {@link AuthPermission}<code>("getSubject") </code>,
     *             but no <code>listen</code>
     *             <code>AuthenticationPermission</code> whose local
     *             principal is a principal in the server subject's
     *             principal set, which is required for accessing any
     *             private credentials corresponding to the principal
     *             in the server subject.
     *         </ul>
     *
     * @throws IllegalArgumentException if <code>serverPort</code> is
     *         not in the range of <code>0</code> to
     *         <code>65535</code>
     */
    public static KerberosServerEndpoint getInstance(
	String serverHost, int port)
	throws UnsupportedConstraintException
    {
	return new KerberosServerEndpointImpl(
	    null, null, serverHost, port, null, null);
    }

    /**
     * Returns a <code>KerberosServerEndpoint</code> instance with the
     * specified server host, port, and socket factories, using the
     * default server subject and server principal.
     *
     * @param serverHost the name or IP address of the server host the
     *        <code>KerberosEndpoint</code> instances created by this
     *        server endpoint will connect to. If <code>null</code>,
     *        the default server host will be used.
     * @param port the port this server endpoint will listen on, 0 to
     *        use any free port
     * @param csf the <code>SocketFactory</code> to be used by the
     *        <code>KerberosEndpoint</code> created by this server
     *        endpoint to create sockets, or <code>null</code> to let
     *        the <code>KerberosEndpoint</code> create {@link Socket}s
     *        directly.
     * @param ssf the <code>ServerSocketFactory</code> to use for this
     *        <code>KerberosServerEndpoint</code>, or
     *        <code>null</code> to let the
     *        <code>KerberosServerEndpoint</code> create {@link
     *        ServerSocket}s directly.
     * @return a <code>KerberosServerEndpoint</code> instance
     * @throws UnsupportedConstraintException if the caller has not
     *         been granted the right
     *         <code>AuthenticationPermission</code>, or there is no
     *         default server subject
     *         (<code>serverSubject.getSubject(AccessController.getContext())
     *         </code> returns <code>null</code>), or no appropriate
     *         Kerberos principal and corresponding Kerberos key can
     *         be found in the server subject
     * @throws SecurityException if there is a security manager and
     *         the following condition is true:
     *
     *         <ul>
     *         <li>The caller has been granted
     *             {@link AuthPermission}<code>("getSubject") </code>,
     *             but no <code>listen</code>
     *             <code>AuthenticationPermission</code> whose local
     *             principal is a principal in the server subject's
     *             principal set, which is required for accessing any
     *             private credentials corresponding to the principal
     *             in the server subject.
     *         </ul>
     *
     * @throws IllegalArgumentException if <code>serverPort</code> is
     *         not in the range of <code>0</code> to
     *         <code>65535</code>
     */
    public static KerberosServerEndpoint getInstance(
	String serverHost, int port, SocketFactory csf,
	ServerSocketFactory ssf)
	throws UnsupportedConstraintException
    {
	return new KerberosServerEndpointImpl(
	    null, null, serverHost, port, csf, ssf);
    }

    /**
     * Returns a <code>KerberosServerEndpoint</code> instance with the
     * specified server subject, server principal, server host, and
     * port.
     *
     * @param serverSubject the server subject to use for
     *        authenticating the server. If <code>null</code>, the
     *        subject associated with the current access control
     *        context will be used.
     * @param serverPrincipal the principal server should authenticate
     *        as. If <code>null</code>, then the default server
     *        principal will be used.
     * @param serverHost the name or IP address of the server host the
     *        <code>KerberosEndpoint</code> instances created by this
     *        server endpoint will connect to. If <code>null</code>,
     *        the default server host will be used.
     * @param port the port this server endpoint will listen on, 0 to
     *        use any free port
     * @return a <code>KerberosServerEndpoint</code> instance
     * @throws UnsupportedConstraintException if the caller has not
     *         been granted the right
     *         <code>AuthenticationPermission</code>, or there is no
     *         default server subject
     *         (<code>serverSubject.getSubject(AccessController.getContext())
     *         </code> returns <code>null</code>), or no appropriate
     *         Kerberos principal and corresponding Kerberos key can
     *         be found in the server subject
     * @throws SecurityException if there is a security manager and
     *         the following condition is true:
     *
     *         <ul>
     *         <li>The passed in serverPrincipal is <code>null</code>,
     *             the caller has the
     *             {@link AuthPermission}<code>("getSubject")
     *             </code>, but no <code>listen</code>
     *             <code>AuthenticationPermission</code> whose local
     *             principal is a principal in the server subject's
     *             principal set, which is required for accessing any
     *             private credentials corresponding to the principal
     *             in the server subject.
     *         </ul>
     *
     * @throws IllegalArgumentException if <code>serverPort</code> is
     *         not in the range of <code>0</code> to
     *         <code>65535</code>
     */
    public static KerberosServerEndpoint getInstance(
	Subject serverSubject, KerberosPrincipal serverPrincipal,
	String serverHost, int port)
	throws UnsupportedConstraintException
    {
	return new KerberosServerEndpointImpl(
	    serverSubject, serverPrincipal, serverHost, port, null, null);
    }

    /**
     * Returns a <code>KerberosServerEndpoint</code> instance with the
     * specified server subject, server principal, server host, port,
     * and socket factories.
     *
     * @param serverSubject the server subject to use for
     *        authenticating the server. If <code>null</code>, the
     *        subject associated with the current access control
     *        context will be used.
     * @param serverPrincipal the principal server should authenticate
     *        as. If <code>null</code>, then the default server
     *        principal will be used.
     * @param serverHost the name or IP address of the server host the
     *        <code>KerberosEndpoint</code> instances created by this
     *        server endpoint will connect to. If <code>null</code>,
     *        the default server host will be used.
     * @param port the port this server endpoint will listen on, 0 to
     *        use any free port
     * @param csf the <code>SocketFactory</code> to be used by the
     *        <code>KerberosEndpoint</code> created by this server
     *        endpoint to create sockets, or <code>null</code> to let
     *        the <code>KerberosEndpoint</code> create {@link Socket}s
     *        directly.
     * @param ssf the <code>ServerSocketFactory</code> to use for this
     *        <code>KerberosServerEndpoint</code>, or
     *        <code>null</code> to let the
     *        <code>KerberosServerEndpoint</code> create {@link
     *        ServerSocket}s directly.
     * @return a <code>KerberosServerEndpoint</code> instance
     * @throws UnsupportedConstraintException if the caller has not
     *         been granted the right
     *         <code>AuthenticationPermission</code>, or there is no
     *         default server subject
     *         (<code>serverSubject.getSubject(AccessController.getContext())
     *         </code> returns <code>null</code>), or no appropriate
     *         Kerberos principal and corresponding Kerberos key can
     *         be found in the server subject
     * @throws SecurityException if there is a security manager and
     *         the following condition is true:
     *
     *         <ul>
     *         <li>The passed in serverPrincipal is <code>null</code>,
     *             the caller has the {@link
     *             AuthPermission}<code>("getSubject") </code>, but
     *             no <code>listen</code>
     *             <code>AuthenticationPermission</code> whose local
     *             principal is a principal in the server subject's
     *             principal set, which is required for accessing any
     *             private credentials corresponding to the principal
     *             in the server subject.
     *         </ul>
     *
     * @throws IllegalArgumentException if <code>serverPort</code> is
     *         not in the range of <code>0</code> to
     *         <code>65535</code>
     */
    public static KerberosServerEndpoint getInstance(
	Subject serverSubject, KerberosPrincipal serverPrincipal,
	String serverHost, int port, SocketFactory csf,
	ServerSocketFactory ssf)
	throws UnsupportedConstraintException
    {
	return new KerberosServerEndpointImpl(
	    serverSubject, serverPrincipal, serverHost, port, csf, ssf);
    }

}
