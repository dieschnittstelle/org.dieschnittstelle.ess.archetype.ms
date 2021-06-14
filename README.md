# org.dieschnittstelle.ess.archetype.ms

## Build the archetype project
from command line or in IDE run inside the project directory: 

mvn clean install

## Create a new project from the archetype
from command line run: 

mvn archetype:generate -DarchetypeGroupId=org.dieschnittstelle.ess -DarchetypeArtifactId=ess-mip-quickstart -DarchetypeVersion=1.0-SNAPSHOT -DarchetypeCatalog=local

when asked to "Confirm properties configuration" either input Y in order to take over the defaults or N in order to specify own values (responding with the return key will take over the offered default values) 

## Build and run the new project

see the README.md file in the project folder created by the previous command.

  



