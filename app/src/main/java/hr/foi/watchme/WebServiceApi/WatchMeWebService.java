package hr.foi.watchme.WebServiceApi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WatchMeWebService {

    @GET("api/korisnici/users/1")
    Call<String> getUsers();

    @GET("api/korisnici/users/provjeri/")
    Call<Void> getUserLogin();

    @Headers("Content-Type: application/json")
    @POST("api/korisnici/users/provjeri/")
    Call<ResponseBody> postUserLogin(@Body String body);
}
