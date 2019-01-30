package hr.foi.watchme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hr.foi.watchme.WebServiceApi.WatchMeWebServiceCaller;
import hr.foi.watchme.WebServiceApi.WebServiceInterfaces.GetStatusCallback;

public class RegisterScreen extends AppCompatActivity implements View.OnClickListener {

    private String textEmail;
    private String textPassword;
    private String textName;
    private String textSurname;
    private EditText textEmailId;
    private EditText textPasswordId;
    private EditText textNameId;
    private EditText textSurnameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonRegister = findViewById(R.id.register);
        textEmailId = findViewById(R.id.input_register_email_adress);
        textPasswordId = findViewById(R.id.input_register_password);
        textNameId = findViewById(R.id.input_register_name);
        textSurnameId = findViewById(R.id.input_register_surname);
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View viewRegister) {
        switch (viewRegister.getId()){
            case R.id.register:
                textEmail = textEmailId.getText().toString();
                textPassword = textPasswordId.getText().toString();
                textName = textNameId.getText().toString();
                textSurname = textSurnameId.getText().toString();


                final WatchMeWebServiceCaller webServiceCaller = new WatchMeWebServiceCaller();
                webServiceCaller.email = textEmail;
                webServiceCaller.password = textPassword;
                webServiceCaller.name = textName;
                webServiceCaller.surname = textSurname;
                webServiceCaller.postUserRegistration();

                Handler handler = new Handler();
                Runnable r = new Runnable() {
                public void run() { registerUser(webServiceCaller); }};
                handler.postDelayed(r, 200);
                break;
            default:
                break;
        }
    }

    public void registerUser(WatchMeWebServiceCaller webServiceCaller){
        final Intent intentRegister = new Intent(this, LoginScreen.class);

        webServiceCaller.getRegistrationSuccess(new GetStatusCallback() {
            @Override
            public void onGetCode(int statusCode) {
                if (statusCode == 200) {
                    Context context = getApplicationContext();
                    CharSequence text = "Uspješna registracija!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    startActivity(intentRegister);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Već zauzet email!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
