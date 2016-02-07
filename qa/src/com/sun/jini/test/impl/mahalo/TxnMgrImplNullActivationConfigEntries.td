#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership. The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License. You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

include0=mahalo.properties
testClass=TxnMgrImplNullConfigEntries
testCategories=txnmanager,txnmanager_impl

net.jini.core.transaction.server.TransactionManager.instances=4

net.jini.core.transaction.server.TransactionManager.override.0=com.sun.jini.mahalo.activationIdPreparer=null
net.jini.core.transaction.server.TransactionManager.override.1=com.sun.jini.mahalo.activationSystemPreparer=null
net.jini.core.transaction.server.TransactionManager.override.6=com.sun.jini.mahalo.persistenceDirectory=null
net.jini.core.transaction.server.TransactionManager.override.7=com.sun.jini.mahalo.recoveredParticipantPreparer=null

com.sun.jini.qa.harness.verifier=${com.sun.jini.qa.harness.verifier} com.sun.jini.test.impl.mahalo.ActivatableMahaloVerifier

# Not obtained unless there are any locators to recover
#net.jini.core.transaction.server.TransactionManager.override.10=com.sun.jini.mahalo.recoveredLookupLocatorPreparer=null
