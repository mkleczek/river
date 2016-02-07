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

testClass=HashCode
testCategories=url_spec,url

# HashCode Test Case names
HashCode.testCases=hashCodeSameURL hashCodeComments hashCodeCapitalLetters hashCodeAlgorithm

#        hashCodeSameURL
hashCodeSameURL.Url1=httpmd://localhost/file.jar;md5=aaaaaaa
hashCodeSameURL.Url2=${hashCodeSameURL.Url1}

#        hashCodeComments
hashCodeComments.Url1=httpmd://localhost/file.jar;md5=aaaaaaa
hashCodeComments.Url2=httpmd://localhost/file.jar;md5=aaaaaaa,comments

#        hashCodeCapitalLetters
hashCodeCapitalLetters.Url1=httpmd://localhost/file.jar;md5=AAAAAAA
hashCodeCapitalLetters.Url2=httpmd://localhost/file.jar;md5=aaaaaaa

#        hashCodeAlgorithm
hashCodeAlgorithm.Url1=httpmd://localhost/file.jar;MD5=aaaaaaa
hashCodeAlgorithm.Url2=httpmd://localhost/file.jar;md5=aaaaaaa
include0=../../url.properties
