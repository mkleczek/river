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

testClass=SecurityTestNonActivatable
testCategories=start,start_impl

#testClasspath=${com.sun.jini.qa.home}$/lib$/harness.jar$:${com.sun.jini.qa.home}$/lib$/qa1-start-tests.jar$:${com.sun.jini.qa.home}$/lib$/$qajinidep$:${com.sun.jini.jsk.home}$/lib$/jsk-platform.jar

testClasspath=<harnessJar>$:<file:lib/qa1-start-tests.jar>$:${com.sun.jini.jsk.home}$/lib$/jsk-platform.jar$:${com.sun.jini.jsk.home}$/lib$/jsk-lib.jar


/*******************************************************************************
 * Test-specific property files
 ******************************************************************************/
// TestService 1
com.sun.jini.test.impl.start.SecurityTest1.impl=com.sun.jini.test.impl.start.TestServiceImpl
com.sun.jini.test.impl.start.SecurityTest1.classpath=<file:lib/qa1-start-testservice1.jar>
com.sun.jini.test.impl.start.SecurityTest1.codebase=http://${HOST}:${com.sun.jini.test.port}/qa1-start-testservice1-dl.jar
com.sun.jini.test.impl.start.SecurityTest1.policyfile=<url:securityTest.testservice1.policy>
com.sun.jini.test.impl.start.SecurityTest1.log=none
com.sun.jini.test.impl.start.SecurityTest1.serverjvmargs=
com.sun.jini.test.impl.start.SecurityTest1.serviceConfiguration=<url:configs/<config>/testservice.config>
com.sun.jini.test.impl.start.SecurityTest1.starterConfiguration=<url:configs/<config>/testservice.config>
com.sun.jini.test.impl.start.SecurityTest1.type=transient
com.sun.jini.test.impl.start.SecurityTest1.host=master

// TestService 2 - same as above except different classpath, codebase, and policy file
com.sun.jini.test.impl.start.SecurityTest2.impl=${com.sun.jini.test.impl.start.SecurityTest1.impl}
com.sun.jini.test.impl.start.SecurityTest2.classpath=<file:lib/qa1-start-testservice2.jar>
com.sun.jini.test.impl.start.SecurityTest2.codebase=http://${HOST}:${com.sun.jini.test.port}/qa1-start-testservice2-dl.jar
com.sun.jini.test.impl.start.SecurityTest2.policyfile=<url:securityTest.testservice2.policy>
com.sun.jini.test.impl.start.SecurityTest2.log=${com.sun.jini.test.impl.start.SecurityTest1.log}
com.sun.jini.test.impl.start.SecurityTest2.serverjvmargs=${com.sun.jini.test.impl.start.SecurityTest1.serverjvmargs}
com.sun.jini.test.impl.start.SecurityTest2.serviceConfiguration=${com.sun.jini.test.impl.start.SecurityTest1.serviceConfiguration}
com.sun.jini.test.impl.start.SecurityTest2.starterConfiguration=${com.sun.jini.test.impl.start.SecurityTest1.starterConfiguration}
com.sun.jini.test.impl.start.SecurityTest2.type=${com.sun.jini.test.impl.start.SecurityTest1.type}
com.sun.jini.test.impl.start.SecurityTest2.host=master

include0=start.properties
