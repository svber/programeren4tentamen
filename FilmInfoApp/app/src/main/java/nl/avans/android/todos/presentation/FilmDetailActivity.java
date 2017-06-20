package nl.avans.android.todos.presentation;

/**
 * Created by Matthijs on 15-6-2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import nl.avans.android.todos.R;
import nl.avans.android.todos.domain.Film;
import nl.avans.android.todos.service.Config;
import nl.avans.android.todos.service.VolleyRequestQueue;

import static nl.avans.android.todos.presentation.MainActivity.FILM_DATA;

//import com.example.matthijs.eindopdrachtpr4.R;
//import com.example.matthijs.eindopdrachtpr4.domein.Film;
//import static com.example.matthijs.eindopdrachtpr4.presentatie.MainActivity.FILM_DATA;


public class FilmDetailActivity extends AppCompatActivity {

    private TextView textTitle;
    private TextView textContents;
    private TextView txtRentalErrorMsg;
    private EditText staff_id_text;

    private int staff_id;
    private String staff_id_str;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        textTitle = (TextView) findViewById(R.id.textDetailFilmTitle);
        textContents = (TextView) findViewById(R.id.textDetailFilmContents);
        staff_id_text = (EditText) findViewById(R.id.staff_id_text);

        Bundle extras = getIntent().getExtras();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staff_id_str = staff_id_text.getText().toString();
                staff_id = Integer.parseInt(staff_id_str);
                Context context = getApplicationContext();
                SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                String Stoken = sharedPref.getString("saved_token", "geen token");

                if (tokenAvailable()){
                    handleRental(staff_id);
                } else {
                    Log.d(TAG, "Geen token gevonden - inloggen dus");
                }

            }
        });

        Film film = (Film) extras.getSerializable(FILM_DATA);
        Log.i(TAG, film.toString());

        textTitle.setText(film.getTitle());
        textContents.setText(film.getContents());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish(); // or go to another activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleRental(int staff_id) {

        String body = "{\"staff_id\":\"" + staff_id + "\"}";
        Log.i(TAG, "handleRegister - body = " + body);

        try {
            JSONObject jsonBody = new JSONObject(body);
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, Config.URL_RENTALS, jsonBody, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            // Succesvol response - dat betekent dat we een geldig token hebben.;
                            displayMessage("Bedankt voor het huren!");

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            handleErrorResponse(error);
                        }
                    });

            jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                    1500, // SOCKET_TIMEOUT_MS,
                    2, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            // Access the RequestQueue through your singleton class.
            VolleyRequestQueue.getInstance(this).addToRequestQueue(jsObjRequest);

        } catch (JSONException e) {
            txtRentalErrorMsg.setText(e.getMessage());
            // e.printStackTrace();
        }
        return;
    }

    public void handleErrorResponse(VolleyError error) {
        Log.e(TAG, "handleErrorResponse");

        if(error instanceof com.android.volley.AuthFailureError) {

            String json = null;
            NetworkResponse response = error.networkResponse;
            if (response != null && response.data != null) {
                json = new String(response.data);
                json = trimMessage(json, "error");
                if (json != null) {
                    json = "Error " + response.statusCode + ": " + json;
                    displayMessage(json);
                }
            } else {
                Log.e(TAG, "handleErrorResponse: kon geen networkResponse vinden.");
            }
        } else if(error instanceof com.android.volley.NoConnectionError) {
            Log.e(TAG, "handleErrorResponse: server was niet bereikbaar");
            txtRentalErrorMsg.setText(getString(R.string.error_server_offline));
        } else {
            Log.e(TAG, "handleErrorResponse: error = " + error);
        }
    }

    public String trimMessage(String json, String key){
        Log.i(TAG, "trimMessage: json = " + json);
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return trimmedString;
    }

    public void displayMessage(String toastString){
        Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_LONG).show();
    }

    private boolean tokenAvailable() {
        boolean result = false;

        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String Stoken = sharedPref.getString(getString(R.string.saved_token), "dummy default token");
        if (Stoken != null && !Stoken.equals("dummy default token")) {
            result = true;
        }
        return result;
    }
}
