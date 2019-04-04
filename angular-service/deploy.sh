#!bin/bash#!bin/bash
docker build -t sasoria/angular-service .
docker login -username sasoria
docker push sasoria/angular-service
# see <http://172.17.0.2:8000/>
docker run -p 8000:8000 sasoria/angular-service:latest

