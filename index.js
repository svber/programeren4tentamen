//
// index.js
//
var http = require('http');
var express = require('express');
var bodyParser = require('body-parser')
var logger = require('morgan');
var db = require('./config/database');
var routes_v1 = require('./api/routes_v1');
var authentication_routes_v1 = require('./api/authentication_routes_v1');
var config = require('./config/config');
var expressJWT = require('express-jwt');

var app = express();

// bodyParser zorgt dat we de body uit een request kunnen gebruiken,
// hierin zit de inhoud van een POST request.
app.use(bodyParser.urlencoded({ 'extended': 'true' })); // parse application/x-www-form-urlencoded
app.use(bodyParser.json()); // parse application/json
app.use(bodyParser.json({ type: 'application/vnd.api+json' })); // parse application/vnd.api+json as json

// Beveilig alle URL routes, tenzij het om /login of /register gaat.
app.use('/api/v1/rentals',expressJWT({
    secret: config.secretkey
}));

// configureer de app
app.set('port', (process.env.PORT | config.webPort));
app.set('env', (process.env.ENV | 'development'))

// Installeer Morgan als logger
app.use(logger('dev'));

// Installeer de routers
app.use('/api/v1', authentication_routes_v1);
app.use('/api/v1', routes_v1);

// Errorhandler voor express-jwt errors
// Wordt uitgevoerd wanneer err != null; anders door naar next().
app.use(function(err, req, res, next) {
    // console.dir(err);
    var error = {
        message: err.message,
        code: err.code,
        name: err.name,
        status: err.status
    }
    res.status(401).send(error);
});

// Fallback - als geen enkele andere route slaagt wordt deze uitgevoerd. 
app.use('*', function(req, res) {
    res.status(400);
    res.json({
        'error': 'Deze URL is niet beschikbaar.'
    });
});

// Installatie klaar; start de server.
app.listen(process.env.PORT || 3000, function() {
    console.log('De server luistert op port ' + app.get('port'));
});

// Voor testen met mocha/chai moeten we de app exporteren.
module.exports = app;