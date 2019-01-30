package hr.foi.watchme.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hr.foi.watchme.FragmentAssets.CatMovieItemVH_LV;
import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class LinearAdapter extends RecyclerView.Adapter<CatMovieItemVH_LV> {
    ArrayList<Movie> movieList;
    Context ctx;
    private static MovieDetailsInterface itemListener;

    public LinearAdapter(ArrayList<Movie> movies, FragmentActivity context, MovieDetailsInterface listener){
        this.movieList = movies;
        this.ctx = context;
        this.itemListener = listener;
    }

    @NonNull
    @Override
    public CatMovieItemVH_LV onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflatedCatMovie = LayoutInflater.from(ctx).inflate(R.layout.fragment_movie_item_list, viewGroup, false);
        return new CatMovieItemVH_LV(inflatedCatMovie,ctx);
    }


    @Override
    public void onBindViewHolder(@NonNull CatMovieItemVH_LV movieVH, int i) {
        final Movie m = movieList.get(i);
        movieVH.bind(m);
        movieVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.movieClicked(m);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
