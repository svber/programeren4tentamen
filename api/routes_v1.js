
var express = require('express');
var router = express.Router();
var db = require('../config/database')

router.post('/login', function(request, response){

// Geeft een geldig token wanneer de gebruiker 
// (customer) is ingelogd; anders
// een duidelijke foutmelding

});

router.post('/register', function(request, response){

});

router.get('/films?offset=:start&count=:nummer', function(request, response){

/*
Geeft alle informatie van de gevraagde
films.Offset en count kunnen als opties
worden gegeven. Offset is het startpunt;
count is het aantal films vanaf de
offset. Voorbeeld:
/api/v1/films?offset=50&count=20 retourneert
20 films vanaf index 50.
*/

});

router.get('/films/:filmid', function(request, response){
/*
Geeft alle informatie van de film met
het gegeven filmid, inclusief alle verhuur
informatie
*/
    var filmsId = request.params.filmid;

    response.contentType('application/json');

    db.query('SELECT * FROM film WHERE film_id=?', [filmsId], function(error, rows, fields) {
        if (error) {
            response.status(401).json(error);
        } else {
            response.status(200).json({ result: rows });
        };
    });
});


router.get('/rentals/:userid', function(request, response){

    var rentalsId = req.params.id;

    response.contentType('application/json');

    db.query('SELECT * FROM rental WHERE rental_id=?', [rentalsId], function(error, rows, fields) {
        if (error) {
            response.status(401).json(error);
        } else {
            response.status(200).json({ result: rows });
        };
    });


});

router.post('/rentals/:userid/:inventoryid', function(request, response){

});

router.put('/rentals/:userid/:inventoryid', function(request, response){

});

router.delete('/rentals/:userid/:inventoryid', function(request, response){

});
module.exports = router;