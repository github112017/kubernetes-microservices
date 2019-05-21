#!/bin/bash

function build_docker () {
  read -p "[msg] do you want to build and push docker images (y/n)? " -n 1 -r
  echo

  if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "[log] deploying docker images ..."
    ./scripts/build-docker
    echo "[log] docker images have been deployed"
  fi
}

function deploy_kubernetes() {
  read -p "[msg] do you want to deploy pods and services to kubernetes (y/n)? " -n 1 -r
  echo

  if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "[log] deploying pods and services ..."
    ./scripts/deploy-kubernetes
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
  # start_minikube
  deploy_kubernetes
}

main

#IP=$(minikube ip)
#HOSTNAME="minikube"

# Add host to /etc/hosts
#if [ -n "$(grep $HOSTNAME /etc/hosts)" ]; then
#	echo "removing host..."
#echo "adding host $HOSTNAME to /etc/hosts"
#echo "$IP $HOSTNAME" >> /etc/hosts
#fi
