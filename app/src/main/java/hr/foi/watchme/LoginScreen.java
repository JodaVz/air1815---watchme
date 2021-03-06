package hr.foi.watchme;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hr.foi.watchme.WebServiceApi.WebServiceInterfaces.GetStatusCallback;
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
        switch (viewLogin.getId()) {
            case R.id.confirm_login:
                textEmail = textEmailId.getText().toString();
                textPassword = textPasswordId.getText().toString();

                final WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();

                //Sending user input to webservice with POST
                webServiceCaller.email = textEmail;
                webServiceCaller.password = textPassword;
                webServiceCaller.postUserLogin();

                //Delaying GET from webservice; giving webservice time to handle POST
                Handler handler = new Handler();
                Runnable r = new Runnable() {public void run() { userLogin(webServiceCaller); }};
                handler.postDelayed(r, 200);
                break;
            case R.id.confirm_registration:
                Intent intentRegistration = new Intent(this, RegisterScreen.class);
                startActivity(intentRegistration);
                break;
            default:
                break;
        }
    }

    //Getting response code from webservice to user login POST and handling further operations
    public void userLogin(WatchMeWebServiceCaller webServiceCaller){
        final Intent intentLogin = new Intent(this, MainActivity.class);
        intentLogin.putExtra("userEmail",textEmail);

        //Manage webservice response; 200 user can enter, anything else and user cannot enter
        webServiceCaller.getUserLogin(new GetStatusCallback() {
            @Override
            public void onGetCode(int statusCode) {
                if (statusCode == 200) {
                    startActivity(intentLogin);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Krivi login podaci!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
