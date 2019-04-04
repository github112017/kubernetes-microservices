#!bin/bash
docker build -t sasoria/angular-service .
docker login -username sasoria
docker push sasoria/angular-service
