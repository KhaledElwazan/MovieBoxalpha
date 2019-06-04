package com.example.moviebox_alpha.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviebox_alpha.R;
import com.example.moviebox_alpha.db.MovieDB;
import com.example.moviebox_alpha.retrofit.Result;

import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class MovieDetailsActivity extends AppCompatActivity {


    private MovieDB movieDB;
    private boolean isFavorited = false;
    private Context context;
    private FloatingActionButton favorite;
    private Result movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        this.context = this;


        Bundle b = getIntent().getExtras();
        movie = null;
        if (b != null) {
            movie = (Result) b.getSerializable("movie");

            setTitle(movie.getTitle());


            final ImageView poster = findViewById(R.id.posterInDetailsFragment);

            new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(String... strings) {

                    try {

                        InputStream inputStream = new URL(strings[0]).openStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        return bitmap;

                    } catch (Exception e) {
                        Log.e("loading poster", e.toString());
                    }

                    return null;
                }


                @Override
                protected void onPostExecute(Bitmap bitmap) {

                    poster.setImageBitmap(bitmap);

                }
            }.execute(movie.getPosterURL());


            TextView releaseDate = findViewById(R.id.releaseDate);
            releaseDate.setText(movie.getReleaseDate());

            TextView voteAvg = findViewById(R.id.voteAverage);
            voteAvg.setText(movie.getVoteAverage().toString());


            TextView synposis = findViewById(R.id.synopsis);
            synposis.setText(movie.getOverview());


            favorite = findViewById(R.id.favorite);


            if (movieDB == null)
                movieDB = MovieDB.getInstance(context);

            new AsyncTask<Result, Void, Boolean>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                protected Boolean doInBackground(Result... pars) {

                    List<Result> results = movieDB.getMovieDBDao().getAll();

                    for (Result t : results) {


                        if (t.equals(pars[0]))
                            return true;

                    }


                    return false;
                }


                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    isFavorited = aBoolean;

                    if (isFavorited) {
                        favorite.setImageDrawable(ContextCompat.getDrawable(context, android.R.drawable.btn_star_big_on));
                    } else
                        favorite.setImageDrawable(ContextCompat.getDrawable(context, android.R.drawable.btn_star_big_off));

                    movieDB.cleanUp();
                }


            }.execute(movie);


            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (movieDB == null)
                        movieDB = MovieDB.getInstance(context);

                    if (!isFavorited) {

                        Snackbar.make(view, "Added to favorite movie list", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        favorite.setImageDrawable(ContextCompat.getDrawable(context, android.R.drawable.btn_star_big_on));
                        isFavorited = true;
                        movieDB.getMovieDBDao().insert(movie);

                    } else {
                        Snackbar.make(view, "Removed from favorite movie list", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        favorite.setImageDrawable(ContextCompat.getDrawable(context, android.R.drawable.btn_star_big_off));
                        isFavorited = false;
                        movieDB.getMovieDBDao().delete(movie);
                    }
                    if (movieDB != null)
                        movieDB.cleanUp();
                }
            });


        }
    }

    @Override
    public void onBackPressed() {

//        Intent intent = new Intent(context, MainActivity.class);
//        context.startActivity(intent);

    }
}
