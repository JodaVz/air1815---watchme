package hr.foi.watchme.Interfaces;

import java.util.ArrayList;

import hr.foi.watchme.POJO.Movie;

public interface MovieDetailsInterface {
    void categoryClicked(String catName, ArrayList<Movie> movies);
    void movieClicked(Movie m);
}
