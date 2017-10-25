var express = require('express');
var app = express();

// Define the port to run on
app.set('port', 80);

app.get('/', function(req, res){
  res.sendFile(__dirname + '/dist/index.html');
});

app.get('/favicon.ico', function(req, res){
  res.sendFile(__dirname + '/dist/favicon.ico');
});

app.get('/main.e2969e325f8d68684fcb.js', function(req, res){
  res.sendFile(__dirname + '/dist/main.e2969e325f8d68684fcb.js');
});

app.get('/main.e2969e325f8d68684fcb.js.map', function(req, res){
  res.sendFile(__dirname + '/dist/main.e2969e325f8d68684fcb.js.map');
});

app.get('/main.adca5dee1ad7cc6b408ab11982cee251.css.map.', function(req, res){
  res.sendFile(__dirname + '/dist/main.adca5dee1ad7cc6b408ab11982cee251.css.map');
});

app.get('/main.adca5dee1ad7cc6b408ab11982cee251.css', function(req, res){
  res.sendFile(__dirname + '/dist/main.adca5dee1ad7cc6b408ab11982cee251.css');
});

// Listen for requests
var server = app.listen(app.get('port'), function() {
  var port = server.address().port;
});