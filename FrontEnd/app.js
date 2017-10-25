var express = require('express');
var app = express();

// Define the port to run on
app.set('port', 3000);

app.get('/', function(req, res){
  res.sendFile(__dirname + '/dist/index.html');
});

app.get('/favicon.ico', function(req, res){
  res.sendFile(__dirname + '/dist/favicon.ico');
});

app.get('/main.50ee8b3c63b608a07e75.js', function(req, res){
  res.sendFile(__dirname + '/dist/main.50ee8b3c63b608a07e75.js');
});

app.get('/main.50ee8b3c63b608a07e75.js.map', function(req, res){
  res.sendFile(__dirname + '/dist/main.50ee8b3c63b608a07e75.js.map');
});

app.get('/main.adca5dee1ad7cc6b408ab11982cee251.css', function(req, res){
  res.sendFile(__dirname + '/dist/main.adca5dee1ad7cc6b408ab11982cee251.css');
});

app.get('/main.adca5dee1ad7cc6b408ab11982cee251.css.map', function(req, res){
  res.sendFile(__dirname + '/dist/main.adca5dee1ad7cc6b408ab11982cee251.css.map');
});

// Listen for requests
var server = app.listen(app.get('port'), function() {
  var port = server.address().port;
});