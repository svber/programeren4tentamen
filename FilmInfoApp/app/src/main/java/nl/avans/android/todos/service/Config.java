package nl.avans.android.todos.service;


import android.support.v4.app.SupportActivity;

import static nl.avans.android.todos.presentation.MainActivity.nummer;

/**
 * Bevat o.a. URL definities.
 */

public class Config extends SupportActivity.ExtraData {

    // aangepast zodat android mijn heroku app ziet.
    public static final String URL_LOGIN = "https://programeren4tentamen.herokuapp.com/api/v1/login";
    public static final String URL_FILMS = "https://programeren4tentamen.herokuapp.com/api/v1/films/?offset="+ nummer +"&count=10";
    public static final String URL_CUSTOMERS = "https://programeren4tentamen.herokuapp.com/api/v1/register";
    public static final String URL_RENTALS = "https://programeren4tentamen.herokuapp.com/api/v1/rentals/:userid/:inventoryid";

}
