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

/**
 * 
 */
package com.sun.jini.test.share;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.jini.discovery.DiscoveryChangeListener;
import net.jini.discovery.DiscoveryEvent;

/**
 * Listener for testing notify code resiliance in
 * the face of listener exceptions. Each of the event
 * handling methods throws an exception.
 */
public class BadTestListener implements DiscoveryChangeListener {
    protected Logger logger;

    public BadTestListener(Logger logger) {
        this.logger = logger;
    }

    public void discarded(DiscoveryEvent e) {
        logger.log(Level.FINEST,
                "BadTestListener.discarded about to throw exception");
        throw new BadListenerException("Discarded");

    }

    public void discovered(DiscoveryEvent e) {
        logger.log(Level.FINEST,
                "BadTestListener.discovered about to throw exception");
        throw new BadListenerException("Discovered");
    }

    public void changed(DiscoveryEvent e) {
        logger.log(Level.FINEST,
                "BadTestListener changed about to throw exception");
        throw new BadListenerException("Changed");
    }

    public static class BadListenerException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public BadListenerException(String message) {
            super(message);
        }
    }
}