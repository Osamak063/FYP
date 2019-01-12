package com.example.osamakhalid.thesmartinterviewer.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.osamakhalid.thesmartinterviewer.R;
import com.example.osamakhalid.thesmartinterviewer.calls.LoginRegisterContract;
import com.example.osamakhalid.thesmartinterviewer.presenters.LoginRegistrationPresenter;
import com.example.osamakhalid.thesmartinterviewer.ui.fragments.CompProfileFragment;
import com.example.osamakhalid.thesmartinterviewer.ui.fragments.LoginFragment;
import com.example.osamakhalid.thesmartinterviewer.ui.fragments.RegistrationFragment;
import com.example.osamakhalid.thesmartinterviewer.utils.SaveSharedPreference;

public class MainActivity extends AppCompatActivity implements RegistrationFragment.OnRegFragmentInteractionListener,
        LoginFragment.OnLoginFragmentInteractionListener, CompProfileFragment.OnProfFragmentInteractionListener {
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .commit();
    }


    @Override
    public void onRegFragmentInteraction() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new CompProfileFragment())
                .commit();
    }

    @Override
    public void onLoginFragmentInteraction(int i) {
        if (LoginFragment.OnLoginFragmentInteractionListener.LOGIN == i) {
            Toast.makeText(getApplicationContext(), "Successfully logged in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else if (LoginFragment.OnLoginFragmentInteractionListener.REG == i) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new RegistrationFragment())
                    .commit();
        }
    }

    @Override
    public void onProfFragmentInteraction() {
        startActivity(new Intent(MainActivity.this, CategoriesActivity.class));
    }
}
