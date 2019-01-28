package hr.foi.watchme.FragmentAssets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.foi.watchme.Interfaces.CategoryDetailsInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class GridViewFragment extends Fragment implements CategoryDetailsInterface {
    LinearLayout catContainer;

    String categoryNameString;
    List<Movie> filmovi;

    TextView categoryName;
    public View itemView;

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
        View viewMain = inflater.inflate(R.layout.grid_layout, container, false);
        catContainer = viewMain.findViewById(R.id.fragment_container);
        return viewMain;
    }

    @Override
    public void onResume() {
        super.onResume();

        categoryName = getActivity().findViewById(R.id.categoryName);
    }


    @Override
    public void showList() {

    }
}
