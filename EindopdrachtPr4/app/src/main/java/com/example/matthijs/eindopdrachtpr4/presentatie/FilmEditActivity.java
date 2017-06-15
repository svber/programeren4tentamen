package com.example.matthijs.eindopdrachtpr4.presentatie;

/**
 * Created by Matthijs on 15-6-2017.
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.matthijs.eindopdrachtpr4.R;
import com.example.matthijs.eindopdrachtpr4.domein.Film;
import com.example.matthijs.eindopdrachtpr4.service.FilmRequest;
import nl.avans.android.todos.R;
import nl.avans.android.todos.domain.ToDo;
import nl.avans.android.todos.service.ToDoRequest;

import static nl.avans.android.todos.presentation.MainActivity.TODO_DATA;

public class FilmEditActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textTitle;
    private TextView textContents;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_edit);

        textTitle = (TextView) findViewById(R.id.textEditFilmTitle);
        textContents = (TextView) findViewById(R.id.textEditFilmContents);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSaveNewFilm);
        fab.setOnClickListener(this);

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
        Film newFilm = new Film(textTitle.getText().toString(), textContents.getText().toString());

        // We return our data to the MainActivity for further handling.
        Intent iData = new Intent();
        iData.putExtra( MainActivity.FILM_DATA, newFilm );

        // Tell the caller that everyting is OK.
        setResult( android.app.Activity.RESULT_OK, iData );

        // ..returns us to the parent "MainActivity"..
        finish();
    }
}