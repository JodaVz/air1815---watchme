package hr.foi.watchme.WebServiceApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WatchMeWebService {

    @GET("api/korisnici/users/1")
    Call<String> getUsers();

    @GET("api/korisnici/users/provjeri/")
    Call<Void> getUserLogin();

    @GET("api/sadrzaj/sadrzaj/")
    Call<String> getMovies();

    @Headers("Content-Type: application/json")
    @POST("api/korisnici/users/provjeri/")
    Call<ResponseBody> postUserLogin(@Body String body);
}
