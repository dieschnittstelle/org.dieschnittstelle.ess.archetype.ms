#set($hash = '#')
${hash} ${artifactId}

${hash}${hash} Set JAVA_HOME environment variable
in the console/terminal set JAVA_HOME to the semeru 11 JDK, e.g.:

export JAVA_HOME=/Users/USERNAME/Library/Java/JavaVirtualMachines/semeru-11.0.17/Contents/Home

Alternatively, set the jdk in the "Project Structure" of IDEA and use the Maven tool window for running the mvn commands.

${hash}${hash} Build
run inside the project directory:

mvn clean install

${hash}${hash} Run
run inside the project directory:

cd target
java -jar ${artifactId}-1.0-SNAPSHOT-exec.jar

${hash}${hash} Test
run Test${entitytypeName} in a JUnit environment, e.g. within your IDE

${hash}${hash} Access GUI
in browser, open: 

http://localhost:${tomeeHttpPort}/

${hash}${hash} Containerise
run inside the project directory:

cd containerise
sh ./containerise.sh

${hash}${hash} Access containerised GUI
in browser, open:
http://localhost:${exposedContainerisedTomeeHttpPort}/

${hash}${hash} Access API using the provided OpenAPI description
checkout and run the OpenAPI client skeleton project provided in https://github.com/dieschnittstelle/org.dieschnittstelle.ess.openapi.client.skeleton, accessing the description at http://localhost:${tomeeHttpPort}/openapi or, if the containerised service is used, at http://localhost:${exposedContainerisedTomeeHttpPort}/openapi, adapting the names of the entities and crud operations, where necessary. If default settings for project creation were used, no changes of the client code will be necessary. 


