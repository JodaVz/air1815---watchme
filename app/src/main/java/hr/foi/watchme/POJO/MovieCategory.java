package hr.foi.watchme.POJO;

import java.util.List;

import hr.foi.watchme.POJO.Movie;

//TODO refaktorirati ovu klasu tako da se stavi u pravi paket
public class MovieCategory{
    private String name;
    private List<Movie> movies;

    public MovieCategory(String catName, List<Movie> movies){
        this.name = catName;
        this.movies = movies;
    }



    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
