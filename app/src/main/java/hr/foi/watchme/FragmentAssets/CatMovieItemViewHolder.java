package hr.foi.watchme.FragmentAssets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class CatMovieItemViewHolder extends RecyclerView.ViewHolder {

    ImageView movieImage;
    TextView movieTitle;
    TextView movieId;
    Integer id = 0;
    Context c;
    public View itemView;

    public CatMovieItemViewHolder(@NonNull View itemView, final Context c) {
        super(itemView);
        movieId = itemView.findViewById(R.id.output_movie_menu_by_category_movie_id);
        movieImage = itemView.findViewById(R.id.output_movie_menu_by_category_movie_cover);
        movieTitle = itemView.findViewById(R.id.output_movie_menu_by_category_movie_name);
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

    public void bind(Movie m) {
        movieId.setText("" + m.getID());
        movieTitle.setText(m.getName());
        Picasso.get()
                .load(m.getCoverPhoto())
                .resize(100, 150)
                .into(movieImage);
    }
}
