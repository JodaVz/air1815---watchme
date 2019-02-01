package hr.foi.watchme.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import hr.foi.watchme.R;

public class SettingsFragment extends Fragment{

    ArrayList<CategoryDetailsInterface> fragmentList;
    RadioGroup radioGroup;
    private static final String SP_DISPLAY_MODE = "displayMode";


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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View selectedButton = group.findViewById(checkedId);
                int radioId = radioGroup.indexOfChild(selectedButton);
                RadioButton btn = (RadioButton) group.getChildAt(radioId);
                String selection = (String) btn.getText();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                editor.putString(SP_DISPLAY_MODE, selection);
                editor.apply();
            }
        });
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

    public static CategoryDetailsInterface getDisplayMode(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String restoredText = prefs.getString(SP_DISPLAY_MODE, ListViewFragment.FRAG_NAME);
        if (restoredText == null)
            return null;
        if(restoredText.equals(ListViewFragment.FRAG_NAME))
            return new ListViewFragment();
        else if (restoredText.equals(GridViewFragment.FRAG_NAME))
            return new GridViewFragment();
        return null;
    }

}
