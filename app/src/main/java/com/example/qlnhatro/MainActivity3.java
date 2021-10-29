package com.example.qlnhatro;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity3 extends AppCompatActivity {

    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);

        navigationView = findViewById(R.id.navigation);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.nav_draw,R.string.nav_draw1);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }



}