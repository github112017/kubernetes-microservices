## Node-service (under development)
An ExpressJS server that provides an AngularJS web client. It calls the api endpoints in a `springboot-service`, displaying it as a list in HTML.
The ExpressJS server exposes the following endopoints,

```
/api/load_player/:name
/api/get_player/:name
/api/get_players
```

![Activity diagram](/docs/Activity_diagram.png)

