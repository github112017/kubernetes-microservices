#!/bin/bash

function build_docker () {
  read -p "[log] do you want to build and push docker images (y/n)? " -n 1 -r
  echo

  if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "[log] deploying docker images ..."
    ./build-docker
    sleep 2
    echo "[log] docker images have been deployed"
  fi
}

function deploy_kubernetes() {
  read -p "[log] do you want to deploy pods and services to kubernetes (y/n)? " -n 1 -r
  echo

  if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "[log] deploying pods and services ..."
    ./deploy-kubernetes
    sleep 2
    echo "[log] kubernetes pods and services have been deployed"
  fi
}

function start_minikube() {
  if [ $(type -P minikube) ]; then
    minikube stop
    minikube delete
  fi

  minikube start
  echo "[log] minikube is running on $(minikube ip)"
}

function main () {
  build_docker
  start_minikube
  deploy_kubernetes
}

main

# └─────╼ kubectl get service -o wide
# └─────╼ kubectl delete svc node-app-service

# └─────╼ kubectl delete -f application.yml

# this workds with sudo
# echo '1.2.3.4 test' | sudo tee -a /etc/hosts

#IP=$(minikube ip)
#HOSTNAME="minikube"

#echo "minikube is running on $IP"

# Add host to /etc/hosts
#if [ -n "$(grep $HOSTNAME /etc/hosts)" ]; then
#	echo "removing host..."
#fi

#echo "adding host $HOSTNAME to /etc/hosts"
#echo "$IP $HOSTNAME" >> /etc/hosts