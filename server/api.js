
var express = require('express');
var router = express.Router();
var path = require('path');

router.post('/login', function(request, response){

});

router.post('/register', function(request, response){

});

router.get('/films?offset=:start&count=:nummer', function(request, response){

});

router.get('/films/:filmid', function(request, response){
    res.contentType('application/json');

    db.query('SELECT * FROM film WHERE ', function(error, rows, fields) {
        if (error) {
            res.status(401).json(error);
        } else {
            res.status(200).json({ result: rows });
        };
    });
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