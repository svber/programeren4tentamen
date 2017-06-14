var config = require('./config.json');
var express = require('express');
var app = express();

app.set('PORT', config.webPort);

app.get('/', function(request, response) {
 response.send('Hello Avans!');
})
app.get('/about', function(request, response) {
 response.send('Written by <jouw naam hier invullen>');
})
app.get('/json', function(request, response) {
 response.json({
 		'some_name': 'Value',
 		'an_array_of_objects': [
	 		{
	 		'another_name': 'Another value',
	 		'a_further_name': "A further value"
	 		},
		 	{'yet_another_name': 'Yet another value'}
 		],
 		'some_boolean': true,
 		'some_integer': 42,
		'array_of_ints': [
		 	2, 3, 5, 7, 11, 13
	 	],
	 	'array_of_strings': [
		 	"twee", "drie", "vijf", "zeven"
 		]
 	})
})
app.post('/', function(request, response) {
 response.send('Hello Avans, POST request received!');
})
app.put('/', function(request, response) {
 response.send('Hello Avans, PUT request received!');
})


app.all('*', function(request, response, next) {
 console.log(request.method + " " + request.url);
 next();
})

app.use('/api/v1', require('./routes/routes_api_v1'));
app.use('/api/v2', require('./routes/routes_api_v2'));


app.all('*', function(request, response) {
 response.status(404);
 response.send('404 - Not found');
})

var port = process.env.PORT || app.get('PORT');
app.listen(port, function() {
 console.log('Server app is listening on port' + port);
})