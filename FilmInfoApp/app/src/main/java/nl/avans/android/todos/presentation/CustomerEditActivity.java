package nl.avans.android.todos.presentation;

/**
 * Created by Matthijs on 15-6-2017.
 */

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
//import com.example.matthijs.eindopdrachtpr4.R;
//import com.example.matthijs.eindopdrachtpr4.domein.Film;
//import com.example.matthijs.eindopdrachtpr4.service.FilmRequest;

//import static com.example.matthijs.eindopdrachtpr4.presentatie.MainActivity.FILM_DATA;
import nl.avans.android.todos.R;
import nl.avans.android.todos.domain.Customer;
import nl.avans.android.todos.domain.Film;
import nl.avans.android.todos.service.FilmRequest;

import static nl.avans.android.todos.presentation.MainActivity.FILM_DATA;

//import static nl.avans.android.todos.presentation.MainActivity.CUSTOMER_DATA;
//import static nl.avans.android.todos.presentation.MainActivity.FILM_DATA;

public class CustomerEditActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textTitle;
    private TextView textContents;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_edit);

        textTitle = (TextView) findViewById(R.id.textEditCustomerEmail);
        textContents = (TextView) findViewById(R.id.textEditCustomerPassword);

        // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSaveNewCustomer);
        //fab.setOnClickListener(this);

        // See if there's any extras for us.
        // We could use this Activity for both new ToDos
        // and for editing existing ToDos
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Film film = (Film) extras.getSerializable(FILM_DATA);
            Log.i(TAG, film.toString());

            textTitle.setText(film.getTitle());
            textContents.setText(film.getContents());
        }
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

    /**
     * Saving the new ToDo
     * We return a ToDo object to the caller (MainActivity) for further handling.
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        // Save new ToDo and return to the previous Activity.
        Customer newCustomer = new Customer(textTitle.getText().toString(), textContents.getText().toString());

        // We return our data to the MainActivity for further handling.
        Intent iData = new Intent();
        iData.putExtra(FILM_DATA, (Parcelable) newCustomer);

        // Tell the caller that everyting is OK.
        setResult( android.app.Activity.RESULT_OK, iData );

        // ..returns us to the parent "MainActivity"..
        finish();
    }
}
