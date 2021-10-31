package com.example.qlnhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.qlnhatro.Fragment.ContactFragment;
import com.example.qlnhatro.Fragment.CustomerFragment;
import com.example.qlnhatro.Fragment.InvoiceFragment;
import com.example.qlnhatro.Fragment.RoomFragment;
import com.example.qlnhatro.Fragment.ServiceFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int FRAGMENT_ROOM=0;
    private static final int FRAGMENT_CUSTOMER=1;
    private static final int FRAGMENT_SERVICE=2;
    private static final int FRAGMENT_INVOICE=3;
    private static final int FRAGMENT_CONTACT=4;

    private int mCurrentFragment = FRAGMENT_ROOM;

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

        NavigationView navigationView =findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new RoomFragment());
        navigationView.getMenu().findItem(R.id.motel).setChecked(true);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id ==R.id.motel){
            if(mCurrentFragment!=FRAGMENT_ROOM){
                replaceFragment(new RoomFragment());
                mCurrentFragment=FRAGMENT_ROOM;
            }
        }else if(id==R.id.customer){
            if(mCurrentFragment!=FRAGMENT_CUSTOMER){
                replaceFragment(new CustomerFragment());
                mCurrentFragment=FRAGMENT_CUSTOMER;
            }
        }else if(id==R.id.service){
            if(mCurrentFragment!=FRAGMENT_SERVICE){
                replaceFragment(new ServiceFragment());
                mCurrentFragment=FRAGMENT_SERVICE;
            }
        }else if(id==R.id.invoice){
            if(mCurrentFragment!=FRAGMENT_INVOICE){
                replaceFragment(new InvoiceFragment());
                mCurrentFragment=FRAGMENT_INVOICE;
            }
        }else if(id==R.id.contact){
            if(mCurrentFragment!=FRAGMENT_CONTACT){
                replaceFragment(new ContactFragment());
                mCurrentFragment=FRAGMENT_CONTACT;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
}