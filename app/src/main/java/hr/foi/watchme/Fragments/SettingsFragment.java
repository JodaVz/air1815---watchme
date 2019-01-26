package hr.foi.watchme.Fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.LinearLayout;

import hr.foi.watchme.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    LinearLayout layoutContainer;

    public SettingsFragment(){

    }

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }

}
