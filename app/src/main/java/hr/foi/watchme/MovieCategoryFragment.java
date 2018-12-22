package hr.foi.watchme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.foi.watchme.WebServiceApi.CategoryAdapter;
import hr.foi.watchme.WebServiceApi.POJO.Movie;

public class MovieCategoryFragment extends Fragment {

    private List<Movie> filmovi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewMain = inflater.inflate(R.layout.fragment_category, container, false);
        return viewMain;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO u neku metodu (On ActivityCreateed ili sličnu - pogledati živtni ciklus fragmenta) postaviti kod koji instancira adapter i proslijeđuje mu listu filmova RIJEŠENO!
        //TODO ovaj fragment mora kroz konstruktor ili metodu getInstance primiti listu filmova i naziv kategorije.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = getView().findViewById(R.id.categoryRecycler);
        myList.setLayoutManager(layoutManager);

        CategoryAdapter adapter = new CategoryAdapter(filmovi, getActivity());
        myList.setAdapter(adapter);
    }
}
