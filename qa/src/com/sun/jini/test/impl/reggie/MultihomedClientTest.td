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

testClass=MultihomedClientTest
testCategories=lookupservice,lookupservice_impl
testPolicyfile=<url: harness/policy/policy.all>

policy=<url: harness/policy/policy.all>
nameservice=com.sun.jini.qa.reggie,com.sun.jini.qa.reggie
lib-dl=${com.sun.jini.jsk.home}/lib-dl/
toolClassPath=${com.sun.jini.jsk.home}/lib/classserver.jar
reggieClasses=${com.sun.jini.jsk.home}/lib/reggie.jar
reggieConfig=<url:com/sun/jini/test/impl/reggie/resources/reggie.config>
startConfig=<url:com/sun/jini/test/impl/reggie/resources/start.config>
lookupConfig=<url:com/sun/jini/test/impl/reggie/resources/lookupdiscovery.config>
#logging=<file:tests/logging/multihomedClientTestLogging.properties>
logging=${java.util.logging.config.file}
metaInf=<file: lib/qa1-multihomedmetainf.jar>
timeout=10

