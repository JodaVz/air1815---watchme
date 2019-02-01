package hr.foi.watchme.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pojo.Movie;

import java.util.ArrayList;

import hr.foi.watchme.Adapters.CategoryAdapter;
import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.R;

public class MovieCategoryFragment extends Fragment {

    private String catName;
    private ArrayList<Movie> movieList;
    MovieDetailsInterface listenerActivity;

    //Getting data from selected category by getting arguments from bundle
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewMain = inflater.inflate(R.layout.fragment_category, container, false);

        catName = getArguments().getString("NAME");
        TextView tvCatName = viewMain.findViewById(R.id.output_category_name);
        tvCatName.setText(catName);
        tvCatName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerActivity.categoryClicked(catName, movieList);
            }
        });

        movieList = getArguments().getParcelableArrayList("MOVIES");

        return viewMain;
    }

    //Creating bundle on new instance
    public static MovieCategoryFragment newInstance(String categoryName, ArrayList<Movie> movies) {
        MovieCategoryFragment fragment = new MovieCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NAME", categoryName);
        bundle.putParcelableArrayList("MOVIES", movies);
        fragment.setArguments(bundle);

        return fragment;
    }

    //Setting layoutManager and adapter on vertical recycle view for displaying movies
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO u neku metodu (On ActivityCreateed ili sličnu - pogledati živtni ciklus fragmenta) postaviti kod koji instancira adapter i proslijeđuje mu listu filmova RIJEŠENO!
        //TODO ovaj fragment mora kroz konstruktor ili metodu getInstance primiti listu filmova i naziv kategorije. RIJEŠENO!

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = getView().findViewById(R.id.categoryRecycler);
        myList.setLayoutManager(layoutManager);

        CategoryAdapter adapter = new CategoryAdapter(movieList, getActivity(), listenerActivity);
        myList.setAdapter(adapter);
    }

    //Setting listener on MovieDetailsInterface
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MovieDetailsInterface) {
            listenerActivity = (MovieDetailsInterface) context;
        }
    }

}
