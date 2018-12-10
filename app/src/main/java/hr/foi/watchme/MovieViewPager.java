package hr.foi.watchme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hr.foi.watchme.WebServiceApi.POJO.Movie;
import hr.foi.watchme.WebServiceApi.ViewPageAdapter;

/*

public class MovieViewPager extends AppCompatActivity {
    private String[] imageUrls = new String[]{
            "https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg"
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movies);

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPageAdapter adapter = new ViewPageAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);
    }
}
*/
public class MovieViewPager extends Fragment {

    private String[] movieUrls;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FillWithUrls();
        View viewMain = inflater.inflate(R.layout.fragment_movies, container, false);
        return viewMain;
    }


    @Override
    public void onResume() {
        super.onResume();
        FillWithCategories();
        ViewPager viewPager = getView().findViewById(R.id.view_pager);
        ViewPageAdapter adapter = new ViewPageAdapter(getActivity(), movieUrls);
        viewPager.setAdapter(adapter);
    }

    public void FillWithUrls() {
        movieUrls = new String[MainActivity.movieList.size()];

        for (int i = 0; i < MainActivity.movieList.size(); i++) {
            movieUrls[i] = MainActivity.movieList.get(i).getCoverPhoto();
        }
        Log.d("POLJE URLA::", Integer.toString(movieUrls.length));
        for (int i = 0; i < movieUrls.length; i++) {
            Log.d("ELEMENT " + i + " : ", movieUrls[i]);
        }
    }


    public void FillWithCategories() {
        LinearLayout category = getView().findViewById(R.id.layout_categories);
        LinearLayout gallery = getView().findViewById(R.id.layout_movies_categories_gallery);
        LayoutInflater inflater = LayoutInflater.from(getContext());


        for (Movie movie : MainActivity.movieList) {
            View view = inflater.inflate(R.layout.fragment_movie_menu_in_category, gallery, false);

            TextView textView = view.findViewById(R.id.output_movie_menu_by_category_movie_name);
            textView.setText(movie.getName());

            ImageView imageView = view.findViewById(R.id.output_movie_menu_by_category_movie_cover);
            Picasso.get()
                    .load(movie.getCoverPhoto())
                    .resize(100, 150)
                    .into(imageView);

            gallery.addView(view);
        }
    }
}
