#set($hash = '#')
${hash} ${artifactId}

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
checkout and run the OpenAPI client skeleton project provided in https://github.com/dieschnittstelle/org.dieschnittstelle.ess.openapi.client.skeleton, accessing the description at http://localhost:${tomeeHttpPort}/api/openapi or, if the containerised service is used, at http://localhost:${exposedContainerisedTomeeHttpPort}/api/openapi


