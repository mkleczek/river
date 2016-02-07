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

testClass=ServiceStarterCreateActivatableNoConsServiceTest
testCategories=start,start_impl


/*******************************************************************************
 * Test-specific property files
 ******************************************************************************/
// TestService with no activatable constructor
com.sun.jini.test.impl.start.TestServiceNoConsImpl.impl=com.sun.jini.test.impl.start.TestServiceNoConsImpl
com.sun.jini.test.impl.start.TestServiceNoConsImpl.classpath=<file:lib/qa1-start-testservicenocons.jar>
com.sun.jini.test.impl.start.TestServiceNoConsImpl.codebase=http://${HOST}:${com.sun.jini.test.port}/qa1-start-testservicenocons-dl.jar
com.sun.jini.test.impl.start.TestServiceNoConsImpl.policyfile=<url:harness/policy/all.policy>
com.sun.jini.test.impl.start.TestServiceNoConsImpl.log=none
com.sun.jini.test.impl.start.TestServiceNoConsImpl.serverjvmargs=
com.sun.jini.test.impl.start.TestServiceNoConsImpl.serviceConfiguration=<url:configs/<config>/testservice.config>
com.sun.jini.test.impl.start.TestServiceNoConsImpl.starterConfiguration=<url:configs/<config>/testservice.config>
com.sun.jini.test.impl.start.TestServiceNoConsImpl.type=transient


// Shared group overrides
sharedGroup.policyfile=<url:harness/policy/all.policy>
#sharedGroupImpl.policyfile=<url:harness/policy/policy.SecurityTest.sharedgroup>
include0=start.properties
