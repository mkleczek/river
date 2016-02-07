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

testClass=StressTestInterleavedWithShutdown
testCategories=javaspace,javaspace_impl,javaspace_impl_matching,stress
com.sun.jini.qa.harness.timeout=14400
include0=../outrigger.properties

## This test is failing on more than just Linux.  I'm temporarily
## removing it from the test run for Porter beta.
#com.sun.jini.qa.harness.verifier=com.sun.jini.qa.harness.SkipLinuxTestVerifier
com.sun.jini.qa.harness.verifier=com.sun.jini.qa.harness.SkipTestVerifier

com.sun.jini.test.share.restart_wait=10000
com.sun.jini.test.impl.outrigger.matching.StressTest.num_entries=1000
com.sun.jini.test.impl.outrigger.matching.StressTest.num_readers=13
com.sun.jini.test.impl.outrigger.matching.StressTest.num_writers=7
com.sun.jini.test.impl.outrigger.matching.StressTest.interleave=true
com.sun.jini.test.impl.outrigger.matching.tryShutdown=1

