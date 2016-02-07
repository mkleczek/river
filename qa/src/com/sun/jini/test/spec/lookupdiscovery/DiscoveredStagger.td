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

testClass=DiscoveredStagger
testCategories=lookupdiscovery,lookupdiscovery_spec,speclookupdiscovery
include0=lookupdiscovery.properties
include1=com/sun/jini/test/share/reggie6_0Ports.properties

# Special properties file intended to be used by the
# com.sun.jini.test.spec.lookupdiscovery.DiscoveredStagger test

# Interval over which lookup services send multicast announcements is set very short to increase
# the probability of receiving multicast announcements from a desired lookup in a noisy environment.
net.jini.core.lookup.ServiceRegistrar.serverjvmargs=-Dnet.jini.discovery.announce=5000

# Number of seconds to wait for discovered/discarded/changed events is set high
# to allow slower OS's in noisy environments a chance to process events
# Since the events arrive over a long (staggered) period of time, the
# default amount of time to wait for events in this test must be overridden
# to guarantee that the appropriate wait period is applied.
net.jini.discovery.maxSecsEventWait=900

# Will discard a lookup if the expected number of multicast announcements
# do not arrive within net.jini.discovery.announce number of seconds.
# Set this property to 2 minutes so that on slower OS's in noisy environments,
# we don't get un-intended discards occurring
net.jini.discovery.announce=120000

