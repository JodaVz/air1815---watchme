package hr.foi.watchme.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.player.PlayerActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hr.foi.watchme.MainActivity;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;
import hr.foi.watchme.WebServiceApi.WatchMeWebServiceCaller;
import hr.foi.watchme.WebServiceApi.WebServiceInterfaces.GetDataCallback;
import hr.foi.watchme.WebServiceApi.WebServiceInterfaces.GetStatusCallback;

public class MovieDetails extends Fragment {

    private Movie movie;
    private String commentText;
    private List<String> comments;
    private ListView list;
    LinearLayout catContainer;

    ImageView moviePosterBack;
    ImageView moviePosterFront;
    TextView movieTitle;
    TextView movieYear;
    TextView movieRating;
    TextView movieAbout;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    EditText movieComment;

    Button viewMoreButton;
    Button commentButton;
    ImageButton likeButton;
    ImageButton dislikeButton;
    boolean likeIsClicked = false;
    boolean dislikeIsClicked = false;
    boolean commentIsClicked = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movie = this.getArguments().getParcelable("movie");

        View viewMain = inflater.inflate(R.layout.activity_movie_details, container, false);
        catContainer = viewMain.findViewById(R.id.fragment_container);

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
        movieComment = getView().findViewById(R.id.input_movie_details_comment);
        commentButton = getView().findViewById(R.id.action_movie_details_comment);
        list = getView().findViewById(R.id.movie_details_comment_container);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arrayList);

        list.setAdapter(adapter);
        bind(movie);


        SetViewMoreButtonListener();
        SetLikeButtonListener();
        SetDislikeButtonListener();
        SetPlayerListener();
        setCommentButtonListener();
        AzureSender();
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                AzureListener();
            }
        };
        Runnable rComment = new Runnable() {
            @Override
            public void run() {
                getAllComments();
            }
        };
        handler.postDelayed(r,200);
        handler.postDelayed(rComment, 200);
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

    public void SetPlayerListener() {
        moviePosterFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewMovieDetails) {
                Intent intentPlayerMovieDetails = new Intent(getActivity(), PlayerActivity.class);
                startActivity(intentPlayerMovieDetails);

            }
        });
    }

    public void SetViewMoreButtonListener() {
        viewMoreButton = getView().findViewById(R.id.action_movie_details_show_more);

        viewMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewMoreButton.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_show_more).getConstantState()) {
                    movieAbout.setMaxLines(Integer.MAX_VALUE);
                    viewMoreButton.setBackground(getResources().getDrawable(R.drawable.ic_show_less));
                } else {
                    movieAbout.setMaxLines(2);
                    viewMoreButton.setBackground(getResources().getDrawable(R.drawable.ic_show_more));
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
                } else {
                    likeButton.setClickable(true);
                }
                final WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();
                webServiceCaller.movieId = movie.getID();
                webServiceCaller.userId = MainActivity.userId;
                webServiceCaller.rating = 1;
                webServiceCaller.postUserRating();

                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    public void run() {
                        webServiceCaller.getUserRating(new GetStatusCallback() {
                            @Override
                            public void onGetCode(int statusCode) {
                                if (statusCode == 200) {
                                    Context context = getContext();
                                    CharSequence text = "Uspješno rejtano!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                } else {
                                    Context context = getContext();
                                    CharSequence text = "Pogreška kod rejtanja!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            }
                        });
                    }
                };
                handler.postDelayed(r, 200);
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
                } else {
                    dislikeButton.setClickable(true);
                }
                final WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();
                webServiceCaller.movieId = movie.getID();
                webServiceCaller.userId = MainActivity.userId;
                webServiceCaller.rating = 2;
                webServiceCaller.postUserRating();

                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    public void run() {
                        webServiceCaller.getUserRating(new GetStatusCallback() {
                            @Override
                            public void onGetCode(int statusCode) {
                                if (statusCode == 200) {
                                    Context context = getContext();
                                    CharSequence text = "Uspješno rejtano!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                } else {
                                    Context context = getContext();
                                    CharSequence text = "Pogreška kod rejtanja!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            }
                        });
                    }
                };
                handler.postDelayed(r, 200);
            }
        });
    }

    public void setCommentButtonListener(){
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentIsClicked = true;
                if (commentIsClicked) {
                    commentButton.setClickable(false);
                    commentButton.setBackgroundColor(Color.GRAY);
                } else {
                    commentButton.setClickable(true);
                }

                commentText = movieComment.getText().toString();
                final WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();
                webServiceCaller.userId = MainActivity.userId;
                webServiceCaller.movieId = movie.getID();
                webServiceCaller.comment = commentText;
                webServiceCaller.postUserComment();

                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    public void run() {
                        webServiceCaller.getUserComment(new GetStatusCallback() {
                            @Override
                            public void onGetCode(int statusCode) {
                                if (statusCode == 200) {
                                    Context context = getContext();
                                    CharSequence text = "Uspješno komentirano!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                } else {
                                    Context context = getContext();
                                    CharSequence text = "Pogreška kod komentiranja!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            }
                        });
                    }
                };
                handler.postDelayed(r, 200);
            }
        });
    }

    public void getAllComments(){
        final WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();
        webServiceCaller.getAllComments(new GetDataCallback() {
            @Override
            public void onGetData(String dataResponse) {
                Gson gson = new Gson();
                TypeToken<List<String>> token = new TypeToken<List<String>>() {
                };
                comments = gson.fromJson(dataResponse, token.getType());

                for (String comment : comments){
                    arrayList.add(comment);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void AzureSender(){
        final WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();

        webServiceCaller.userId = MainActivity.userId;
        webServiceCaller.movieId = movie.getID();
        webServiceCaller.postUserMovie();
    }

    public void AzureListener(){
        final WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();
        webServiceCaller.checkUserRating(new GetStatusCallback() {
            @Override
            public void onGetCode(int statusCode) {
                if(statusCode == 200){
                    likeButton.setClickable(false);
                    dislikeButton.setVisibility(View.GONE);
                }
                else if (statusCode == 500){
                    dislikeButton.setClickable(false);
                    likeButton.setVisibility(View.GONE);
                }
                else if(statusCode == 401){
                }
            }
        });
    }

}