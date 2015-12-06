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
package com.sun.jini.qa.harness;

import com.sun.jini.admin.DestroyAdmin;

import java.rmi.RemoteException;

import net.jini.admin.Administrable;
import net.jini.config.Configuration;

/** 
 * This class provides static methods that can be used to destroy a service.
 * This implementation was taken from the <code>com.sun.jini.start</code>
 * package when it became obsolete there.
 */
class ServiceDestroyer {

    static final int DESTROY_SUCCESS = 0;
    /* Failure return codes */
    static final int SERVICE_NOT_ADMINISTRABLE = -1;
    static final int SERVICE_NOT_DESTROY_ADMIN = -2;
    static final int DEACTIVATION_TIMEOUT      = -3;
    static final int PERSISTENT_STORE_EXISTS   = -4;

    static final int N_MS_PER_SEC = 1000;
    static final int DEFAULT_N_SECS_WAIT = 600;

    /**
     * Administratively destroys the service referenced by the input
     * parameter. The service input to this method must implement
     * both <code>net.jini.admin.Administrable</code> and the
     * <code>com.sun.jini.admin.DestroyAdmin</code> interfaces
     * in order for this method to successfully destroy the service.
     *
     * @param service reference to the service to destroy
     * @return <code>true</code> if the service's destroy method was invoked
     *         successfully; <code>false</code> otherwise.
     * 
     * @throws java.rmi.RemoteException typically, this exception occurs when
     *         there is a communication failure between the client and the
     *         service's backend. When this exception does occur, the
     *         service may or may not have been successfully destroyed.
     */
    static int destroy(Object service) throws RemoteException {
        /* First, test that the service implements both of the appropriate
         * administration interfaces
         */
        DestroyAdmin destroyAdmin = null;
        if( !(service instanceof Administrable) ) {
            return SERVICE_NOT_ADMINISTRABLE;
        }
        Object admin = ((Administrable)service).getAdmin();
        if( !(admin instanceof DestroyAdmin) ) {
            return SERVICE_NOT_DESTROY_ADMIN;
        }
        destroyAdmin = (DestroyAdmin)admin;
        destroyAdmin.destroy();
        return DESTROY_SUCCESS;
    }

}

