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

import hr.foi.watchme.R;

public class ListView extends Fragment {
    LinearLayout catContainer;
    TextView categoryName;
    LinearLayout moviesContainer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewMain = inflater.inflate(R.layout.list_layout, container, false);
        catContainer = viewMain.findViewById(R.id.fragment_container);
        return viewMain;
    }

    @Override
    public void onResume() {
        super.onResume();
        categoryName = getView().findViewById(R.id.categoryName);
        moviesContainer = getView().findViewById(R.id.MovieByCategoryContainer);
    }
}
