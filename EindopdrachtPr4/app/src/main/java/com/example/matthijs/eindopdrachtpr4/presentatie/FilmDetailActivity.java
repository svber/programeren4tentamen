package com.example.matthijs.eindopdrachtpr4.presentatie;

/**
 * Created by Matthijs on 15-6-2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.matthijs.eindopdrachtpr4.R;
import com.example.matthijs.eindopdrachtpr4.domein.Film;
import nl.avans.android.todos.R;
import nl.avans.android.todos.domain.ToDo;

import static com.example.matthijs.eindopdrachtpr4.presentatie.MainActivity.FILM_DATA;
import static nl.avans.android.todos.presentation.MainActivity.TODO_DATA;

public class FilmDetailActivity extends AppCompatActivity {

    private TextView textTitle;
    private TextView textContents;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        textTitle = (TextView) findViewById(R.id.textDetailFilmTitle);
        textContents = (TextView) findViewById(R.id.textDetailFilmContents);

        Bundle extras = getIntent().getExtras();

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
}
