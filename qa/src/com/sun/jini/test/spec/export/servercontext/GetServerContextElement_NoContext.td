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

testClass=GetServerContextElement_NoContext
testCategories=export_spec,spec
testClasspath=<file:lib/qa1-export-servercontext-tests-null.jar>$:<harnessJar>$:<testJar>$:${com.sun.jini.jsk.home}$/lib$/jsk-platform.jar$:${com.sun.jini.jsk.home}$/lib$/jsk-lib.jar
com.sun.jini.qa.harness.runkitserver=false 
com.sun.jini.qa.harness.runjiniserver=false
