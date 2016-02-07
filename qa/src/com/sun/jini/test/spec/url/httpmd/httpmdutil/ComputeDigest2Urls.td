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

testClass=ComputeDigest2Urls
testCategories=url_spec,url

# ComputeDigest2Urls Test Case names
ComputeDigest2Urls.testCases=computeDigest2UrlsSameFile computeDigest2UrlsSameHttp computeDigest2UrlsSameFileHttp computeDigest2UrlsSameContentsFile computeDigest2UrlsSameContentsFileHttp computeDigest2UrlsSameContentsHttp computeDigest2UrlsDiffFile computeDigest2UrlsDiffHttp

ComputeDigest2Urls.SrcDir=${testClassServer.dir}
ComputeDigest2Urls.Port=${testClassServer.port}

#        computeDigest2UrlsSameFile
computeDigest2UrlsSameFile.FileName1=qa1-httpmd.jar
computeDigest2UrlsSameFile.FileName2=qa1-httpmd.jar
computeDigest2UrlsSameFile.Protocol1=file
computeDigest2UrlsSameFile.Protocol2=file

#        computeDigest2UrlsSameHttp
computeDigest2UrlsSameHttp.FileName1=qa1-httpmd.jar
computeDigest2UrlsSameHttp.FileName2=qa1-httpmd.jar
computeDigest2UrlsSameHttp.Protocol1=http
computeDigest2UrlsSameHttp.Protocol2=http

#        computeDigest2UrlsSameFileHttp
computeDigest2UrlsSameFileHttp.FileName1=qa1-httpmd.jar
computeDigest2UrlsSameFileHttp.FileName2=qa1-httpmd.jar
computeDigest2UrlsSameFileHttp.Protocol1=file
computeDigest2UrlsSameFileHttp.Protocol2=http

#        computeDigest2UrlsSameContentsFile
computeDigest2UrlsSameContentsFile.FileName1=qa1-httpmd.jar
computeDigest2UrlsSameContentsFile.FileName2=qa1-httpmd-same.jar
computeDigest2UrlsSameContentsFile.Protocol1=file
computeDigest2UrlsSameContentsFile.Protocol2=file

#        computeDigest2UrlsSameContentsHttp
computeDigest2UrlsSameContentsHttp.FileName1=qa1-httpmd.jar
computeDigest2UrlsSameContentsHttp.FileName2=qa1-httpmd-same.jar
computeDigest2UrlsSameContentsHttp.Protocol1=http
computeDigest2UrlsSameContentsHttp.Protocol2=http

#        computeDigest2UrlsSameContentsFileHttp
computeDigest2UrlsSameContentsFileHttp.FileName1=qa1-httpmd.jar
computeDigest2UrlsSameContentsFileHttp.FileName2=qa1-httpmd-same.jar
computeDigest2UrlsSameContentsFileHttp.Protocol1=file
computeDigest2UrlsSameContentsFileHttp.Protocol2=http

#        computeDigest2UrlsDiffFile
computeDigest2UrlsDiffFile.FileName1=qa1-httpmd.jar
computeDigest2UrlsDiffFile.FileName2=qa1-httpmd-another.jar
computeDigest2UrlsDiffFile.Protocol1=file
computeDigest2UrlsDiffFile.Protocol2=file
computeDigest2UrlsDiffFile.Expected=non-equal

#        computeDigest2UrlsDiffHttp
computeDigest2UrlsDiffHttp.FileName1=qa1-httpmd.jar
computeDigest2UrlsDiffHttp.FileName2=qa1-httpmd-another.jar
computeDigest2UrlsDiffHttp.Protocol1=http
computeDigest2UrlsDiffHttp.Protocol2=http
computeDigest2UrlsDiffHttp.Expected=non-equal
include0=../../url.properties
