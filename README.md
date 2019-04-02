# kubernetes-microservices
Microservices running in Docker containers that interact on a Kubernetes platform.

## rest-service (under development)
A Spring Boot Server that exposes endpoints, returning json objects.

### build docker image
```
$ mvn install dockerfile:build
$ mvn dockerfile:push (not tested)
```

## web-service
