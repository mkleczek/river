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

package net.jini.jeri.ssl.internal;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;

import javax.net.SocketFactory;

import org.apache.river.discovery.UnicastDiscoveryClient;
import org.apache.river.discovery.UnicastResponse;
import org.apache.river.discovery.internal.EndpointBasedClient;
import org.apache.river.discovery.internal.EndpointInternals;

import net.codespaces.core.ClassResolver;
import net.jini.core.constraint.InvocationConstraints;
import net.jini.io.UnsupportedConstraintException;
import net.jini.jeri.Endpoint;

/**
 * Implements the client side of the <code>net.jini.discovery.ssl</code>
 * unicast discovery format.
 *
 * @author Sun Microsystems, Inc.
 * @since 2.0
 */
public class DiscoveryClient implements UnicastDiscoveryClient {
    
    // Internal implementation. We dont want to expose the internal base
    // classes to the outside.    
    private final ClientImpl impl; 
    
    public DiscoveryClient() {
	impl = new ClientImpl();
    }

    // javadoc inherited from DiscoveryFormatProvider
    public String getFormatName() {
	return impl.getFormatName();
    }

    // javadoc inherited from UnicastDiscoveryClient
    public void checkUnicastDiscoveryConstraints(
		    InvocationConstraints constraints)
	throws UnsupportedConstraintException
    {
	impl.checkUnicastDiscoveryConstraints(constraints);
    }
    
    // javadoc inherited from UnicastDiscoveryClient
    public UnicastResponse doUnicastDiscovery(Socket socket,
					      InvocationConstraints constraints,
					      ClassResolver classResolver,
					      Collection context,
					      ByteBuffer sent,
					      ByteBuffer received)
	throws IOException, ClassNotFoundException
    {
	return impl.doUnicastDiscovery(socket, constraints, classResolver, context, sent, received);
    }

    private static final class ClientImpl extends EndpointBasedClient {

	private static EndpointInternals epi = new SslEndpoint.SslEndpointInternals();
	
	/**
	 * Creates a new instance.
	 */
	ClientImpl() {
	    super("net.jini.discovery.ssl", epi);
	}

	// documentation inherited from EndpointBasedClient
	protected Endpoint getEndpoint(SocketFactory factory,
				       InvocationConstraints constraints)
	    throws UnsupportedConstraintException
	{
	    return SslEndpoint.getInstance("ignored", 1, factory);
	}
    }
}
