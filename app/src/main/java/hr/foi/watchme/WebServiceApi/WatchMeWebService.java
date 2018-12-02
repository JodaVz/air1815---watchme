package hr.foi.watchme.WebServiceApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WatchMeWebService {

    @GET("api/korisnici/users/1")
    Call<String> getUsers();
}
