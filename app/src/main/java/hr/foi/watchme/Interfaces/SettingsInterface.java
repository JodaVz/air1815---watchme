package hr.foi.watchme.Interfaces;

import com.example.category.CategoryDetailsInterface;
import com.example.pojo.Movie;

import java.util.ArrayList;

public interface SettingsInterface {
    CategoryDetailsInterface getFragment(String catName, ArrayList<Movie> movies);
    String getFragmentName(String fragName);
}
