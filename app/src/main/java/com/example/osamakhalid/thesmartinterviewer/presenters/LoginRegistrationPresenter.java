package com.example.osamakhalid.thesmartinterviewer.presenters;

import android.content.Context;
import android.text.TextUtils;

import com.example.osamakhalid.thesmartinterviewer.calls.LoginRegisterContract;
import com.example.osamakhalid.thesmartinterviewer.calls.LoginRegisterUser;
import com.example.osamakhalid.thesmartinterviewer.models.User;
import com.example.osamakhalid.thesmartinterviewer.utils.SaveSharedPreference;

import org.w3c.dom.Text;

/**
 * Created by Osama Khalid on 1/7/2019.
 */

public class LoginRegistrationPresenter implements LoginRegisterContract.presenter,
        LoginRegisterContract.GetLoginRegistered.OnRegistrationFinishedListener,
        LoginRegisterContract.GetLoginRegistered.OnLoginFinishedListener {

    private LoginRegisterContract.MainViewRegistration mainViewRegistration;
    private LoginRegisterContract.MainViewLogin mainViewLogin;
    private LoginRegisterContract.GetLoginRegistered reg;
    private Context context;

    public LoginRegistrationPresenter(LoginRegisterContract.MainViewRegistration mainViewRegistration) {
        this.mainViewRegistration = mainViewRegistration;
        this.reg = new LoginRegisterUser();
    }

    public LoginRegistrationPresenter(LoginRegisterContract.MainViewLogin mainViewLogin, Context context) {
        this.mainViewLogin = mainViewLogin;
        this.reg = new LoginRegisterUser();
        this.context = context;
        if (SaveSharedPreference.getLoggedStatus(this.context)) {
            this.mainViewLogin.successfullyLogin();
        }
    }

    @Override
    public void onLoginDestroy() {
        mainViewLogin = null;
    }

    @Override
    public void onRegDestroy() {
        mainViewRegistration = null;
    }

    @Override
    public void registerUser(String email, String password, String name) {
        if (mainViewRegistration != null) {
            if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                mainViewRegistration.emailEmpty();
                mainViewRegistration.passwordEmpty();
                mainViewRegistration.nameEmpty();
            } else if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email)) {
                mainViewRegistration.nameEmpty();
                mainViewRegistration.emailEmpty();
            } else if (TextUtils.isEmpty(name) && TextUtils.isEmpty(password)) {
                mainViewRegistration.nameEmpty();
                mainViewRegistration.passwordEmpty();
            } else if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                mainViewRegistration.emailEmpty();
                mainViewRegistration.passwordEmpty();
            } else if (TextUtils.isEmpty(name)) {
                mainViewRegistration.nameEmpty();
            } else if (TextUtils.isEmpty(email)) {
                mainViewRegistration.emailEmpty();
            } else if (TextUtils.isEmpty(password)) {
                mainViewRegistration.passwordEmpty();
            } else {
                reg.register(this, new User(email, password));
            }
        }
    }

    @Override
    public void loginUser(String email, String password) {
        if (mainViewLogin != null) {
            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                mainViewLogin.emailEmpty();
                mainViewLogin.passwordEmpty();
            } else if (TextUtils.isEmpty(email)) {
                mainViewLogin.emailEmpty();
            } else if (TextUtils.isEmpty(password)) {
                mainViewLogin.passwordEmpty();
            } else {
                reg.login(this, new User(email, password));
            }
        }
    }

    @Override
    public void onRegistrationFinished(int success) {
        if (mainViewRegistration != null) {
            if (success == 0) {
                mainViewRegistration.userAlreadyExists();
            } else if (success == 1) {
                mainViewRegistration.successfullyRegistered();
            }
        }
    }

    @Override
    public void onRegistrationFailure(Throwable t) {
        if (mainViewRegistration != null)
            mainViewRegistration.onResponseFailure(t);
    }

    @Override
    public void onLoginFinished(int success) {
        if (mainViewLogin != null) {
            if (success == 0) {
                mainViewLogin.notMatched();
            } else if (success == 1) {
                mainViewLogin.successfullyLogin();
                SaveSharedPreference.setLoggedIn(this.context, true);
            }
        }
    }

    @Override
    public void onLoginFailure(Throwable t) {
        if (mainViewLogin != null)
            mainViewLogin.onResponseFailure(t);
    }
}
