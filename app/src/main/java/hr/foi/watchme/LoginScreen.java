package hr.foi.watchme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.player.PlayerActivity;
import com.google.gson.Gson;


import hr.foi.watchme.WebServiceApi.GetDataCallback;
import hr.foi.watchme.WebServiceApi.User;
import hr.foi.watchme.WebServiceApi.WatchMeWebServiceCaller;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    private String textEmail;
    private String textPassword;
    private EditText textEmailId;
    private EditText textPasswordId;
    public User user;


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
                final Intent intentLogin = new Intent(this, MainActivity.class);

                WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();
                webServiceCaller.getAllUsers(new GetDataCallback() {
                    @Override
                    public void onGetUserData(String userResponse) {
                        Gson gson = new Gson();
                        user = gson.fromJson(userResponse,User.class);
                        if (user.email.equals(textEmail)  && user.Password.equals(textPassword)) {
                            startActivity(intentLogin);
                        }else{
                            Context context = getApplicationContext();
                            CharSequence text = "Krivi login podaci!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                });
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
