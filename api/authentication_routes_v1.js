//
// ./api/authentication.routes.v1.js
//
var express = require('express');
var router = express.Router();
var db = require('../config/database');
var auth = require('../authentication/authentication');

//
// Hier gaat de gebruiker inloggen.
// Input: username en wachtwoord
// ToDo: 
//	 - zoek de username in de database, en vind het password dat opgeslagen is
// 	 - als user gevonden en password matcht, dan return valide token
//   - anders is de inlogpoging gefaald - geef foutmelding terug.
//
router.post('/login', function(req, res) {

// Geeft een geldig token wanneer de gebruiker 
// (customer) is ingelogd; anders
// een duidelijke foutmelding

    // Even kijken wat de inhoud is
    console.dir(req.body);

    // De username en pwd worden meegestuurd in de request body
    var username = req.body.username;
    var password = req.body.password;

    //email(username) en password uit de database halen
    db.query('SELECT `email`, `password` FROM `customer` WHERE `email` = "'+username+'"', function(error, rows, fields) {
        if (error) {
           console.dir(error);
        } else {
            console.log(rows);
            //var result = JSON.parse(rows);
            //console.log(result)
            try{
                var dbUsername = rows[0].email;
                var dbPassword = rows[0].password

            if (username == dbUsername && password == dbPassword) {
                var token = auth.encodeToken(username);
                res.status(200).json({"token": token,});
            } else {
            console.log('Input: username = ' + username + ', password = ' + password);
            res.status(401).json({ "error": "Invalid credentials, bye" })
            }
            } catch(e){
                res.status(401).json({ "error": "Invalid credentials, bye" })
            }
        };
    });

    // Kijk of de gegevens matchen. Zo ja, dan token genereren en terugsturen.


});

router.post('/register', function(request, response){
/*
hier kan je een account aan maken als je nog geen account hebt
of je krijgt een duidelijke foutmelding
*/
console.dir(request.body);

    var customer = request.body;
    var query = {
        sql: 'INSERT INTO `customer`(`first_name`, `last_name`, `email`) VALUES (?, ?, ?)',
        values: [customer.first_name, customer.last_name, customer.email],
        
    };

    console.dir(customer);
    console.log('Onze query: ' + query.sql);

    response.contentType('application/json');
    db.query(query, function(error, rows, fields) {
        if (error) {
            response.status(401).json(error);
        } else {
            response.status(200).json({ result: rows });
        };
    });
});
// Hiermee maken we onze router zichtbaar voor andere bestanden. 
module.exports = router;