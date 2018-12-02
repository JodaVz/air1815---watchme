package hr.foi.watchme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.player.PlayerActivity;

import hr.foi.watchme.WebServiceApi.User;
import hr.foi.watchme.WebServiceApi.WatchMeWebServiceCaller;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    private String textEmail;
    private String textPassword;
    private EditText textEmailId;
    private EditText textPasswordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button buttonLogin = findViewById(R.id.confirm_login);
        Button buttonRegistration = findViewById(R.id.confirm_registration);
        textEmailId = findViewById(R.id.input_login_email_adress);
        textPasswordId = findViewById(R.id.input_login_password);
        buttonLogin.setOnClickListener(this);
        buttonRegistration.setOnClickListener(this);


    }

    @Override
    public void onClick(View viewLogin) {
        switch (viewLogin.getId()){
            case R.id.confirm_login:
                textEmail= textEmailId.getText().toString();
                textPassword = textPasswordId.getText().toString();

                WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();
                webServiceCaller.getAllUsers();


               /* if (webServiceCaller.user.email == textEmail && webServiceCaller.user.Password == textPassword) {
                    Intent intentLogin = new Intent(this, MainActivity.class);
                    startActivity(intentLogin);
                }*/
                break;
            case R.id.confirm_registration:
                Intent intentTestPlayer = new Intent(this, PlayerActivity.class);
                startActivity(intentTestPlayer);
                break;
            default:
                break;
        }
    }
}
