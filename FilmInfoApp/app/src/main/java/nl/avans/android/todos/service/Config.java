package nl.avans.android.todos.service;

/**
 * Bevat o.a. URL definities.
 */

public class Config {

    // aangepast zodat android mijn heroku app ziet.
    public static final String URL_LOGIN = "https://programeren4tentamen.herokuapp.com/api/v1/login";
    public static final String URL_FILMS = "https://programeren4tentamen.herokuapp.com/api/v1/films/?offset=0&count=10";
    public static final String URL_CUSTOMERS = "https://programeren4tentamen.herokuapp.com/api/v1/register";
    public static final String URL_RENTALS = "https://programeren4tentamen.herokuapp.com/api/v1/rentals/:userid/:inventoryid";

}
