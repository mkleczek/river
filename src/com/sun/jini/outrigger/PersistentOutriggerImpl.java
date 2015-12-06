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
package com.sun.jini.outrigger;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.MarshalledObject;
import javax.security.auth.login.LoginException;
import net.jini.config.ConfigurationException;
import com.sun.jini.start.LifeCycle;

/**
 * <code>OutriggerServerWrapper</code> subclass for
 * persistent servers.
 *
 * @author Sun Microsystems, Inc.
 * @since 2.0
 */
class PersistentOutriggerImpl extends OutriggerServerWrapper {
    /**
     * Create a new non-activatable, persistent space.
     * The space will be implemented by a new
     * <code>OutriggerServerImpl()</code> server instance.
     * @param configArgs set of strings to be used to obtain a
     *                   <code>Configuration</code>.
     * @param lifeCycle the object to notify when this
     *                  service is destroyed.
     * @throws IOException if there is problem recovering data
     *         from disk or exporting the server for the space.
     * @throws ConfigurationException if the configuration is 
     *         malformed.  
     * @throws LoginException if the <code>loginContext</code> specified
     *         in the configuration is non-null and throws 
     *         an exception when login is attempted.
     */
    PersistentOutriggerImpl(String[] configArgs, LifeCycle lifeCycle) 
        throws IOException, ConfigurationException, LoginException
    {
	super(configArgs, lifeCycle, true);
	allowCalls();
    }
}

