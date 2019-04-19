# kubernetes-microservices
Microservices running in docker containers that interact on a Kubernetes platform. Services are listed in `services/` and a docker image will deploy to docker.io according to its respective Dockerfile. The service template is,

```
service/
  src/
  docs/
  Dockerfile
  application.yml
  README.md
  deploy.sh
```

#### Todo
-[ ] implement MongoDB-service
-[ ] add groovy tests in Springboot-service
-[ ] deploy with [Openshift](https://www.openshift.com/)
-[ ] build with [Architect](https://github.com/Skatteetaten/architect)

## Components
![Component diagram](/docs/Component_diagram.png)
## Deployment
![Component diagram](/docs/Deployment_diagram.png)

## Deploy
This will build docker images, and deploy applications and services when prompted.
```
$ ./deploy.sh
```
### Redeploy
This will delete applications, services and deploy them again. Useful when new docker images are pushed to the registry.
```
$ ./redeploy.sh
```


