package com.example.moviebox_alpha;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviebox_alpha.activities.MovieDetailsActivity;
import com.example.moviebox_alpha.retrofit.Result;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class MovieAdapter extends BaseAdapter {

    private List<Result> movies;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;

    }

    public void setMovies(List<Result> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies == null ? 0 : movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Result movie = movies.get(position);

        if (convertView == null) {
            LayoutInflater from = LayoutInflater.from(context);
            convertView = from.inflate(R.layout.card, null);
        }


        final ImageView moviePoster = convertView.findViewById(R.id.moviePoster);

        moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("movie", movie);
                intent.putExtras(b); //Put your id to your next Intent
                context.startActivity(intent);

            }
        });


        TextView movieTitle = convertView.findViewById(R.id.movieName);


        String posterURl = context.getString(R.string.imageURL) + movie.getPosterPath();

        movie.setPosterURL(posterURl);


        new AsyncTask<String, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(String... results) {

                String poster = results[0];

                try {

                    InputStream inputStream = new URL(poster).openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    return bitmap;

                } catch (Exception e) {
                    Log.e("failed loading poster", e.toString());
                }

                System.out.println("null");
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                moviePoster.setImageBitmap(bitmap);
            }
        }.execute(posterURl);


        movieTitle.setText(movie.getTitle());


        return convertView;
    }


}
