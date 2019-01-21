package hr.foi.watchme.Interfaces;

import java.util.List;

import hr.foi.watchme.POJO.MovieCategory;
import hr.foi.watchme.POJO.Movie;

public interface MoviesInterface {
    public List<Movie> getMovieList();
    public List<MovieCategory> getAllMoviesByCategories();
    public Movie getMovieById(Integer id);
}
