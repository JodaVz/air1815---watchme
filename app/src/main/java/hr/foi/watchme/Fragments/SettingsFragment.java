package hr.foi.watchme.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.category.CategoryDetailsInterface;

import java.util.ArrayList;

import hr.foi.watchme.FragmentAssets.GridViewFragment;
import hr.foi.watchme.FragmentAssets.ListViewFragment;
import hr.foi.watchme.Interfaces.SettingsInterface;
import hr.foi.watchme.R;

public class SettingsFragment extends Fragment{

    ArrayList<CategoryDetailsInterface> fragmentList;
    RadioGroup radioGroup;
    SettingsInterface settingsInterface;
    CategoryDetailsInterface categoryDetailsInterface;

    public SettingsFragment() {

    }

    //Setting preferences


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManagment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewMain = inflater.inflate(R.layout.settings, container, false);
        return viewMain;
    }

    @Override
    public void onResume() {
        super.onResume();
        radioGroup = getActivity().findViewById(R.id.radio_gaga);
        CreateRadioButtons();

        for(int i=1 ; i<=fragmentList.size(); i++) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    //getActivity().findViewById(checkedId);
                }
            });
        }
    }

    private void FragmentManagment() {
        fragmentList = new ArrayList<CategoryDetailsInterface>();
        fragmentList.add(new GridViewFragment());
        fragmentList.add(new ListViewFragment());
    }

    private void CreateRadioButtons() {
        for (CategoryDetailsInterface frag : fragmentList) {
            RadioButton rbn = new RadioButton(getContext());
            rbn.setId(View.generateViewId());
            rbn.setText(frag.getName());
            radioGroup.addView(rbn);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
