package hr.foi.watchme.WebServiceApi;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WatchMeWebServiceCaller {
    public boolean delaj = false;
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
        Call<User> call = serviceCaller.getUsers();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                delaj=true;
                //User user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


}
