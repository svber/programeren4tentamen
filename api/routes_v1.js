
var express = require('express');
var router = express.Router();
var db = require('../config/database')

router.post('/login', function(request, response){

});

router.post('/register', function(request, response){

});

router.get('/films?offset=:start&count=:nummer', function(request, response){

});

router.get('/films/:filmid', function(request, response){

});

router.get('/rentals/:userid', function(request, response){

});

router.post('/rentals/:userid/:inventoryid', function(request, response){

});

router.put('/rentals/:userid/:inventoryid', function(request, response){

});

router.delete('/rentals/:userid/:inventoryid', function(request, response){

});
module.exports = router;