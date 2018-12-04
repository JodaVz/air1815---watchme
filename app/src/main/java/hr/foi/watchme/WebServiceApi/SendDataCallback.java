package hr.foi.watchme.WebServiceApi;

import okhttp3.RequestBody;

public interface SendDataCallback {
    void sendUserData(String email, String password);
}
