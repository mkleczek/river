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
testClass=AddListenerEvent
testCategories=servicediscovery,servicediscovery_impl,servicediscoveryevent,implservicediscovery,implservicediscoveryevent
net.jini.lookup.nLookupServices=1
net.jini.lookup.nServices=1
# Not need to run activatable and/or persistent version of
# the lookp service. Uncomment the following items when
# the harness supports starting the non-activatable
# service modes.
#com.sun.jini.qa.harness.runactivation=false
#net.jini.core.lookup.ServiceRegistrar.type=transient
#net.jini.core.lookup.ServiceRegistrar.impl=com.sun.jini.reggie.TransientRegistrarImpl
