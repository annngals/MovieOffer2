package com.example.movieoffer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Movie{
    String name;
    String year;
    String genre;
    String reg;
    String time;
}
class Movies{
    ArrayList<Movie> movies;
}

public class MainActivity extends AppCompatActivity {

    TextView name;
    TextView year;
    TextView genre;
    TextView reg;
    TextView time;
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = loadMovies();
        name = findViewById(R.id.movieNameTV);
        year = findViewById(R.id.yearTV);
        genre = findViewById(R.id.genreTV);
        reg = findViewById(R.id.regTV);
        time = findViewById(R.id.timeTV);
    }

    public ArrayList<Movie> loadMovies() {
        InputStream stream = null;
        try {
            stream = getAssets().open("movies.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(stream);
        Gson gson = new Gson();
        Movies m = gson.fromJson(reader, Movies.class);
        return m.movies;
    }

    public void onClick(View v){
        if (movies.size() != 0) {
            int randomId = (int) (Math.random() * movies.size());
            name.setText(movies.get(randomId).name);
            year.setText(movies.get(randomId).year);
            genre.setText(movies.get(randomId).genre);
            reg.setText(movies.get(randomId).reg);
            time.setText(movies.get(randomId).time);
            movies.remove(randomId);
        }
        else {
            name.setText("Run out of movies");
            year.setText("");
            genre.setText("");
            reg.setText("");
            time.setText("");
        }
    }

}