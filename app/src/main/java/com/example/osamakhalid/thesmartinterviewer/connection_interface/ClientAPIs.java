package com.example.osamakhalid.thesmartinterviewer.connection_interface;

import com.example.osamakhalid.thesmartinterviewer.utils.ConnectionUrls;
import com.example.osamakhalid.thesmartinterviewer.models.LoginRegistrationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Osama Khalid on 1/7/2019.
 */

public interface ClientAPIs {
    @GET(ConnectionUrls.REGISTER_URL)
    Call<LoginRegistrationResponse> registerUser(@Query("email") String email, @Query("password") String password,
                                                 @Query("name") String name);

    @GET(ConnectionUrls.LOGIN_URL)
    Call<LoginRegistrationResponse> loginUser(@Query("email") String email, @Query("password") String password);
}
