#!/usr/bin/env bash

./gradlew clean build
docker build -f Docker/Dockerfile --no-cache -t garystafford/wp-es-demo:latest .
docker push garystafford/wp-es-demo:latest

# docker run --name wp-es-demo -p 8080:8080 -d garystafford/wp-es-demo:latest
# docker logs $(docker ps | grep wp-es-demo | awk '{print $NF}') --follow
