package hr.foi.watchme.Interfaces;

import java.util.ArrayList;

import com.example.pojo.Movie;

public interface MovieDetailsInterface {
    //Used to get name and list of movies that are in selected category
    void categoryClicked(String catName, ArrayList<Movie> movies);
    //Used to get data from selected movie
    void movieClicked(Movie m);
}
