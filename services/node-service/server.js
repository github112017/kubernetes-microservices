'use strict';

const express = require('express')
const request = require('request');
const path = require("path")

const PORT = 8000
const HOST = '0.0.0.0'

const app = express()

app.use(express.static(path.join(__dirname, 'public')))
app.use("/css", express.static(__dirname + '/public/css'))
app.use("/js", express.static(__dirname + '/public/js'))
app.use("/node_modules", express.static(__dirname + '/node_modules'))

/* Enable CORS */
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

  next();
})

/* Send static html file, adds dependencies (?) 
app.get('/api/ui', function (req, res) {
	res.sendFile(__dirname + '/public/index.html')
})
*/

/* Load player, /api/load_player/{name} */
app.get('/api/load_player/:name', (req, res) => {
	var name = req.params.name
	var url = 'springboot-app-service:8081/api/load_player?name=' + name
  
  console.log(":: BEFORE ::")
	request(url, function (error, response, body) {
    if (!error && response.statusCode == 200) {
      console.log(":: INSIDE ::")
      res.json(response)
    }
		else {
      console.log(":: ERROR ::")
			console.log(error)
		}
	})
})


/* Get player, /api/player/{name} */
app.get('/api/get_player/:name', (req, res) => {
  var name = req.params.name
  var url = 'springboot-app-service:8081/api/get_player?name=' + name
  
  console.log(":: BEFORE ::")
  request(url, function (error, response, body) {
    if (!error && response.statusCode == 200) {
      res.json(response)
      console.log(":: INSIDE ::")
    } 
    else {
      console.log(":: ERROR ::")
      console.log(error)
    }
  })
})

/* Get player list, returns json - list of players */
app.get('/api/get_players', (req, res) => {
	var url = 'springboot-app-service:8081/api/get/players'

	request(url, function (error, response, body) {
		if (response.statusCode === 200) {
			res.json(response)
		} 
		else if (error) {
			console.log(error)
		}
	})
})

app.listen(PORT, HOST)

console.log(`Running on http://${HOST}:${PORT}`)