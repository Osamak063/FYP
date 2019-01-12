package com.example.osamakhalid.thesmartinterviewer.connection_interface;

import com.example.osamakhalid.thesmartinterviewer.models.User;
import com.example.osamakhalid.thesmartinterviewer.utils.ConnectionUrls;
import com.example.osamakhalid.thesmartinterviewer.models.LoginRegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Osama Khalid on 1/7/2019.
 */

public interface ClientAPIs {
    @GET(ConnectionUrls.REGISTER_URL)
    Call<LoginRegistrationResponse> registerUser(@Body User user);

    @POST(ConnectionUrls.LOGIN_URL)
    Call<LoginRegistrationResponse> loginUser(@Body User user);

    @POST(ConnectionUrls.USER_DETAILS)
    Call<LoginRegistrationResponse> CompleteDetails(@Body User user);
}
