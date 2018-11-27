package hr.foi.watchme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.player.PlayerActivity;

public class MovieDetails extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ImageView moviePosterFront = findViewById(R.id.iv_movie_poster_front);
        moviePosterFront.setOnClickListener(this);
    }

    @Override
    public void onClick(View viewMovieDetails) {
        switch (viewMovieDetails.getId()){
            case R.id.iv_movie_poster_front:
                Intent intentPlayerMovieDetails = new Intent(this, PlayerActivity.class);
                startActivity(intentPlayerMovieDetails);
                break;
            default:
                break;
        }
    }
}