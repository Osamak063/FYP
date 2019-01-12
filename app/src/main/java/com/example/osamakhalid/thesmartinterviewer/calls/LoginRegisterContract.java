package com.example.osamakhalid.thesmartinterviewer.calls;

import com.example.osamakhalid.thesmartinterviewer.models.User;

/**
 * Created by Osama Khalid on 1/7/2019.
 */

public interface LoginRegisterContract {
    interface presenter {

        void onLoginDestroy();

        void onRegDestroy();

        void registerUser(String email, String password, String name);

        void loginUser(String email, String password);
    }

    interface MainViewRegistration {

        void userAlreadyExists();

        void successfullyRegistered();

        void onResponseFailure(Throwable throwable);

        void nameEmpty();

        void emailEmpty();

        void passwordEmpty();
    }

    interface MainViewLogin {

        void notMatched();

        void successfullyLogin();

        void onResponseFailure(Throwable throwable);

        void emailEmpty();

        void passwordEmpty();
    }

    interface GetLoginRegistered {

        interface OnRegistrationFinishedListener {
            void onRegistrationFinished(int success);

            void onRegistrationFailure(Throwable t);
        }

        interface OnLoginFinishedListener {
            void onLoginFinished(int success);

            void onLoginFailure(Throwable t);
        }

        void register(OnRegistrationFinishedListener onRegistrationFinishedListener, User user);

        void login(OnLoginFinishedListener onLoginFinishedListener, User user);
    }
}
