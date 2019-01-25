package hr.foi.watchme.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.player.PlayerActivity;
import com.squareup.picasso.Picasso;

import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.Interfaces.MovieIdInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class MovieDetails extends Fragment implements View.OnClickListener {

    private Movie movie;
    private int movieId;
    private MovieDetailsInterface mListenerActivity;
    private MovieIdInterface miListenerActivity;
    LinearLayout catContainer;
    Context context;

    ImageView moviePosterBack;
    ImageView moviePosterFront;
    TextView movieTitle;
    TextView movieYear;
    TextView movieRating;
    TextView movieAbout;

    Button viewMorebutton;
    ImageButton likeButton;
    ImageButton dislikeButton;
    boolean likeIsClicked = false;
    boolean dislikeIsClicked = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewMain = inflater.inflate(R.layout.activity_movie_details, container, false);
        catContainer = viewMain.findViewById(R.id.fragment_container);

        movie = this.getArguments().getParcelable("movie");

        return viewMain;
    }

    @Override
    public void onResume() {
        super.onResume();
        movieTitle = getView().findViewById(R.id.output_movie_details_title);
        moviePosterFront = getView().findViewById(R.id.iv_movie_poster_front);
        moviePosterBack = getView().findViewById(R.id.iv_movie_poster_back);
        movieAbout = getView().findViewById(R.id.output_movie_details_about);
        movieRating = getView().findViewById(R.id.output_movie_details_rating);
        movieYear = getView().findViewById(R.id.output_movie_details_year);
        likeButton = getView().findViewById(R.id.input_movie_details_like);
        dislikeButton = getView().findViewById(R.id.input_movie_details_dislike);
        bind(movie);

        SetViewMoreButtonListener();
        SetLikeButtonListener();
        SetDislikeButtonListener();

    }

    public void bind(Movie m) {
        movieTitle.setText(m.getName());
        movieYear.setText(m.getReleaseDate());
        movieAbout.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages," +
                " and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        movieRating.setText("" + m.getFeedback());

        Picasso.get()
                .load(m.getCoverPhoto())
                .resize(120, 160)
                .into(moviePosterFront);
        Picasso.get()
                .load(m.getCoverPhoto())
                .resize(600, 300)
                .centerCrop()
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
        this.context = context;

        try {
            miListenerActivity = (MovieIdInterface) getActivity();
            mListenerActivity = (MovieDetailsInterface) getActivity();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SetViewMoreButtonListener() {
        viewMorebutton = getView().findViewById(R.id.action_movie_details_show_more);
        viewMorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewMorebutton.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_show_more).getConstantState()) {
                    movieAbout.setMaxLines(Integer.MAX_VALUE);
                    viewMorebutton.setBackground(getResources().getDrawable(R.drawable.ic_show_less));
                } else {
                    movieAbout.setMaxLines(2);//your TextView
                    viewMorebutton.setBackground(getResources().getDrawable(R.drawable.ic_show_more));
                }
            }
        });
    }

    public void SetLikeButtonListener() {
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeIsClicked = true;

                if (likeIsClicked) {
                    likeButton.setClickable(false);
                    dislikeButton.setVisibility(View.GONE);
                    Toast.makeText(context, "You like " + movieTitle.getText(), Toast.LENGTH_SHORT).show();

                }
                else {
                    likeButton.setClickable(true);
                }
            }
        });
    }

    public void SetDislikeButtonListener() {
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dislikeIsClicked = true;

                if (dislikeIsClicked) {
                    dislikeButton.setClickable(false);
                    likeButton.setVisibility(View.GONE);
                    Toast.makeText(context, "You dislike " + movieTitle.getText(), Toast.LENGTH_SHORT).show();

                }
                else {
                    dislikeButton.setClickable(true);
                }
            }
        });
    }
}