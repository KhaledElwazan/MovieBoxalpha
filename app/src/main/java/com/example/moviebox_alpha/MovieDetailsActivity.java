package com.example.moviebox_alpha;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviebox_alpha.retrofit.Result;

import java.io.InputStream;
import java.net.URL;

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




        }
    }
}
