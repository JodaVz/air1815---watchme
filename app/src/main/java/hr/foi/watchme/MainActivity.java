package hr.foi.watchme;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import hr.foi.watchme.WebServiceApi.GetDataCallback;
import hr.foi.watchme.WebServiceApi.POJO.Movie;
import hr.foi.watchme.WebServiceApi.WatchMeWebServiceCaller;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MoviesInterface{

    private DrawerLayout drawer;
    public static List<Movie> movieList;
    public static List<MovieCategory> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //It will show popular fragment on create
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MovieFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_popular);
        }

        WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();
        webServiceCaller.getMovies(new GetDataCallback() {
            @Override
            public void onGetData(String dataResponse) {
                Gson gson = new Gson();
                TypeToken<List<Movie>> token = new TypeToken<List<Movie>>() {
                };
                movieList = gson.fromJson(dataResponse, token.getType());
            }
        });

        webServiceCaller.getCategories(new GetDataCallback() {
            @Override
            public void onGetData(String dataResponse) {
                Gson gson = new Gson();
                TypeToken<List<List<Movie>>> token = new TypeToken<List<List<Movie>>>() {
                };
                //TODO prilagoditi JSON da jedna stavka polja izgleda kao uređeni par (ime kategorije, polje filmova)
                categoryList = gson.fromJson(dataResponse, token.getType());
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_movie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MovieViewPager()).commit();
                break;
            case R.id.nav_tv_series:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TvSeriesFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MovieFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public List<Movie> getMovieList() {
        return movieList;
    }

    @Override
    public List<MovieCategory> getAllMoviesByCategories() {
        List<MovieCategory> mockLista = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));
        movies.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));
        movies.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));
        movies.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));
        movies.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));
        movies.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));
        movies.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));
        movies.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));

        List<Movie> movies1 = new ArrayList<>();
        movies1.add(new Movie("https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg"));
        movies1.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));
        movies1.add(new Movie("https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg"));
        movies1.add(new Movie("https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg"));
        movies1.add(new Movie("https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg"));
        movies1.add(new Movie("https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg"));
        movies1.add(new Movie("https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg"));
        movies1.add(new Movie("https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg"));
        movies1.add(new Movie("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg"));
        mockLista.add(new MovieCategory("Drama", movies));
        mockLista.add(new MovieCategory("Vestern", movies1));
        mockLista.add(new MovieCategory("asdkjasndjkahsd", movies));
        return mockLista;
    }
}

