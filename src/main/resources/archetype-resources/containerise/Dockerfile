# Base Java image
FROM williamyeh/java8

EXPOSE ${tomeeHttpPort}

COPY /${artifactId}-1.0-SNAPSHOT-exec.jar /local/${artifactId}-1.0-SNAPSHOT-exec.jar

USER root

CMD ["java","-jar","/local/${artifactId}-1.0-SNAPSHOT-exec.jar"]
