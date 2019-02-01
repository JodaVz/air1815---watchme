package hr.foi.watchme.Interfaces;

import java.util.List;

import com.example.pojo.MovieCategory;
import com.example.pojo.Movie;

public interface MoviesInterface {
    //Used to get list of all movies
    public List<Movie> getMovieList();
    //Used to get list of movies by category
    public List<MovieCategory> getAllMoviesByCategories();
}
