cp ./../target/${artifactId}-1.0-SNAPSHOT-exec.jar .

docker build -t ${groupId}/${artifactId}  .

docker stop ${artifactId}
docker rm ${artifactId}

docker run -itd -p ${exposedContainerisedTomeeHttpPort}:${tomeeHttpPort} --name ${artifactId} ${groupId}/${artifactId}


