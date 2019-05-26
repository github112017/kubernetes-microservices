## Springboot-service (under development)
A Spring Boot Server that calls a REST API returning player statistics and currently saves the data in a `List`. The general structure of this project is taken from Skatteetaten's [openshift reference application](https://github.com/Skatteetaten/openshift-reference-springboot-server).
It exposes the following endpoints,
### PUT player
```
// PUT
api/player
HTTP/1.1 200
Content-Length: 13
Content-Type: text/plain;charset=UTF-8
Date: Mon, 29 Apr 2019 10:38:26 GMT
  
  "Player loaded"
  
```
### GET player
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
### GET players
 ```
//GET
api/players
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Mon, 29 Apr 2019 19:32:30 GMT
Transfer-Encoding: chunked

{
    "180744178": {
        "country": "Norway",
        "game": "bf4",
        "id": 180744178,
        "name": "player_01",
        "rank": 140
    },
    "352434725": {
        "country": "Republic of Ireland",
        "game": "bf4",
        "id": 352434725,
        "name": "player_02",
        "rank": 140
    }
}
 ```
### DELETE players
```
// DELETE
api/players
HTTP/1.1 200
Content-Length: 12
Content-Type: text/plain;charset=UTF-8
Date: Mon, 29 Apr 2019 10:38:26 GMT
  
  cleared players
  
```

### Request layout
![Activity diagram](docs/Activity_diagram.png)

### Log

### Docker
Building and deploying a docker image is done with,
```
$ ./deploy.sh
```
