package com.example.osamakhalid.thesmartinterviewer.calls;

import com.example.osamakhalid.thesmartinterviewer.base_connection.RetrofitInstance;
import com.example.osamakhalid.thesmartinterviewer.connection_interface.ClientAPIs;
import com.example.osamakhalid.thesmartinterviewer.models.LoginRegistrationResponse;
import com.example.osamakhalid.thesmartinterviewer.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Osama Khalid on 1/7/2019.
 */

public class LoginRegisterUser implements LoginRegisterContract.GetLoginRegistered {

    @Override
    public void register(final OnRegistrationFinishedListener onRegistrationFinishedListener, User user) {
        ClientAPIs clientAPIs = RetrofitInstance.getRetrofitInstance().create(ClientAPIs.class);
        Call<LoginRegistrationResponse> call = clientAPIs.registerUser(user);

        call.enqueue(new Callback<LoginRegistrationResponse>() {
            @Override
            public void onResponse(Call<LoginRegistrationResponse> call, Response<LoginRegistrationResponse> response) {
                if (response.isSuccessful()) {
                    onRegistrationFinishedListener.onRegistrationFinished(response.body().getSuccess());
                }
            }

            @Override
            public void onFailure(Call<LoginRegistrationResponse> call, Throwable t) {
                onRegistrationFinishedListener.onRegistrationFailure(t);
            }
        });
    }

    @Override
    public void login(final OnLoginFinishedListener onLoginFinishedListener, User user) {
        ClientAPIs clientAPIs = RetrofitInstance.getRetrofitInstance().create(ClientAPIs.class);
        Call<LoginRegistrationResponse> call = clientAPIs.loginUser(user);

        call.enqueue(new Callback<LoginRegistrationResponse>() {
            @Override
            public void onResponse(Call<LoginRegistrationResponse> call, Response<LoginRegistrationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onLoginFinishedListener.onLoginFinished(response.body().getSuccess());
                }
            }

            @Override
            public void onFailure(Call<LoginRegistrationResponse> call, Throwable t) {
                onLoginFinishedListener.onLoginFailure(t);
            }
        });
    }
}
