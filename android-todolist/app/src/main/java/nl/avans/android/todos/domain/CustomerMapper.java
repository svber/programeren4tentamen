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
public class CustomerMapper {

    public static final String CUSTOMER_RESULT = "result";
    public static final String CUSTOMER_FIRSTNAME = "first_name";
    public static final String CUSTOMER_LASTNAME = "last_name";
    public static final String CUSTOMER_EMAIL = "email";
    public static final String CUSTOMER_STATUS = "create_date";

    /**
     * Map het JSON response op een arraylist en retourneer deze.
     */
    public static ArrayList<Customer> mapCustomerList(JSONObject response){

        ArrayList<Customer> result = new ArrayList<>();

        try{
            JSONArray jsonArray = response.getJSONArray(CUSTOMER_RESULT);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonProduct = jsonArray.getJSONObject(i);

                // Convert stringdate to Date
                String timestamp = jsonProduct.getString(CUSTOMER_STATUS);
                DateTime customerDateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(timestamp);

                Customer customer = new Customer(
                        jsonProduct.getString(CUSTOMER_FIRSTNAME),
                        jsonProduct.getString(CUSTOMER_LASTNAME),
                        jsonProduct.getString(CUSTOMER_EMAIL),
                        customerDateTime
                );

                result.add(customer);
            }
        } catch( JSONException ex) {
            Log.e("CustomerMapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
        return result;
    }
}
