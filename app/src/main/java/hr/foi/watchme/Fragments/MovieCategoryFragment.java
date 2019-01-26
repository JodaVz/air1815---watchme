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
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import hr.foi.watchme.Adapters.CategoryAdapter;
import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class MovieCategoryFragment extends Fragment {

    private String catName;
    private List<Movie> filmovi;
    MovieDetailsInterface listenerActivity;
    public TextView itemView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewMain = inflater.inflate(R.layout.fragment_category, container, false);

        catName = getArguments().getString("NAME");
        TextView tvCatName = viewMain.findViewById(R.id.output_category_name);
        tvCatName.setText(catName);

        filmovi = (List<Movie>) getArguments().getSerializable("MOVIES");
        this.itemView = tvCatName;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), catName, Toast.LENGTH_SHORT).show();

            }
        });
        return viewMain;
    }

    public static MovieCategoryFragment newInstance(String categoryName, List<Movie> movies) {
        MovieCategoryFragment fragment = new MovieCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NAME", categoryName);
        bundle.putSerializable("MOVIES", (Serializable) movies);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO u neku metodu (On ActivityCreateed ili sličnu - pogledati živtni ciklus fragmenta) postaviti kod koji instancira adapter i proslijeđuje mu listu filmova RIJEŠENO!
        //TODO ovaj fragment mora kroz konstruktor ili metodu getInstance primiti listu filmova i naziv kategorije. RIJEŠENO!

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = getView().findViewById(R.id.categoryRecycler);
        myList.setLayoutManager(layoutManager);

        CategoryAdapter adapter = new CategoryAdapter(filmovi, getActivity(), listenerActivity);
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
