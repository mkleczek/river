#!/usr/bin/env groovy
/*
 * Copyright to the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

def cli = new CliBuilder(usage:'deploy_river.groovy',
                          header:'Options:')
cli.help('print this message')
cli.install('install locally, don\'t deploy to repository')
cli.dryrun('dryrun - just show the actions, but don\'t do them')
    
println("Args are $args")

def options = cli.parse(args)

if (options.hasOption("help")) {
    println(cli.usage())
    return ""
}

def rootDir = ".."
def repositoryId="apache.releases.https"
def repositoryURL="https://repository.apache.org/service/local/staging/deploy/maven2"
// To test locally, you can use a file url, as below...
//String repositoryURL="file:///Users/trasukg/mvn-repo"

def passphrase
if (!options.hasOption("install")) {
    passphrase=System.console().readLine "Enter your GPG passphrase: "
}
        
["net.jini:jsk-platform":"lib",
 "net.jini:jsk-lib":"lib",
 "net.jini:jsk-dl":"lib-dl",
 "net.jini:jsk-resources":"lib",
 "net.jini:jsk-policy":"lib-ext",
 "net.jini.lookup:serviceui":"lib",
 "org.apache.river:fiddler":"lib",
 "org.apache.river:fiddler-dl":"lib-dl",
 "org.apache.river:mahalo":"lib",
 "org.apache.river:mahalo-dl":"lib-dl",
 "org.apache.river:mercury":"lib",
 "org.apache.river:mercury-dl":"lib-dl",
 "org.apache.river:norm":"lib",
 "org.apache.river:norm-dl":"lib-dl",
 "org.apache.river:outrigger":"lib",
 "org.apache.river:outrigger-dl":"lib-dl",
 "org.apache.river:reggie":"lib",
 "org.apache.river:reggie-dl":"lib-dl",
 "org.apache.river:start":"lib",
 "org.apache.river:tools":"lib"
].each {artifact, subDir ->
    
    def parts = artifact.split(":")
    def gId = parts[0]
    def aId = parts[1]
    def dir = rootDir+"/"+subDir

    def deployCommand
    
    if (options.hasOption("install")) {
        /*    mvn install:install-file -Dfile=<path-to-file> -DgroupId=<group-id> \
            -DartifactId=<artifact-id> -Dversion=<version> -Dpackaging=<packaging>
        */
        deployCommand = [\
            "mvn",
            "install:install-file",
            "-DpomFile=${aId}.pom",
            "-Dfile=${dir}/${aId}.jar"
        ]
    } else {
        deployCommand = [\
            "mvn",
            "gpg:sign-and-deploy-file",
            "-DpomFile=${aId}.pom",
            "-Dfile=${dir}/${aId}.jar",
            "-DrepositoryId=${repositoryId}",
            "-Durl=${repositoryURL}",
            "-Dgpg.useAgent=false",
            "-Dgpg.keyname=gtrasuk@apache.org",
            "-Dgpg.passphrase=${passphrase}"
        ]

    }
    /* First make sure the jar file contains the LICENSE file */
    if (!options.hasOption('dryrun')) {
        def p="jar uvf ${dir}/${aId}.jar -C .. LICENSE".execute()
        p.consumeProcessOutputStream(System.out)
        p.consumeProcessErrorStream(System.err)
        p.waitFor()
        
        Process process = deployCommand.execute()
        process.consumeProcessOutputStream(System.out)
        process.consumeProcessErrorStream(System.err)
        process.waitFor()
    } else {
        println deployCommand
    }
}
