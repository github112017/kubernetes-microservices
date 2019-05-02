# kubernetes-microservices
Microservices running in docker containers that interact synchronously on a Kubernetes platform. Services are listed in `services/` and a docker image will deploy to docker.io according to its respective Dockerfile. The service template is,

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
- [ ] implement mongodb-service
- [ ] add groovy tests to springboot-service
- [ ] deploy with [Openshift](https://www.openshift.com/)
- [ ] build with [Architect](https://github.com/Skatteetaten/architect)
- [ ] install [Jenkins](https://jenkins.io/)

[02.06.2019] I'm currently considering deploying with FINN.no's [fiaas-deploy-daemon](https://github.com/fiaas/fiaas-deploy-daemon). Sadly, I get a LivenessCheck error when I deploy applications to Kubernetes.

## Proposed application architecture
![Component diagram](/docs/Application_architecture.png)
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


