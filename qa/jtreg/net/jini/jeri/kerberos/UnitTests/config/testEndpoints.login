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

/* JAAS login configuration file for client and server */

onePrincipalServer {
    com.sun.security.auth.module.Krb5LoginModule required 
	useKeyTab=true 
	keyTab="config/jgssTests.keytab"
	principal="testServer2@${java.security.krb5.realm}"
	storeKey=true
	doNotPrompt=true;
};

twoPrincipalServer {
    com.sun.security.auth.module.Krb5LoginModule required 
	useKeyTab=true 
	keyTab="config/jgssTests.keytab"
	principal="testServer1@${java.security.krb5.realm}"
	storeKey=true
	doNotPrompt=true;
    com.sun.security.auth.module.Krb5LoginModule required 
	useKeyTab=true 
	keyTab="config/jgssTests.keytab"
	principal="testServer2@${java.security.krb5.realm}"
	storeKey=true
	doNotPrompt=true;
};

testServer {
    com.sun.security.auth.module.Krb5LoginModule required 
	useKeyTab=true 
	keyTab="config/jgssTests.keytab"
	principal="testServer1@${java.security.krb5.realm}"
	storeKey=true
	doNotPrompt=true;
    com.sun.security.auth.module.Krb5LoginModule required 
	useKeyTab=true 
	keyTab="config/jgssTests.keytab"
	principal="testServer2@${java.security.krb5.realm}"
	storeKey=true
	doNotPrompt=true;
    com.sun.security.auth.module.Krb5LoginModule required 
	useKeyTab=true 
	keyTab="config/jgssTests.keytab"
	principal="testServer3@${java.security.krb5.realm}"
	storeKey=true
	doNotPrompt=true;
};

testClient {
    com.sun.security.auth.module.Krb5LoginModule required
	useTicketCache=true
	ticketCache="config/testClient1.tgt"
	doNotPrompt=true;
    com.sun.security.auth.module.Krb5LoginModule required
	useTicketCache=true
	ticketCache="config/testClient2.tgt"
	doNotPrompt=true;
    com.sun.security.auth.module.Krb5LoginModule required
	useTicketCache=true
	ticketCache="config/testClient3.tgt"
	doNotPrompt=true;
};
