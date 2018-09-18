#!/usr/bin/env sh

# author: Gary A. Stafford
# site: https://programmaticponderings.com
# license: MIT License

IMAGE_REPOSITORY=garystafford
IMAGE_NAME=wp-es-demo
GCP_PROJECT=wp-search-bot


# Build Spring Boot app
./gradlew clean build

# Build Docker file
docker build -f Docker/Dockerfile --no-cache -t ${IMAGE_REPOSITORY}/${IMAGE_NAME}:latest .

# Push image to Docker Hub
docker push ${IMAGE_REPOSITORY}/${IMAGE_NAME}:latest

# Push image to GCP Container Registry
docker tag ${IMAGE_REPOSITORY}/${IMAGE_NAME}:latest gcr.io/${GCP_PROJECT}/${IMAGE_NAME}:latest
docker push gcr.io/${GCP_PROJECT}/${IMAGE_NAME}:latest

# Re-deploy Workload (containerized app) to GKE
kubectl replace --force -f gke/${IMAGE_NAME}.yaml