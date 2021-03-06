package hr.foi.watchme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.category.CategoryDetailsInterface;
import com.example.pojo.Movie;
import com.example.pojo.MovieCategory;
import com.example.pojo.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hr.foi.watchme.FragmentAssets.GridViewFragment;
import hr.foi.watchme.FragmentAssets.ListViewFragment;
import hr.foi.watchme.FragmentAssets.MovieViewPager;
import hr.foi.watchme.Fragments.MovieDetails;
import hr.foi.watchme.Fragments.MovieFragment;
import hr.foi.watchme.Fragments.SettingsFragment;
import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.Interfaces.MoviesInterface;
import hr.foi.watchme.Interfaces.SettingsInterface;
import hr.foi.watchme.WebServiceApi.WatchMeWebServiceCaller;
import hr.foi.watchme.WebServiceApi.WebServiceInterfaces.GetDataCallback;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MoviesInterface, MovieDetailsInterface {

    private DrawerLayout drawer;
    public static List<Movie> movieList;
    public static List<MovieCategory> categoryList;
    public static List<MovieCategory> filteredCategoryList;
    public static Integer userId;
    public String userEmail;
    public String userName;
    public TextView username;
    public TextView email;
    public Movie movie;
    public List<User> users;

    SettingsInterface settingsInterfaceListener;

    public Boolean switchPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting user email from login
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userEmail = extras.getString("userEmail");
        }

        //Setting navigation view
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MovieFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_popular);
        }

        //Getting movie, category and user data from Azure webservices
        WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();

        getMovies(webServiceCaller);
        getCategories(webServiceCaller);
        getUsers(webServiceCaller);
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                setUsers(users);
            }
        };
        handler.postDelayed(r, 200);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Getting users preference for catalog view
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        switchPref = sharedPref.getBoolean
                (Settings.KEY_PREF_EXAMPLE_SWITCH, false);
    }

    //Opening new fragments on different item selection in navigation view
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        switch (item.getItemId()) {
            case R.id.nav_movie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MovieViewPager()).commit();
                navigationView.setCheckedItem(R.id.nav_movie);
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_settings);
                break;
        }
        //After an item is selected, automatically closes drawer menu
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    //By pressing back button on phone, drawer menu closes(if it's opened)
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
        return filteredCategoryList;
    }

    //Sending data of pressed movie in a bundle
    @Override
    public void movieClicked(Movie m) {
        MovieDetails movieDetails = new MovieDetails();
        Bundle arguments = new Bundle();
        arguments.putParcelable("movie", m);
        movieDetails.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                movieDetails).addToBackStack("movieDetails").commit();
    }

    //Getting users preference for catalog view and setting catalog view accordingly
    @Override
    public void categoryClicked(String name, ArrayList<Movie> movies) {
        //TODO ova aktivnost samo dohvaća postavke kroz sučelje koje je implementirano u postavkama.
        //Settings activity će dinamički skužit koji sve moduli postoje i vratiti tip fragmenta kroz metodu sučelja
        CategoryDetailsInterface frag = SettingsFragment.getDisplayMode(this);
        if(frag == null)
            return;
        frag = frag.newInstance(name, movies);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                (Fragment) frag).addToBackStack("gridLayout").commit();

    }

    //Returning selected item in settings
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

    //Parsing date from webservice to a more readable format
    public void parseDate(Movie m) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("yyyy");
        String startDateStr = m.getReleaseDate();
        Date date;
        try {
            date = inputFormat.parse(startDateStr);
            String startDateStrNewFormat = outputFormat.format(date);
            m.setReleaseDate(startDateStrNewFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Getting movie objects in a list from webservice
    public void getMovies(WatchMeWebServiceCaller webServiceCaller) {
        webServiceCaller.getMovies(new GetDataCallback() {
            @Override
            public void onGetData(String dataResponse) {
                //Parsing JSON response
                Gson gson = new Gson();
                TypeToken<List<Movie>> token = new TypeToken<List<Movie>>() {
                };
                movieList = gson.fromJson(dataResponse, token.getType());
            }
        });
    }

    //Getting a list of categories, category contains a list of movies from that category, from webservice
    public void getCategories(WatchMeWebServiceCaller webServiceCaller) {
        webServiceCaller.getCategories(new GetDataCallback() {
            @Override
            public void onGetData(String dataResponse) {
                //Parsing JSON response
                Gson gson = new Gson();
                TypeToken<List<MovieCategory>> token = new TypeToken<List<MovieCategory>>() {
                };
                categoryList = gson.fromJson(dataResponse, token.getType());

                //Parsing date to a readable value
                for (MovieCategory category : categoryList) {
                    for (Movie movie : category.getMovies()) {
                        parseDate(movie);
                    }
                }

                //Removing empty categories from the list
                filteredCategoryList = new ArrayList<>();

                for (MovieCategory m : categoryList) {
                    if (!m.getMovies().isEmpty()) {
                        filteredCategoryList.add(m);
                    }
                }
            }
        });
    }

    //Getting user data from webservice
    public void getUsers(WatchMeWebServiceCaller webServiceCaller) {
        webServiceCaller.getAllUsers(new GetDataCallback() {
            @Override
            public void onGetData(String dataResponse) {
                //Parsing JSON response
                Gson gson = new Gson();
                TypeToken<List<User>> token = new TypeToken<List<User>>() {
                };
                users = gson.fromJson(dataResponse, token.getType());

            }
        });
    }

    public void setUsers(List<User> users){
        for ( User user : users) {
            if (user.email.equals(userEmail)) {
                //Setting user name, surname and email in navigation header
                userName = user.getName() + " " + user.getSurname();
                userId = user.getId();
/*
                username = findViewById(R.id.username);
                username.setText(userName);

                email = findViewById(R.id.emailAdress);
                email.setText(userEmail);
                break;*/
            }
        }
    }
}



