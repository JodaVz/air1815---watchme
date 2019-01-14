package hr.foi.watchme;

import java.util.List;

import hr.foi.watchme.WebServiceApi.POJO.Movie;

public interface MoviesInterface {
    public List<Movie> getMovieList();
    public List<MovieCategory> getAllMoviesByCategories();
}
