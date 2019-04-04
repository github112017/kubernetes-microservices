#!bin/bash#!bin/bash
docker build -t sasoria/angular-service .
# docker login -username sasoria
# docker push sasoria/angular-service
docker run -p 49160:8000 sasoria/angular-service:latest

