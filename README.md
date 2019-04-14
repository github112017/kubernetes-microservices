# kubernetes-microservices
Microservices running in Docker containers that interact on a Kubernetes platform.

## Components
![Component diagram](/docs/Component_diagram.png)
## Deployments
![Component diagram](/docs/Deployment_diagram.png)

## Springboot-service (under development)
A Spring Boot Server that exposes endpoints, returning json objects. It calls a REST API and currently saves the data in a `List`.

## Node-service (under development)
An ExpressJS server that provides an AngularJS web client. It calls the api endpoints in a `springboot-service`, displaying it as a list in HTML.

## Mongodb-service (under development)
- [ ] Not implemented.


