package com.example.moviebox_alpha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.moviebox_alpha.retrofit.Result;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        Bundle b = getIntent().getExtras();
        Result movie = null;
        if (b != null) {
            movie = (Result) b.getSerializable("movie");

            setTitle(movie.getTitle());
        }
    }
}
