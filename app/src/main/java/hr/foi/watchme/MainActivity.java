package hr.foi.watchme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import hr.foi.watchme.FragmentAssets.GridView;
import hr.foi.watchme.FragmentAssets.MovieViewPager;
import hr.foi.watchme.Fragments.MovieDetails;
import hr.foi.watchme.Fragments.MovieFragment;
import hr.foi.watchme.Fragments.SettingsFragment;
import hr.foi.watchme.Interfaces.CatalogInterface;
import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.Interfaces.MoviesInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.POJO.MovieCategory;
import hr.foi.watchme.POJO.User;
import hr.foi.watchme.WebServiceApi.WatchMeWebServiceCaller;
import hr.foi.watchme.WebServiceApi.WebServiceInterfaces.GetDataCallback;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MoviesInterface, MovieDetailsInterface, CatalogInterface {

    private DrawerLayout drawer;
    public static List<Movie> movieList;
    public static List<MovieCategory> categoryList;
    public static List<MovieCategory> filteredCategoryList;
    public static Integer userId;
    public String userEmail;

    public int movieId;
    public Movie movie;

    boolean listView;

    public Boolean switchPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            userEmail = extras.getString("userEmail");
        }

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
                TypeToken<List<MovieCategory>> token = new TypeToken<List<MovieCategory>>() {
                };
                //TODO prilagoditi JSON da jedna stavka polja izgleda kao uređeni par (ime kategorije, polje filmova) RIJEŠENO!
                categoryList = gson.fromJson(dataResponse, token.getType());
                /*
                for(MovieCategory m: categoryList){
                    if(m.getMovies() != null){
                        filteredCategoryList.add(m);
                    }
                }*/
            }
        });

        webServiceCaller.getAllUsers(new GetDataCallback() {
            @Override
            public void onGetData(String dataResponse) {
                Gson gson = new Gson();
                TypeToken<List<User>> token = new TypeToken<List<User>>() {
                };
                List<User> users = gson.fromJson(dataResponse, token.getType());
                for(User user : users){
                    if (user.email.equals(userEmail)){
                        userId = user.getId();
                        break;
                    }
                }
            }
        });

        //
        // preferences
        //

        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        switchPref = sharedPref.getBoolean
                (Settings.KEY_PREF_EXAMPLE_SWITCH, false);
        Toast.makeText(this, switchPref.toString(), Toast.LENGTH_SHORT).show();
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
                        new MovieDetails()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
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
        return categoryList;
    }


    @Override
    public Movie getMovieById() {
        for (Movie m : movieList) {
            if (m.getID() == movieId) {
                movie = m;
                break;
            } else movie = null;
        }
        return movie;
    }

    @Override
    public void movieClicked(Movie m) {
        MovieDetails movieDetails = new MovieDetails();
        Bundle arguments = new Bundle();
        arguments.putParcelable( "movie" , m);
        movieDetails.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                movieDetails).addToBackStack("movieDetails").commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void CategoryPreview(Boolean b, String name) {
        List<Movie> moviesInCategory = null;
        if(b){
            GridView gridView = new GridView();
            for(MovieCategory movieCategory: categoryList){
                if(movieCategory.getName()== name){
                    moviesInCategory = movieCategory.getMovies();
                }
            }
            MovieCategory movies = new MovieCategory(name, moviesInCategory);
            Bundle arguments = new Bundle();
            arguments.putParcelable("movies", movies);
            gridView.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    gridView).addToBackStack("gridLayout").commit();
        }

    }

    @Override
    public void CategoryClicked(String name, List<Movie> movies) {
        List<Movie> moviesInCategory = null;
        GridView gridView = new GridView();
        for(MovieCategory movieCategory: categoryList){
            if(movieCategory.getName()== name){
                moviesInCategory = movieCategory.getMovies();
            }
        }
        MovieCategory movies1 = new MovieCategory(name, moviesInCategory);
        Bundle arguments = new Bundle();
        arguments.putParcelable("movies", movies1);
        gridView.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                gridView).addToBackStack("gridLayout").commit();
    }
}


