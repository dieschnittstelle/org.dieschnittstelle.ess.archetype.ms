# Base Java image
FROM ibm-semeru-runtimes:open-11-jdk

EXPOSE ${tomeeHttpPort}

COPY /${artifactId}-1.0-SNAPSHOT-exec.jar /local/${artifactId}-1.0-SNAPSHOT-exec.jar

USER root

CMD ["java","-jar","/local/${artifactId}-1.0-SNAPSHOT-exec.jar"]
