# kubernetes-microservices
Microservices running in Docker containers that interact on a Kubernetes platform.

## Springboot-service (under development)
A Spring Boot Server that exposes endpoints, returning json objects.

### Build
```
$ cd springboot-service
$ bash deploy.sh
```
### Deploy
```
$ kubectl create -f application.yml
```

## Angular-service (under development)

### Build
```
$ cd angular-service
$ bash deploy.sh
```
### Deploy
```
$ kubectl create -f application.yml
```
