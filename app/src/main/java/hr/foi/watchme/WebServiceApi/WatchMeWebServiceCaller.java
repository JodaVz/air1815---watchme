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

        //Constructor initializes new Retrofit instance to communicate to webservice
        OkHttpClient client = new OkHttpClient();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    //Getting all users in JSON from webservice
    public void getAllUsers(final GetDataCallback getDataCallback){
        //Attaching relative path interface
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<String> call = serviceCaller.getUsers();

        //Calling webservice in another thread, handling response
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

    //Getting all movies in JSON from webservice
    public void getMovies(final GetDataCallback getDataCallback){
        //Attaching relative path interface
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<String> call = serviceCaller.getMovies();

        //Calling webservice in another thread, handling response
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

    //Getting all categories (list of lists) in JSON from webservice
    public void getCategories (final GetDataCallback getDataCallback){
        //Attaching relative path interface
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<String> call = serviceCaller.getCategories();

        //Calling webservice in another thread, handling response
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

    //Getting all comments in JSON from webservice
    public void getAllComments (final GetDataCallback getDataCallback){
        //Attaching relative path interface
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<String> call = serviceCaller.getAllComments();

        //Calling webservice in another thread, handling response
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

    //Getting status code from webservice that describes whether user can login or not
    public void getUserLogin(final GetStatusCallback getStatusCallback){
        //Attaching relative path interface
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.getUserLogin();

        //Calling webservice in another thread, handling response
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

    //Getting status code from webservice that describes whether user registered successfully or not
    public void getRegistrationSuccess(final GetStatusCallback getStatusCallback){
        //Attaching relative path interface
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.getUserRegistration();

        //Calling webservice in another thread, handling response
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

    //Writing data to database and getting status code from webservice that describes whether user rated successfully
    public void getUserRating(final GetStatusCallback getStatusCallback){
        //Attaching relative path interface
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.getUserRating();

        //Calling webservice in another thread, handling response
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

    //Getting status code from webservice that describes whether user already has and how it has rated the movie
    public void checkUserRating(final GetStatusCallback getStatusCallback){
        //Attaching relative path interface
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.checkUserRating();

        //Calling webservice in another thread, handling response
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

    //Writing data to database and getting status code from webservice that describes whether user commented successfully
    public void getUserComment(final GetStatusCallback getStatusCallback){
        //Attaching relative path interface
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        Call<Void> call = serviceCaller.getUserComment();

        //Calling webservice in another thread, handling response
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

    //Sends data to webservice that it then uses for authentication
    public void postUserLogin(){
        //Attaching relative path interface and preparing JSON data for sending
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"email\":\""+email+"\",\"Password\":\""+password+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserLogin(jsonString);

        //Calling webservice in another thread, handling response
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

    //Sends data to webservice that it then uses for registering the user
    public void postUserRegistration(){
        //Attaching relative path interface and preparing JSON data for sending
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"Name\":\""+name+"\",\"Surname\":\""+surname+"\",\"Email\":\""+email+"\",\"Password\":\""+password+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserRegistration(jsonString);

        //Calling webservice in another thread, handling response
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

    //Sends data to webservice that it then uses for saving rating of the user
    public void postUserRating(){
        //Attaching relative path interface and preparing JSON data for sending
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"LeftBy\":\""+userId+"\",\"CommentedOn\":\""+movieId+"\",\"Rating\":\""+rating+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserRating(jsonString);

        //Calling webservice in another thread, handling response
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

    //Sends data to webservice that it then uses for saving comment of the user
    public void postUserComment(){
        //Attaching relative path interface and preparing JSON data for sending
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"LeftBy\":\""+userId+"\",\"CommentedOn\":\""+movieId+"\",\"Comments\":\""+comment+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserComment(jsonString);

        //Calling webservice in another thread, handling response
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

    //Sends data to webservice that it then uses for identifying the current movie
    public void postUserMovie(){
        //Attaching relative path interface and preparing JSON data for sending
        WatchMeWebService serviceCaller = retrofit.create(WatchMeWebService.class);
        String jsonString = "{\"LeftBy\":\""+userId+"\",\"CommentedOn\":\""+movieId+"\"}";

        Call<ResponseBody> call = serviceCaller.postUserRating(jsonString);

        //Calling webservice in another thread, handling response
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
