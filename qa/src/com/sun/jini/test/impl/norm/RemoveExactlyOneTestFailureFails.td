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

# Test creates a set and registers for both warning and renewail failure
# events.  One listener will throw an exception, the other will succed.
# We the take action that will cause both to listers to recive an event.
# If both recive an event we optionally shutdown the server, and then
# take the action again.  At this point we pass if only one of the
# events are recived (the one with the listener that did not throw
# an exception) and fail otherwise

testClass=RemoveExactlyOneTest
testCategories=renewalservice,renewalservice_impl

com.sun.jini.test.impl.norm.tryShutdown=true
com.sun.jini.test.impl.norm.failureFails=true
include0=norm.properties
testLoggingProperties=<file: src/com/sun/jini/test/resources/qa1_debug.logging>
