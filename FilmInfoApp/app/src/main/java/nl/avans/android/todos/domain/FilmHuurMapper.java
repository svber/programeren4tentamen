package nl.avans.android.todos.domain;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Matthijs on 20-6-2017.
 */


    public class FilmHuurMapper {

        public static final String RENTAL_RESULT = "result";
        public static final String RENTAL_STAFFID = "staff_id";
        public static final String RENTAL_STATUS = "rental_date";

        /**
         * Map het JSON response op een arraylist en retourneer deze.
         */
        public static ArrayList<FilmHuur> mapFilmHuurList(JSONObject response){

            ArrayList<FilmHuur> result = new ArrayList<>();

            try{
                JSONArray jsonArray = response.getJSONArray(RENTAL_RESULT);

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonProduct = jsonArray.getJSONObject(i);

                    // Convert stringdate to Date
                    String timestamp = jsonProduct.getString(RENTAL_STATUS);
                    DateTime customerDateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(timestamp);

                    FilmHuur filmHuur = new FilmHuur(
                            jsonProduct.getInt(RENTAL_STAFFID)
                    );

                    result.add(filmHuur);
                }
            } catch( JSONException ex) {
                Log.e("FilmHuurMapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
            }
            return result;
        }
    }

