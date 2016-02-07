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

testClass=AppleUserImpl
testCategories=reliability
testConfiguration=<url:configs/<config>/TransportReliability.config>
testConfigurationOptional=true
testPolicyFile=<url:harness/policy/all.policy>

# disable all class servers
com.sun.jini.qa.harness.runkitserver=false 
com.sun.jini.qa.harness.runjiniserver=false
com.sun.jini.qa.testClassServer=

# skip jrmp because this type of reliability testing is
#           already done by the J2SE RMI reliability test
# skip none because this is just a duplication of jeri
# skip kerberos because dgc is required by the test
#           but is not supported by the kerberos provider
com.sun.jini.qa.harness.verifier=${com.sun.jini.qa.harness.verifier} com.sun.jini.qa.harness.SkipConfigTestVerifier
com.sun.jini.qa.harness.skipConfigs=none,jrmp,kerberos

com.sun.jini.test.impl.reliability.maxLevel=8
com.sun.jini.test.impl.reliability.maxThreads=12
