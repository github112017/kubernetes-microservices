## Springboot-service (under development)
A Spring Boot Server that exposes the following endpoints,
### PUT
```
// PUT
api/player
HTTP/1.1 200
Content-Length: 13
Content-Type: text/plain;charset=UTF-8
Date: Mon, 29 Apr 2019 10:38:26 GMT
  
  "Player loaded"
  
```
### GET
```
// GET
api/player
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Mon, 29 Apr 2019 10:38:26 GMT
  
  {
    "country": value,
    "game": value,
    "name": value,
    "rank": value
  }
  
 ```
### DELETE
```
// DELTE
HTTP/1.1 200
Content-Length: 12
Content-Type: text/plain;charset=UTF-8
Date: Mon, 29 Apr 2019 10:38:26 GMT
  
  cleared data
  
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
