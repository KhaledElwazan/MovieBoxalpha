package com.example.moviebox_alpha;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviebox_alpha.activities.MovieDetailsActivity;
import com.example.moviebox_alpha.retrofit.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends BaseAdapter {

    private List<Result> movies;
    private Context context;

    // private Bitmap[] posters;
    private List<Bitmap> posters = new ArrayList<Bitmap>();

    public MovieAdapter(Context context) {
        this.context = context;

    }

    public void setMovies(List<Result> movies, List<Bitmap> posters) {
        this.movies = movies;
        this.posters = posters;

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
                intent.putExtras(b);
                context.startActivity(intent);

            }
        });


        TextView movieTitle = convertView.findViewById(R.id.movieName);
        movieTitle.setText(movie.getTitle());
//        moviePoster.setImageBitmap(posters[position]);

        String posterURl = context.getString(R.string.imageURL) + movie.getPosterPath();

        movie.setPosterURL(posterURl);


        if (posters.get(position) != null)
            moviePoster.setImageBitmap(posters.get(position));





        return convertView;
    }


}
