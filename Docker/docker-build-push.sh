#!/usr/bin/env sh

# author: Gary A. Stafford
# site: https://programmaticponderings.com
# license: MIT License

#set -ex

./gradlew clean build

docker build -f Docker/Dockerfile --no-cache -t garystafford/wp-es-demo:latest .

# Docker Hub
docker push garystafford/wp-es-demo:latest

# GCP Container Registry
docker tag garystafford/wp-es-demo:latest gcr.io/wp-search-bot/wp-es-demo:latest
docker push gcr.io/wp-search-bot/wp-es-demo:latest

kubectl replace --force -f gke/wp-es-demo.yaml

# docker run --name wp-es-demo -p 8080:8080 -d garystafford/wp-es-demo:latest
# docker logs $(docker ps | grep wp-es-demo | awk '{print $NF}') --follow
