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

testClass=MailboxImplNullConfigEntries
testCategories=eventmailbox,eventmailbox_impl

net.jini.event.EventMailbox.instances=6

net.jini.event.EventMailbox.override.0=com.sun.jini.mercury.serverExporter=null
net.jini.event.EventMailbox.override.1=com.sun.jini.mercury.initialLookupAttributes=null
net.jini.event.EventMailbox.override.2=com.sun.jini.mercury.initialLookupLocators=null
net.jini.event.EventMailbox.override.3=com.sun.jini.mercury.leasePeriodPolicy=null
net.jini.event.EventMailbox.override.4=com.sun.jini.mercury.listenerPreparer=null
net.jini.event.EventMailbox.override.5=com.sun.jini.mercury.locatorToJoinPreparer=null
include0=mercury.properties
