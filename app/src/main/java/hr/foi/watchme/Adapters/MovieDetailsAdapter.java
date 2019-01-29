package hr.foi.watchme.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;

import java.text.ParseException;

import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.Fragments.MovieDetails;

public class MovieDetailsAdapter {

    private Context context;
    private Movie movie;

    public MovieDetailsAdapter(Context context, Movie movie) {
        this.context = context;
        this.movie = movie;
    }

    public void onBindViewHolder(@NonNull MovieDetails movieVH) throws ParseException {
        movieVH.bind(movie);
    }
}
