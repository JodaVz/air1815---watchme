package hr.foi.watchme.Interfaces;

import android.support.v4.app.Fragment;

import java.util.List;

import hr.foi.watchme.POJO.Movie;

public interface CatalogInterface {
    void CategoryClicked(String name, List<Movie> movies, String fragment);
}
