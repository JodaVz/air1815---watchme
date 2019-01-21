package hr.foi.watchme.WebServiceApi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import hr.foi.watchme.POJO.MovieDetails;
import hr.foi.watchme.POJO.Movie;

public class MovieDetailsAdapter extends PagerAdapter {

    private Context context;
    private Movie movie;

    public MovieDetailsAdapter(Context context, Movie movie) {
        this.context = context;
        this.movie = movie;
    }

    public void onBindViewHolder(@NonNull MovieDetails movieVH) {
        movieVH.bind(movie);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }

}
