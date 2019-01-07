package com.example.osamakhalid.thesmartinterviewer.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;


import com.example.osamakhalid.thesmartinterviewer.R;
import com.example.osamakhalid.thesmartinterviewer.utils.SaveSharedPreference;

public class CategoriesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        gridView = findViewById(R.id.grid_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.categories_item, new String[]{"IT", "Pharmaceutical", "Hospital", "Electromechanical", "Others"});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(CategoriesActivity.this, CompaniesActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout_menu) {
            SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
            Intent i = new Intent(CategoriesActivity.this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
