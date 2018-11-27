package hr.foi.watchme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.player.PlayerActivity;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie_menu_by_category);

        //Button buttonLogin = findViewById(R.id.confirm_login);
        //Button buttonRegistration = findViewById(R.id.confirm_registration);
        //buttonLogin.setOnClickListener(this);
        //buttonRegistration.setOnClickListener(this);
    }

    @Override
    public void onClick(View viewLogin) {
        /*switch (viewLogin.getId()){
            case R.id.confirm_login:
                Intent intentLogin = new Intent(this, MainActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.confirm_registration:
                Intent intentTestPlayer = new Intent(this, PlayerActivity.class);
                startActivity(intentTestPlayer);
                break;
            default:
                break;
        }*/
    }
}
