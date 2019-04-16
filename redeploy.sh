#!/bin/bash
ROOT_DIR=$(pwd)
SPRINGBOOT_DIR=$ROOT_DIR/services/springboot-service
NODE_DIR=$ROOT_DIR/services/node-service

# node
cd $NODE_DIR
echo "[log] deleting node-service..."
kubectl delete -f application.yml

echo "[log] creating node-service..."
sleep 4
kubectl create -f application.yml

# springboot
cd $SPRINGBOOT_DIR
echo "[log] deleting springboot-service..."
kubectl delete -f application.yml

echo "[log] creating springboot-service..."
sleep 4
kubectl create -f application.yml
