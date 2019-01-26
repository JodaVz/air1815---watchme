package hr.foi.watchme.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import hr.foi.watchme.FragmentAssets.GridView;
import hr.foi.watchme.Interfaces.CatalogInterface;
import hr.foi.watchme.POJO.Movie;

public class CategoryCatalogAdapter{

    private String name;
    private List<Movie> movie;
    private Context context;
    private static CatalogInterface catalogListener;

    public CategoryCatalogAdapter(Context context, List<Movie> movie, String name) {
        this.context = context;
        this.movie = movie;
        this.name = name;
    }

    public void onBindViewHolder(@NonNull GridView V) {
        V.bind(movie, name);
        V.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogListener.CategoryClicked(name, movie);
            }
        });
    }
}
