package hr.foi.watchme.WebServiceApi;


import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class WatchMeWebServiceCaller {

    public String email;
    public String password;

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

    public void getAllUsers(final GetDataCallback getDataCallback){

        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<String> call = serviceCaller.getUsers();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    getDataCallback.onGetUserData(response.body());
                }else {
                    System.out.print("Ne vela");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getUserLogin(final GetStatusCallback getStatusCallback){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.getUserLogin();

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getStatusCallback.onGetCode(response.code());
                Log.d(TAG,"Kod: "+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void postUserLogin(){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"email\":\""+email+"\",\"Password\":\""+password+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserLogin(jsonString);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"Can't make post");
            }
        });

    }

}
