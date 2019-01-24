package hr.foi.watchme.POJO;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.player.PlayerActivity;
import com.squareup.picasso.Picasso;

import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.R;

public class MovieDetails extends Fragment implements View.OnClickListener {

    private Movie movie;
    private MovieDetailsInterface mListenerActivity;
    LinearLayout catContainer;

    ImageView moviePosterBack;
    ImageView moviePosterFront;
    TextView movieTitle;
    TextView movieYear;
    TextView movieRating;
    TextView movieAbout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FetchMovie();

        View viewMain = inflater.inflate(R.layout.activity_movie_details, container, false);
        catContainer = viewMain.findViewById(R.id.fragment_container);

        ImageView moviePosterFront = getView().findViewById(R.id.iv_movie_poster_front);
        moviePosterFront.setOnClickListener(this);

        return viewMain;
    }

    public void FetchMovie() {
        movie = mListenerActivity.getMovieById();

        bind(movie);
    }

    public void bind(Movie m) {
        movieTitle.setText(m.getName());
        movieYear.setText(m.getReleaseDate());
        movieAbout.setText(m.getEpisode());
        movieRating.setText(m.getFeedback());
        Picasso.get()
                .load(m.getCoverPhoto())
                .resize(120, 160)
                .into(moviePosterFront);
        Picasso.get()
                .load(m.getCoverPhoto())
                .into(moviePosterBack);
    }

    @Override
    public void onClick(View viewMovieDetails) {
        switch (viewMovieDetails.getId()) {
            case R.id.iv_movie_poster_front:
                Intent intentPlayerMovieDetails = new Intent(getActivity(), PlayerActivity.class);
                startActivity(intentPlayerMovieDetails);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListenerActivity = (MovieDetailsInterface) getActivity();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}