package hr.foi.watchme.WebServiceApi;

import android.widget.TextView;

import com.google.gson.Gson;

import hr.foi.watchme.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WatchMeWebServiceCaller {
    public User user;
    public String delaware;
    public boolean wait = true;
    Retrofit retrofit;
    private final String baseUrl = "https://watchmeservices20181120093721.azurewebsites.net/";

    public WatchMeWebServiceCaller(){

        OkHttpClient client = new OkHttpClient();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void getAllUsers(){

        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<String> call = serviceCaller.getUsers();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                delaware=response.body();

                Gson gson = new Gson();
                user = gson.fromJson(delaware,User.class);

                wait=false;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


}
