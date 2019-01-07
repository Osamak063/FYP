package com.example.osamakhalid.thesmartinterviewer.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osamakhalid.thesmartinterviewer.R;
import com.example.osamakhalid.thesmartinterviewer.calls.LoginRegisterContract;
import com.example.osamakhalid.thesmartinterviewer.presenters.LoginRegistrationPresenter;
import com.example.osamakhalid.thesmartinterviewer.utils.SaveSharedPreference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginRegisterContract.MainViewLogin {
    EditText username, password;
    Button login, reg;
    LoginRegisterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login.setOnClickListener(this);
        reg.setOnClickListener(this);
        presenter = new LoginRegistrationPresenter(this, getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == login.getId()) {
            presenter.loginUser(username.getText().toString(), password.getText().toString());
        } else if (view.getId() == reg.getId()) {
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
        }
    }

    @Override
    public void notMatched() {
        Toast.makeText(getApplicationContext(), "Email or Password does not match", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successfullyLogin() {
        Toast.makeText(getApplicationContext(), "Successfully logged in", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
