'use strict';

const express = require('express')
const request = require('request');
const path = require("path")
const PORT = 8000
const HOST = '0.0.0.0'

const app = express()

app.use(express.static(path.join(__dirname, 'src')))
app.use("/css", express.static(__dirname + '/src/css'))
app.use("/js", express.static(__dirname + '/src/js'))
app.use("/node_modules", express.static(__dirname + '/node_modules'))

/* Enable CORS */
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

  next();
})

/* Load player, /api/player/{name} */
app.put('/api/player/:name', (req, res) => {
	var name = req.params.name
	var url = 'http://springboot-app-service:8081/api/player?name='+name
  
  var load_req = {
    host: url,
    method: 'PUT',
    headers: {
      'Content-Type': 'text/plain'
    }
  }
  console.error("Before loading")

  request(url, load_req, load_callback) 

  console.error("After loading")

  function load_callback(error, response, body) {
    if (!error && response.statusCode == 200) {
      res.send("Player loaded")
    }
    else {
      res.send(error)
      console.log(error)
      console.error(error)
    }
  }
})

/* Get player, /api/player/{name} */
app.get('/api/player/:name', (req, res) => {
  var name = req.params.name
  var url = 'http://springboot-app-service:8081/api/player?name='+name
  
   var get_req = {
    host: url,
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  }
  request(url, get_req, get_callback)

  function get_callback (error, response, body) {
    if (!error && response.statusCode == 200) {
      res.json(response)
    }
    else {
      res.send(error)
      console.error(error)
      console.log(error)
    }
  }
})

/* Get player, /api/player/{name} */
app.delete('/api/players', (req, res) => {
  // var name = req.params.name
  var url = 'http://springboot-app-service:8081/api/players'
  
   var get_req = {
    host: url,
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    }
  }
  request(url, get_req, get_callback)

  function get_callback (error, response, body) {
    if (!error && response.statusCode == 200) {
      res.json(response)
    }
    else {
      res.send(error)
      console.error(error)
      console.log(error)
    }
  }
})

/* Get player list, returns json - list of players 
app.get('/api/get_players', (req, res) => {
	var url = 'http://springboot-app-service:8081/api/get_players'

	request(url, function (error, response, body) {
		if (response.statusCode === 200) {
			res.json(response)
		} 
		else if (error) {
			console.log(error)
		}
	})
})
*/

app.listen(PORT, HOST)

console.log(`Running on http://${HOST}:${PORT}`)