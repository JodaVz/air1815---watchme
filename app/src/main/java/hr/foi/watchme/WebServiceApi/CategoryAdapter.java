package hr.foi.watchme.WebServiceApi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.foi.watchme.FragmentAssets.CatMovieItemViewHolder;
import hr.foi.watchme.R;
import hr.foi.watchme.POJO.Movie;


public class CategoryAdapter extends RecyclerView.Adapter<CatMovieItemViewHolder> {
    List<Movie> movieList;
    Context ctx;

    //TODO napraviti konstruktor koji prima listu filmova po kategoriji RIJEŠENO!
    public CategoryAdapter(List<Movie> movies, Context context){
        this.movieList = movies;
        this.ctx = context;
    }

    @NonNull
    @Override
    public CatMovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflatedCatMovie = LayoutInflater.from(ctx).inflate(R.layout.category_movie_item, viewGroup, false);
        return new CatMovieItemViewHolder(inflatedCatMovie);
    }

    @Override
    public void onBindViewHolder(@NonNull CatMovieItemViewHolder movieVH, int i) {
        //TODO uzeti element iz liste filmova prema kategoriji i poslat ga metodi bind iz movieVH.bind(); RIJEŠENO!
        movieVH.bind(movieList.get(i));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
