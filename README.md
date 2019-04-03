# kubernetes-microservices
Microservices running in Docker containers that interact on a Kubernetes platform.

## Springboot-service (under development)
A Spring Boot Server that exposes endpoints, returning json objects.

### Build
```
$ mvn install dockerfile:build
$ docker login -username USERNAME
$ docker push USERNAME/rest-service
```
### Deploy
```
$ kubectl create -f application.yml
```

## Angular-service (under development)
