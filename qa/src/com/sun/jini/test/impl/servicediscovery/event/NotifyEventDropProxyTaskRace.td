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

include0=../servicediscovery.properties
testClass=NotifyEventDropProxyTaskRace
testCategories=servicediscovery,servicediscovery_impl,servicediscoveryevent,implservicediscovery,implservicediscoveryevent
net.jini.lookup.nLookupServices=1
net.jini.lookup.nAddLookupServices=0
net.jini.lookup.nServices=0
net.jini.lookup.nAddServices=1
# To override activatable and/or persistent version of
# the lookp service. Uncomment the following items.
#com.sun.jini.qa.harness.runactivation=false
#net.jini.core.lookup.ServiceRegistrar.type=transient
