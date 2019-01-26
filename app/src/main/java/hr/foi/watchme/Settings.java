package hr.foi.watchme;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Locale;

public class Settings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch aSwitch;

    public void setLanguage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = preferences.getString("pref_lang", "en");
        Configuration config = new Configuration(context.getResources().getConfiguration());
        config.setLocale(new Locale(lang));
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        aSwitch = findViewById(R.id.settings_view_switch);
        aSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            aSwitch.setText("List view");
            //TODO uključi modul za prikaz kataloga u listi
        }
        else{
            aSwitch.setText("Grid view");
            //TODO uključi modul za prikaz kataloga u gridu
        }
    }
}
