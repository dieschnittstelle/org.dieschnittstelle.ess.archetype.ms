cp ./../target/${artifactId}-1.0-SNAPSHOT-exec.jar .

docker build -t ${groupId}/${artifactId}  .

docker stop ${artifactId}
docker rm ${artifactId}

docker run -itd -p 7071:7081 --name ${artifactId} ${groupId}/${artifactId}


