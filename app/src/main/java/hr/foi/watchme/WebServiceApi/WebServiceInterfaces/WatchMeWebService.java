package hr.foi.watchme.WebServiceApi.WebServiceInterfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WatchMeWebService {

    @GET("api/korisnici/users/")
    Call<String> getUsers();

    @GET("api/korisnici/users/provjeri/")
    Call<Void> getUserLogin();

    @GET("api/korisnici/users/unesi_novog_korisnika/")
    Call<Void> getUserRegistration();

    @GET("api/feedback/feedback/spremi_ocjenu")
    Call<Void> getUserRating();

    @GET("api/feedback/feedback/spremi_komentar")
    Call<Void> getUserComment();

    @GET("api/feedback/feedback/provjeri_ocjenu")
    Call<Void> checkUserRating();

    @GET("api/feedback/feedback/dohvati_komentare")
    Call<String> getAllComments();

    @GET("api/sadrzaj/sadrzaj/")
    Call<String> getMovies();

    @GET("api/sadrzaj/sadrzaj/dohvati_kategorije_filmova")
    Call<String> getCategories();

    @Headers("Content-Type: application/json")
    @POST("api/korisnici/users/provjeri/")
    Call<ResponseBody> postUserLogin(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("api/korisnici/users/unesi_novog_korisnika/")
    Call<ResponseBody> postUserRegistration(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("api/feedback/feedback/spremi_ocjenu")
    Call<ResponseBody> postUserRating(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("api/feedback/feedback/spremi_komentar")
    Call<ResponseBody> postUserComment(@Body String body);
}
