package hr.foi.watchme.FragmentAssets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hr.foi.watchme.Adapters.LinearAdapter;
import hr.foi.watchme.Interfaces.CategoryDetailsInterface;
import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class ListViewFragment extends Fragment implements CategoryDetailsInterface {
    TextView categoryName;
    MovieDetailsInterface listenerActivity;

    String categoryNameString;
    ArrayList<Movie> filmovi;

    public View viewMain;

    public static ListViewFragment newInstance(String catName, ArrayList<Movie> movies) {
        ListViewFragment myFragment = new ListViewFragment();

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
        viewMain = inflater.inflate(R.layout.list_layout, container, false);
        categoryName = viewMain.findViewById(R.id.categoryName);
        categoryName.setText(categoryNameString);


        return viewMain;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        RecyclerView myList = getView().findViewById(R.id.MovieByCategoryContainer);
        myList.setLayoutManager(layoutManager);

        LinearAdapter adapter = new LinearAdapter(filmovi, getActivity(), listenerActivity);
        myList.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MovieDetailsInterface) {
            listenerActivity = (MovieDetailsInterface) context;
        }
    }

}
