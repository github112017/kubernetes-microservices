#!/bin/bash
mvn install dockerfile:build
docker login -username sasoria
docker push sasoria/rest-service
