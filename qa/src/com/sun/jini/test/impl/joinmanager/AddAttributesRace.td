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

testClass=AddAttributesRace
testCategories=joinmanager,joinmanager_impl

#disable this test - it requires a special version of reggie that
#has yet to be updated for Davis
com.sun.jini.qa.harness.verifier=com.sun.jini.qa.harness.SkipTestVerifier
include0=joinmanager.properties

# Properties file for test AddAttributesRace which is a
# regression test for bug # 4671109

net.jini.core.lookup.ServiceRegistrar.classpath=<file:/lib/qa1-reggie-race.jar>
net.jini.core.lookup.ServiceRegistrar.codebase=http://${HOST}:${com.sun.jini.test.port}/qa1-reggie-race-dl.jar
net.jini.core.lookup.ServiceRegistrar.policyfile=<url:harness/policy/all.policy>


# Interval over which lookup services send multicast announcements is set very short to increase
# the probability of receiving multicast announcements from a desired lookup in a noisy environment.
net.jini.core.lookup.ServiceRegistrar.serverjvmargs=-Dnet.jini.discovery.announce=5000

# Number of seconds to wait for discovered/discarded/changed events is set high
# to allow slower OS's in noisy environments a chance to process events
net.jini.discovery.maxSecsEventWait=600

# Uses the default implementation for the lookup service

net.jini.lookup.nLookupServices=1
net.jini.lookup.nAddLookupServices=0

net.jini.lookup.nServices=1
net.jini.lookup.nAddServices=0

net.jini.lookup.nAttributes=3
net.jini.lookup.nAddAttributes=2
