package nl.avans.android.todos.domain;


/**
 * Created by Matthijs on 15-6-2017.
 */

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Deze class vertaalt JSON objecten naar (lijsten van) ToDos.
 */
public class FilmMapper {

    public static final String FILM_RESULT = "result";
    public static final String FILM_TITLE = "title";
    public static final String FILM_DESCRIPTION = "description";
    public static final String FILM_RELEASE_YEAR = "release_year";
    public static final String FILM_STATUS = "last_update";

    /**
     * Map het JSON response op een arraylist en retourneer deze.
     */
    public static ArrayList<Film> mapFilmList(JSONObject response){

        ArrayList<Film> result = new ArrayList<>();

        try{
            JSONArray jsonArray = response.getJSONArray(FILM_RESULT);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonProduct = jsonArray.getJSONObject(i);

                // Convert stringdate to Date
                String timestamp = jsonProduct.getString(FILM_RELEASE_YEAR);
                DateTime filmDateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(timestamp);

                Film film = new Film(
                        jsonProduct.getString(FILM_TITLE),
                        jsonProduct.getString(FILM_DESCRIPTION),
                        jsonProduct.getString(FILM_STATUS),
                        filmDateTime
                );
//                Log.i("ToDoMapper", "Film: " + film);

                result.add(film);
            }
        } catch( JSONException ex) {
            Log.e("FilmMapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
        return result;
    }
}
