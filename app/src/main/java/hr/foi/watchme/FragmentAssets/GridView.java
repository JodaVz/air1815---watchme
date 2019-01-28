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

import java.util.List;

import hr.foi.watchme.Interfaces.CatalogInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class GridView extends Fragment implements CatalogInterface{
    LinearLayout catContainer;

    String categoryNameString;
    List<Movie> filmovi;

    TextView categoryName;
    public View itemView;

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

    public void bind(List<Movie> m, String name){

          categoryName.setText(name);

    }

    @Override
    public void CategoryClicked(String name, List<Movie> movies, String fragment) {
            if (fragment == "FG"){
                categoryNameString = name;
                filmovi = movies;
            }else{

            }
    }
}
