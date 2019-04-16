# kubernetes-microservices
Microservices running in docker containers that interact on a Kubernetes platform. Services are listed in `services/` and a docker image will deploy to docker.io according to its respective Dockerfile. The service template is,

```
service/
  src/
  Dockerfile
  application.yml
```

## Components
![Component diagram](/docs/Component_diagram.png)
## Deployment
![Component diagram](/docs/Deployment_diagram.png)

### Deploy
```
$ ./deploy.sh
```
#### Redeploy
```
$ ./redeploy.sh
```


