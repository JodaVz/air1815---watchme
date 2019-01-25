package hr.foi.watchme.FragmentAssets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import hr.foi.watchme.Fragments.MovieCategoryFragment;
import hr.foi.watchme.Interfaces.MoviesInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.POJO.MovieCategory;
import hr.foi.watchme.R;
import hr.foi.watchme.Adapters.ViewPageAdapter;

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

    private List<Movie> movies;
    private String[] movieUrls;
    private List<MovieCategory> moviesByCategories;
    private MoviesInterface mListenerActivity;
    LinearLayout catContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FillWithUrls();
        View viewMain = inflater.inflate(R.layout.fragment_movies, container, false);
        catContainer = viewMain.findViewById(R.id.categoryFragsContainer);
        return viewMain;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewPager viewPager = getView().findViewById(R.id.view_pager);
        ViewPageAdapter adapter = new ViewPageAdapter(getActivity(), movieUrls);
        viewPager.setAdapter(adapter);

        fillCategories();
    }

    private void fillCategories() {

        for(MovieCategory cat : moviesByCategories){
            MovieCategoryFragment frag = MovieCategoryFragment.newInstance(cat.getName(), cat.getMovies());
            FragmentManager fragMan = getChildFragmentManager();
            FragmentTransaction fragTransaction = fragMan.beginTransaction();

            fragTransaction.add(R.id.categoryFragsContainer, frag , "" + cat.getName());
            fragTransaction.commit();
        }
    }


    public void FillWithUrls() {
        moviesByCategories = mListenerActivity.getAllMoviesByCategories();
        movies = mListenerActivity.getMovieList();
        //TODO dio koda ispod prebaciti u aktivnost da vrati čiste URLove, a ne viška podatke o filmove
        movieUrls = new String[movies.size()];

        for (int i = 0; i < movies.size(); i++) {
            movieUrls[i] = movies.get(i).getCoverPhoto();
        }
        Log.d("POLJE URLA::", Integer.toString(movieUrls.length));
        for (int i = 0; i < movieUrls.length; i++) {
            Log.d("ELEMENT " + i + " : ", movieUrls[i]);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListenerActivity = (MoviesInterface) getActivity();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
