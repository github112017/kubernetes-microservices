## Springboot-service (under development)
A Spring Boot Server that exposes the following endpoints,
```
/api/load_player
/api/load_players
/api/get_player
/api/get_players
/api/save_players
/api/clear_players
```

It calls a REST API that return player statistics and currently saves the data in a `List`. The general structure of this project is taken from Skatteetaten's [openshift reference application](https://github.com/Skatteetaten/openshift-reference-springboot-server).

### Request layout
![Activity diagram](docs/Activity_diagram.png)

### Log

### Docker
Building and deploying a docker image is done with,
```
$ ./deploy.sh
```
