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

import java.util.List;

import hr.foi.watchme.WebServiceApi.GetDataCallback;
import hr.foi.watchme.WebServiceApi.POJO.Movie;
import hr.foi.watchme.WebServiceApi.WatchMeWebServiceCaller;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    public List<Movie> movieList;

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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_movie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ViewPagerMovies()).commit();
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
}

