package com.example.osamakhalid.thesmartinterviewer.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osamakhalid.thesmartinterviewer.R;
import com.example.osamakhalid.thesmartinterviewer.calls.LoginRegisterContract;
import com.example.osamakhalid.thesmartinterviewer.presenters.LoginRegistrationPresenter;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, LoginRegisterContract.MainViewRegistration {
    EditText username, password, name;
    Button regBtn;
    LoginRegisterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        regBtn = findViewById(R.id.register);
        regBtn.setOnClickListener(this);
        presenter = new LoginRegistrationPresenter(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == regBtn.getId()) {
            presenter.registerUser(username.getText().toString(), password.getText().toString(),
                    name.getText().toString());
        }
    }

    @Override
    public void userAlreadyExists() {
        username.setError("User already exists");
    }

    @Override
    public void successfullyRegistered() {
        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
        username.setText("");
        password.setText("");
        name.setText("");
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nameEmpty() {
        name.setError("Please enter your name");
    }

    @Override
    public void emailEmpty() {
        username.setError("Please enter username");
    }

    @Override
    public void passwordEmpty() {
        password.setError("Please enter password");
    }
}
