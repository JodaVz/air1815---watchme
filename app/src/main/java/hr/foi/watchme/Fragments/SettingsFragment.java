package hr.foi.watchme.Fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import hr.foi.watchme.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    public SettingsFragment(){

    }

    //Setting preferences
    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }

}
