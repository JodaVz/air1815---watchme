package hr.foi.watchme.FragmentAssets;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class CatMovieItemViewHolder extends RecyclerView.ViewHolder{

    ImageView movieImage;
    TextView movieTitle;

    public CatMovieItemViewHolder(@NonNull View itemView) {
        super(itemView);

        movieImage = itemView.findViewById(R.id.output_movie_menu_by_category_movie_cover);
        movieTitle = itemView.findViewById(R.id.output_movie_menu_by_category_movie_name);

    }

    public void bind(Movie m){
        movieTitle.setText(m.getName());
        Picasso.get()
                .load(m.getCoverPhoto())
                .resize(100, 150)
                .into(movieImage);
    }
}
