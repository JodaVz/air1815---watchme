package hr.foi.watchme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class GridAdapter extends BaseAdapter {
    ArrayList<Movie> movies;
    private MovieDetailsInterface itemListener;
    Context context;
    private final String [] imageUrls;
    private final String [] movieNames;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapter(ArrayList<Movie> movies, MovieDetailsInterface itemListener, Context context, String[] imageUrls, String[] movieNames) {
        this.movies = movies;
        this.itemListener = itemListener;
        this.context = context;
        this.imageUrls = imageUrls;
        this.movieNames = movieNames;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            view = new View(context);
            view = layoutInflater.inflate(R.layout.category_movie_item, null);
            ImageView movieImage = (ImageView) view.findViewById(R.id.output_movie_menu_by_category_movie_cover);
            TextView movieName = (TextView) view.findViewById(R.id.output_movie_menu_by_category_movie_name);
            TextView movieID = (TextView) view.findViewById(R.id.output_movie_menu_by_category_movie_id);
            Picasso.get()
                    .load(imageUrls[position])
                    .fit()
                    .centerCrop()
                    .into(movieImage);
            movieName.setText(movieNames[position]);
            final Movie m = movies.get(position);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.movieClicked(m);
                }
            });
        }
        return view;
    }
}
