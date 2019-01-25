package hr.foi.watchme.WebServiceApi.WebServiceInterfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WatchMeWebService {

    @GET("api/korisnici/users/")
    Call<String> getUsers();

    @GET("api/korisnici/users/provjeri/")
    Call<Void> getUserLogin();

    @GET("api/korisnici/users/unesi_novog_korisnika/")
    Call<Void> getUserRegistration();

    @GET("api/sadrzaj/sadrzaj/")
    Call<String> getMovies();

    @GET("api/sadrzaj/sadrzaj/{id}")
    Call<String> getMoviesByID(@Path("id") int id);

    @GET("api/sadrzaj/sadrzaj/dohvati_kategorije_filmova")
    Call<String> getCategories();

    @Headers("Content-Type: application/json")
    @POST("api/korisnici/users/provjeri/")
    Call<ResponseBody> postUserLogin(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("api/korisnici/users/unesi_novog_korisnika/")
    Call<ResponseBody> postUserRegistration(@Body String body);
}
