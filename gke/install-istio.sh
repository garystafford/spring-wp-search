#!/usr/bin/env sh

# author: Gary A. Stafford
# site: https://programmaticponderings.com
# license: MIT License
# purpose: Install Istio on GKE cluster

ISTIO_HOME="/Applications/istio-1.0.2"

kubectl apply -f $ISTIO_HOME/install/kubernetes/helm/helm-service-account.yaml
helm init --service-account tiller
sleep 15
helm install $ISTIO_HOME/install/kubernetes/helm/istio \
  --name istio \
  --namespace istio-system

# helm delete istio
