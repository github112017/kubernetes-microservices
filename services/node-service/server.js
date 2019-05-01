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
  
  var req = {
    host: url,
    method: 'PUT',
    headers: {
      'Content-Type': 'text/plain'
    }
  }

  request(url, req, callback) 

  function callback(error, response, body) {
    if (!error && response.statusCode == 200) {
      res.send("Player loaded")
    }
    else {
      console.error(error)
      next(error)
    }
  }
})

/* Get player, /api/player/{name} */
app.get('/api/player/:name', (req, res) => {
  var name = req.params.name
  var url = 'http://springboot-app-service:8081/api/player?name='+name
  
   var req = {
    host: url,
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  }
  request(url, req, callback)

  function callback (error, response, body) {
    if (!error && response.statusCode == 200) {
      res.json(response)
    }
    else {
      console.error(error)
      next(error)
    }
  }
})

/* Delete players, /api/players */
app.delete('/api/players', (req, res) => {
  var url = 'http://springboot-app-service:8081/api/players'
  
   var req = {
    host: url,
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    }
  }
  request(url, req, callback)

  function callback (error, response, body) {
    if (!error && response.statusCode == 200) {
      res.json(response)
    }
    else {
      console.error(error)
      next(error)
    }
  }
})

/* GET player list */
app.get('/api/players', (req, res) => {
	var url = 'http://springboot-app-service:8081/api/players'

	var req = {
    host: url,
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  }
  request(url, req, callback)

  function callback (error, response, body) {
    if (!error && response.statusCode == 200) {
      res.json(response)
    }
    else {
      console.error(error)
      next(error)
    }
  }
})

/* DELETE player list */
app.delete('/api/players', (req, res) => {
  var url = 'http://springboot-app-service:8081/api/players'

  var req = {
    host: url,
    method: 'DELETE',
    headers: {
      'Content-Type': 'text/plain'
    }
  }
  request(url, req, callback)

  function callback (error, response, body) {
    if (!error && response.statusCode == 200) {
      res.json(response)
    }
    else {
      console.error(error)
      next(error)
    }
  }
})

app.listen(PORT, HOST)

console.log(`Running on http://${HOST}:${PORT}`)