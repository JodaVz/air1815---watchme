package hr.foi.watchme.Interfaces;

import java.util.List;

import hr.foi.watchme.POJO.MovieCategory;
import hr.foi.watchme.POJO.Movie;

public interface MoviesInterface {
    //Used to get list of all movies
    public List<Movie> getMovieList();
    //Used to get list of movies by category
    public List<MovieCategory> getAllMoviesByCategories();
}
