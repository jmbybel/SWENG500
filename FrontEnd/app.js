let express = require('express');
let app = express();

// Define the port to run on
app.set('port', 80);

app.get('/', function(req, res){
  res.sendFile(__dirname + '/dist/index.html');
});

app.get('/sensors', function(req, res){
  res.sendFile(__dirname + '/dist/index.html');
});

app.get('/favicon.ico', function(req, res){
  res.sendFile(__dirname + '/dist/favicon.ico');
});

app.get('/main.js', function(req, res){
  res.sendFile(__dirname + '/dist/main.js');
});

app.get('/main.js.map', function(req, res){
  res.sendFile(__dirname + '/dist/main.js.map');
});

app.get('/main.css.map.', function(req, res){
  res.sendFile(__dirname + '/dist/main.css.map');
});

app.get('/main.css', function(req, res){
  res.sendFile(__dirname + '/dist/main.css');
});

// Listen for requests
const server = app.listen(app.get('port'), function() {
  server.address().port;
});
