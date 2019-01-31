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
import com.example.category.CategoryDetailsInterface;
import hr.foi.watchme.Interfaces.MovieDetailsInterface;
import hr.foi.watchme.POJO.Movie;
import hr.foi.watchme.R;

public class ListViewFragment extends Fragment implements CategoryDetailsInterface {
    TextView categoryName;
    MovieDetailsInterface listenerActivity;

    String categoryNameString;
    ArrayList<Movie> filmovi;

    public View viewMain;

    //Creating bundle for new instance
    public static ListViewFragment newInstance(String catName, ArrayList<Movie> movies) {
        ListViewFragment myFragment = new ListViewFragment();

        Bundle args = new Bundle();
        args.putString("category", catName);
        args.putParcelableArrayList("movies", movies);
        myFragment.setArguments(args);

        return myFragment;
    }

    //Getting values from bundle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryNameString = getArguments().getString("category");
        filmovi = getArguments().getParcelableArrayList("movies");
    }

    //Inflating list_layout xml and setting category name on inflated layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMain = inflater.inflate(R.layout.list_layout, container, false);
        categoryName = viewMain.findViewById(R.id.categoryName);
        categoryName.setText(categoryNameString);


        return viewMain;
    }

    //Setting layoutManager and adapter on vertical recycle view for displaying movies
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        RecyclerView myList = getView().findViewById(R.id.MovieByCategoryContainer);
        myList.setLayoutManager(layoutManager);

        LinearAdapter adapter = new LinearAdapter(filmovi, getActivity(), listenerActivity);
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
