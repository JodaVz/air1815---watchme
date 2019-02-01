package hr.foi.watchme.FragmentAssets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import com.example.pojo.Movie;
import hr.foi.watchme.R;

public class CatMovieItemVH_LV extends RecyclerView.ViewHolder {
    ImageView movieImage;
    TextView movieId;
    TextView movieTitle;
    TextView rating;
    TextView year;
    Integer id = 0;
    Context c;
    public View itemView;

    //Initializing text and image views. Setting toast to display title of selected movie
    public CatMovieItemVH_LV(@NonNull View itemView, final Context c) {
        super(itemView);
        movieId = itemView.findViewById(R.id.movie_in_category_list_movie_id);
        movieImage = itemView.findViewById(R.id.movie_in_category_list_cover);
        movieTitle = itemView.findViewById(R.id.movie_in_category_list_movie_name);
        rating = itemView.findViewById(R.id.movie_in_category_list_movie_rating);
        year = itemView.findViewById(R.id.movie_in_category_list_movie_year);
        this.itemView = itemView;
        this.c = c;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = Integer.parseInt(movieId.getText().toString());
                Toast.makeText(v.getContext(), id + " " + movieTitle.getText(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    //Binding title, id and cover photo of a movie to it's corresponding text and image views
    public void bind(Movie m) {
        movieId.setText("" + m.getID());
        movieTitle.setText(m.getName());
        year.setText("" + m.getReleaseDate());
        Picasso.get()
                .load(m.getCoverPhoto())
                .resize(100, 150)
                .into(movieImage);
        rating.setText("" + m.getRating());
    }
}
