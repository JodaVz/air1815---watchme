package hr.foi.watchme.Interfaces;

import hr.foi.watchme.POJO.Movie;

public interface MovieDetailsInterface {
    Movie getMovieById();
    void movieClicked(Movie m);
}
