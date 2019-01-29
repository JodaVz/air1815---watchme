package hr.foi.watchme.FragmentAssets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import hr.foi.watchme.Adapters.GridAdapter;
import hr.foi.watchme.Interfaces.CategoryDetailsInterface;
import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class GridViewFragment extends Fragment implements CategoryDetailsInterface {

    MovieDetailsInterface listenerActivity;
    String categoryNameString;
    ArrayList<Movie> filmovi;

    String[] movieUrls;
    String[] names;
    TextView categoryName;
    GridView gridView;
    public View viewMain;

    public static GridViewFragment newInstance(String catName, ArrayList<Movie> movies) {
        GridViewFragment myFragment = new GridViewFragment();

        Bundle args = new Bundle();
        args.putString("category", catName);
        args.putParcelableArrayList("movies", movies);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryNameString = getArguments().getString("category");
        filmovi = getArguments().getParcelableArrayList("movies");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FillWithUrls();
        viewMain = inflater.inflate(R.layout.grid_layout, container, false);
        categoryName = viewMain.findViewById(R.id.categoryName_grid);
        gridView = viewMain.findViewById(R.id.gridView);
        GridAdapter gridAdapter = new GridAdapter(filmovi, listenerActivity, getActivity(), movieUrls, names);
        gridView.setAdapter(gridAdapter);
        return viewMain;
    }

    public void FillWithUrls() {

        movieUrls = new String[filmovi.size()];
        names = new String[filmovi.size()];

        for (int i = 0; i < filmovi.size(); i++) {
            movieUrls[i] = filmovi.get(i).getCoverPhoto();
            names[i] = filmovi.get(i).getName();
        }
        Log.d("POLJE URLA::", Integer.toString(movieUrls.length));
        for (int i = 0; i < movieUrls.length; i++) {
            Log.d("ELEMENT " + i + " : ", movieUrls[i]);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MovieDetailsInterface) {
            listenerActivity = (MovieDetailsInterface) context;
        }
    }

}
