## Node-service (under development)
An ExpressJS server that provides an AngularJS web client. It calls the api endpoints in a `springboot-service`, displaying it as a list in HTML. As such, it essentially works as a proxy server, making the `springboot-service` available.

The ExpressJS server exposes the following endopoints,

```
//PUT
/api/player/:name
HTTP/1.1 200 OK
Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Access-Control-Allow-Origin: *
Connection: keep-alive
Content-Length: 13
Content-Type: text/html; charset=utf-8
Date: Wed, 01 May 2019 19:36:32 GMT
ETag: W/"d-x+6h1FC0JnrTFGZfopJauFC0X9w"
X-Powered-By: Express

Player loaded

//GET
/api/player/:name
HTTP/1.1 200 OK
Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Access-Control-Allow-Origin: *
Connection: keep-alive
Content-Length: 657
Content-Type: application/json; charset=utf-8
Date: Wed, 01 May 2019 19:40:49 GMT
ETag: W/"291-cZrFoOHAIMEKp00SL/LDVUO+QCQ"
X-Powered-By: Express

{
    "body": "{\"country\":\"Republic of Ireland\",\"game\":\"bf4\",\"name\":\"John\",\"rank\":140}",
    "headers": {
        "connection": "close",
        "content-type": "application/json;charset=UTF-8",
        "date": "Wed, 01 May 2019 19:40:49 GMT",
        "transfer-encoding": "chunked"
    },
    "request": {
        "headers": {
            "Content-Type": "application/json"
        },
        "method": "GET",
        "uri": {
            "auth": null,
            "hash": null,
            "host": "springboot-app-service:8081",
            "hostname": "springboot-app-service",
            "href": "http://springboot-app-service:8081/api/player?name=John",
            "path": "/api/player?name=John",
            "pathname": "/api/player",
            "port": "8081",
            "protocol": "http:",
            "query": "name=John",
            "search": "?name=John",
            "slashes": true
        }
    },
    "statusCode": 200
}

//GET
/api/players
HTTP/1.1 200 OK
Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Access-Control-Allow-Origin: *
Connection: keep-alive
Content-Length: 859
Content-Type: application/json; charset=utf-8
Date: Wed, 01 May 2019 19:43:30 GMT
ETag: W/"35b-tR4nC2LoaVIYEDylTHYD2DT/AHg"
X-Powered-By: Express

{
    "body": "{\"352434725\":{\"country\":\"Republic of Ireland\",\"game\":\"bf4\",\"name\":\"Jazy2\",\"rank\":140,\"id\":352434725},\"180744178\":{\"country\":\"null\",\"game\":\"bf4\",\"name\":\"MedicNL\",\"rank\":140,\"id\":180744178},\"171933431\":{\"country\":\"null\",\"game\":\"bf4\",\"name\":\"John\",\"rank\":0,\"id\":171933431}}",
    "headers": {
        "connection": "close",
        "content-type": "application/json;charset=UTF-8",
        "date": "Wed, 01 May 2019 19:43:30 GMT",
        "transfer-encoding": "chunked"
    },
    "request": {
        "headers": {
            "Content-Type": "application/json"
        },
        "method": "GET",
        "uri": {
            "auth": null,
            "hash": null,
            "host": "springboot-app-service:8081",
            "hostname": "springboot-app-service",
            "href": "http://springboot-app-service:8081/api/players",
            "path": "/api/players",
            "pathname": "/api/players",
            "port": "8081",
            "protocol": "http:",
            "query": null,
            "search": null,
            "slashes": true
        }
    },
    "statusCode": 200
}

// DELETE
/api/players
HTTP/1.1 200 OK
Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Access-Control-Allow-Origin: *
Connection: keep-alive
Content-Length: 558
Content-Type: application/json; charset=utf-8
Date: Wed, 01 May 2019 19:45:06 GMT
ETag: W/"22e-ij+++A/Ey4YsIa2HJS+1P5BeHro"
X-Powered-By: Express

{
    "body": "cleared players",
    "headers": {
        "connection": "close",
        "content-length": "15",
        "content-type": "text/plain;charset=UTF-8",
        "date": "Wed, 01 May 2019 19:45:06 GMT"
    },
    "request": {
        "headers": {
            "Content-Type": "application/json",
            "content-length": 0
        },
        "method": "DELETE",
        "uri": {
            "auth": null,
            "hash": null,
            "host": "springboot-app-service:8081",
            "hostname": "springboot-app-service",
            "href": "http://springboot-app-service:8081/api/players",
            "path": "/api/players",
            "pathname": "/api/players",
            "port": "8081",
            "protocol": "http:",
            "query": null,
            "search": null,
            "slashes": true
        }
    },
    "statusCode": 200
}
```
### Request layout (outdated, TODO : create a new layout)
![Activity diagram](docs/Activity_diagram.png)

### Debug
Accessing the node debugger on the ExpressJS server can be done with,
```
$ kubectl exec -it {depl-name} bash
$ node inspect server.js
```

### Log
Accessing the node log on the ExpressJS server can be done with,
```
$ kubectl exec -it {depl-name} bash
$ tail -f /tmp/node.log
```

Ideally it should be implemented with [log4js](https://github.com/log4js-node/log4js-node).

### Docker
Building and deploying a docker image is done with,
```
$ ./deploy.sh
```




