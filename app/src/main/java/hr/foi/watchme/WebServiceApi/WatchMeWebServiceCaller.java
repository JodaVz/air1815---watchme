package hr.foi.watchme.WebServiceApi;


import android.util.Log;


import hr.foi.watchme.WebServiceApi.WebServiceInterfaces.GetDataCallback;
import hr.foi.watchme.WebServiceApi.WebServiceInterfaces.GetStatusCallback;
import hr.foi.watchme.WebServiceApi.WebServiceInterfaces.WatchMeWebService;
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
    public String name;
    public String surname;
    public String comment;
    public Integer userId;
    public Integer movieId;
    public Integer rating;

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
                    getDataCallback.onGetData(response.body());
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

    public void getMovies(final GetDataCallback getDataCallback){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<String> call = serviceCaller.getMovies();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    getDataCallback.onGetData(response.body());
                }else{
                    Log.d(TAG,"Nisu došli filmeki: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG,"Nije moguće povezivanje s bazom ");
            }
        });
    }

    public void getCategories (final GetDataCallback getDataCallback){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<String> call = serviceCaller.getCategories();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    getDataCallback.onGetData(response.body());
                }else{
                    Log.d(TAG,"Nisu došle kategorije: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG,"Nije moguće povezivanje s bazom ");
            }
        });
    }

    public void getAllComments (final GetDataCallback getDataCallback){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<String> call = serviceCaller.getAllComments();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    getDataCallback.onGetData(response.body());
                }else{
                    Log.d(TAG,"Nisu došli komentari: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG,"Nije moguće povezivanje s bazom ");
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

    public void getRegistrationSuccess(final GetStatusCallback getStatusCallback){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.getUserRegistration();

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

    public void getUserRating(final GetStatusCallback getStatusCallback){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.getUserRating();

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

    public void checkUserRating(final GetStatusCallback getStatusCallback){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.checkUserRating();

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

    public void getUserComment(final GetStatusCallback getStatusCallback){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.getUserComment();

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

    public void postUserRegistration(){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"Name\":\""+name+"\",\"Surname\":\""+surname+"\",\"Email\":\""+email+"\",\"Password\":\""+password+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserRegistration(jsonString);

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

    public void postUserRating(){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"LeftBy\":\""+userId+"\",\"CommentedOn\":\""+movieId+"\",\"Rating\":\""+rating+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserRating(jsonString);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"Can't make post feedback");
            }
        });
    }

    public void postUserComment(){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"LeftBy\":\""+userId+"\",\"CommentedOn\":\""+movieId+"\",\"Comments\":\""+comment+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserComment(jsonString);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"Can't make post comment");
            }
        });
    }

    public void postUserMovie(){
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"LeftBy\":\""+userId+"\",\"CommentedOn\":\""+movieId+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserRating(jsonString);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"Can't make post feedback");
            }
        });
    }

}
