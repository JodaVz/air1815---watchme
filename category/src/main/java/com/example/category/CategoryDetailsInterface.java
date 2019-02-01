package com.example.category;


import android.support.v4.app.Fragment;

import com.example.pojo.Movie;

import java.util.ArrayList;

public interface CategoryDetailsInterface {
    /*
    This interface helps us with modularity of
    switching between list and grid view for category catalog.
     */
    String getName();
    Fragment getFragment();
    CategoryDetailsInterface newInstance(String categoryName, ArrayList<Movie> movies);

}
