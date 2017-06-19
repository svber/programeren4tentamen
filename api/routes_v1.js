
var express = require('express');
var router = express.Router();
var db = require('../config/database');

router.get('/info', function(request, response) {
    response.status(200);
    response.json({
        "description": "Info about movies"
    });
});

router.get('/films', function(request, response){
/*
Geeft alle informatie van de gevraagde films. Offset en count kunnen als opties worden gegeven. Offset is het startpunt; count is het aantal films vanaf de offset. 
Voorbeeld: /api/v1/films?offset=50&count=20 retourneert 20 films vanaf index 50.
*/
    var offset = request.query.offset || 0;
    var count = request.query.count || 5;

    response.contentType('application/json');

    db.query('SELECT * FROM film LIMIT '+offset+', '+count+'', function(error, rows, fields) {
        if (error) {
            response.status(401).json(error);
        } else {
            response.status(200).json({ result: rows });
        };
    });
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

    var rentalsId = request.params.userid;

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
    /**
     * als jet niet lukt verander tabel met : ALTER TABLE `rental` CHANGE `rental_date` `rental_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;
     * en verwijder return date
     */
    var user_id = request.params.userid;
    var inventory_id = request.params.inventoryid

   
    var customer_id = user_id;
    var return_date = request.body.return_date;
    var staff_id = request.body.staff_id;
    
    response.contentType('application/json');

    db.query('INSERT INTO `rental`(`rental_date`, `inventory_id`, `customer_id`, `return_date`, `staff_id`) VALUES (CURRENT_TIMESTAMP,'+inventory_id+','+customer_id+',"'+return_date+'",'+staff_id+')', function(error, rows, fields) {
        if (error) {
            response.status(401).json(error);
        } else {
            response.status(200).json({ result: rows });
        };
    });
});

router.put('/rentals/:userid/:inventoryid', function(request, response){
    var user_id = request.params.userid;
    var inventory_id = request.params.inventoryid

    var rental_date = request.body.rental_date;
    var customer_id = user_id;
    var return_date = request.body.return_date;
    var staff_id = request.body.staff_id;

    response.contentType('application/json');

    db.query('UPDATE `rental` SET `rental_date`="'+rental_date+'",`return_date`="'+return_date+'",`staff_id`='+staff_id+' WHERE customer_id = '+user_id+' and inventory_id ='+inventory_id, function(error, rows, fields) {
        if (error) {
            response.status(401).json(error);
        } else {
            response.status(200).json({ result: rows });
        };
    });
});

router.delete('/rentals/:userid/:inventoryid', function(request, response){
    var user_id = request.params.userid;
    var inventoryid = request.params.inventoryid

    response.contentType('application/json');

    db.query('DELETE FROM `rental` WHERE `customer_id` = '+ user_id +' AND `inventory_id` ='+inventoryid+'', function(error, rows, fields) {
        if (error) {
            response.status(401).json(error);
        } else {
            response.status(200).json({ result: rows });
        };
    });
});

module.exports = router;