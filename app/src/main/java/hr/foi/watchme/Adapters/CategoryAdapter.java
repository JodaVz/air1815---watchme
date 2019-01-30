package hr.foi.watchme.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hr.foi.watchme.FragmentAssets.CatMovieItemViewHolder;
import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;


public class CategoryAdapter extends RecyclerView.Adapter<CatMovieItemViewHolder> {
    ArrayList<Movie> movieList;
    Context ctx;
    private static MovieDetailsInterface itemListener;

    //TODO napraviti konstruktor koji prima listu filmova po kategoriji RIJEŠENO!
    public CategoryAdapter(ArrayList<Movie> movies, FragmentActivity context, MovieDetailsInterface listener){
        this.movieList = movies;
        this.ctx = context;
        this.itemListener = listener;
    }

    @NonNull
    @Override
    public CatMovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflatedCatMovie = LayoutInflater.from(ctx).inflate(R.layout.category_movie_item, viewGroup, false);
        return new CatMovieItemViewHolder(inflatedCatMovie,ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull final CatMovieItemViewHolder movieVH, int i) {
        //TODO uzeti element iz liste filmova prema kategoriji i poslat ga metodi bind iz movieVH.bind(); RIJEŠENO!
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
