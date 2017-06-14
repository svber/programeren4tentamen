// API version 2
var express = require('express');
var router = express.Router();
var path = require('path');
var recipes = require('../recepies.js');

router.get('/info', function(request, response) {
 response.status(200);
 response.json({
 "description": "Recipes"
 });
});

router.get('/recipes', function(request,response){
	response.json(recipes)
});

router.get('/recipes/:id', function(request,response){
	var id = request.params.id || '';
	var recipe = recipes[id];
	response.json(recipe);
});

module.exports = router;