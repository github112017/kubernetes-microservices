#!/bin/bash
echo "[log] building node-service..."

docker build -t sasoria/node-service .
docker login -username sasoria
docker push sasoria/node-service

echo "[log] node service built"

# run docker container
# docker run -p 8000:8000 sasoria/node-service:latest

