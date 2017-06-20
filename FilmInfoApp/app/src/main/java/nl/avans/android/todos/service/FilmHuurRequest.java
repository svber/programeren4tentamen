package nl.avans.android.todos.service;

/**
 * Created by Matthijs on 19-6-2017.
 */



/**
 * Created by Matthijs on 15-6-2017.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nl.avans.android.todos.R;
import nl.avans.android.todos.domain.FilmHuur;

/**
 * Deze class handelt requests naar de API server af. De JSON objecten die we terug krijgen
 * worden door de ToDoMapper vertaald naar (lijsten van) ToDo items.
 */
public class FilmHuurRequest {

    private Context context;
    public final String TAG = this.getClass().getSimpleName();

    // De aanroepende class implementeert deze interface.
    private FilmHuurRequest.FilmHuurListener listener;

    /**
     * Constructor
     *
     * @param context
     * @param listener
     */
    public FilmHuurRequest(Context context, FilmHuurRequest.FilmHuurListener listener) {
        this.context = context;
        this.listener = listener;
    }

    /**
     * Verstuur een POST met nieuwe FilmHuur.
     */
    public void handlePostCustomer(final FilmHuur newFilmHuur) {

        Log.i(TAG, "handlePostFilmHuur");

        // Haal het token uit de prefs
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final String token = sharedPref.getString(context.getString(R.string.saved_token), "dummy default token");
        if(token != null && !token.equals("dummy default token")) {

            //
            // Maak een JSON object met username en password. Dit object sturen we mee
            // als request body (zoals je ook met Postman hebt gedaan)
            //
            String body = "{\"staff_id\":\"" + newFilmHuur.getStaff_id() + "\",\"rental_date\":\"" + newFilmHuur.getRental_date() + "\"}";

            try {
                JSONObject jsonBody = new JSONObject(body);
                Log.i(TAG, "handlePostFilmHuur - body = " + jsonBody);
                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.POST, Config.URL_RENTALS, jsonBody, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i(TAG, response.toString());
                                // Het toevoegen is gelukt
                                // Hier kun je kiezen: of een refresh van de hele lijst ophalen
                                // en de ListView bijwerken ... Of alleen de ene update toevoegen
                                // aan de ArrayList. Wij doen dat laatste.
                                listener.onFilmHuurAvailable(newFilmHuur);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Error - send back to caller
                                listener.onFilmHuurError(error.toString());
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization", "Bearer " + token);
                        return headers;
                    }
                };

                // Access the RequestQueue through your singleton class.
                VolleyRequestQueue.getInstance(context).addToRequestQueue(jsObjRequest);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                listener.onFilmHuurError(e.getMessage());
            }
        }
    }

    //
    // Callback interface - implemented by the calling class (MainActivity in our case).
    //
    public interface FilmHuurListener {
        // Callback function to return a fresh list of ToDos
        void onFilmHuurAvailable(ArrayList<FilmHuur> filmsHuur);

        // Callback function to handle a single added Film.
        void onFilmHuurAvailable(FilmHuur filmHuur);

        // Callback to handle serverside API errors
        void onFilmHuurError(String message);
    }

}