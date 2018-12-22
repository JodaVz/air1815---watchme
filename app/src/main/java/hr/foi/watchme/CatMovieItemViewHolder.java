package hr.foi.watchme;

import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hr.foi.watchme.WebServiceApi.POJO.Movie;

public class CatMovieItemViewHolder extends RecyclerView.ViewHolder {

    ImageView movieImage;
    TextView movieTitle;

    public CatMovieItemViewHolder(@NonNull View itemView) {
        super(itemView);
        //TODO holder će primiti INFLATAN! xml jedne stavke filma (category_movie_item.xml)

        //TODO koristiti findViewById da se iz itemViewa nađe imageView i textview RIJEŠENO!
        movieImage = itemView.findViewById(R.id.output_movie_menu_by_category_movie_cover);
        movieTitle = itemView.findViewById(R.id.output_movie_menu_by_category_movie_name);

    }

    public void bind(Movie m){
        //TODO koristiti Piccaso movieImage.set RIJEŠENO!
        movieTitle.setText(m.getName());
        Picasso.get()
                .load(m.getCoverPhoto())
                .resize(100, 150)
                .into(movieImage);
    }
}
