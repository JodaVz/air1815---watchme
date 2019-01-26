package hr.foi.watchme.Interfaces;

import java.util.List;

import hr.foi.watchme.POJO.Movie;

public interface CatalogInterface {
    void CategoryPreview(Boolean b, String name);
    void CategoryClicked(String name, List<Movie> movies);
}
