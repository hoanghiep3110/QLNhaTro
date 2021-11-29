package com.example.qlnhatro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qlnhatro.Fragment.ContactFragment;
import com.example.qlnhatro.Fragment.CustomerFragment;
import com.example.qlnhatro.Fragment.HomeFragment;
import com.example.qlnhatro.Fragment.InvoiceFragment;
import com.example.qlnhatro.Fragment.RoomFragment;
import com.example.qlnhatro.Fragment.ServiceFragment;
import com.example.qlnhatro.Service.UserSession;
import com.google.android.material.navigation.NavigationView;

public class MenuDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

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

    private TextView txt1, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);

        navigationView = findViewById(R.id.navigation);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_draw, R.string.nav_draw1);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        txt1 = (TextView)header.findViewById(R.id.txtHoten);
        txt2 = (TextView)header.findViewById(R.id.txtSdt);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        String hoten = sp.getString("fullname", "");
        String sdt  = sp.getString("phone","");

        txt1.setText(hoten);
        txt2.setText(sdt);

        replaceFragment(new RoomFragment());
        navigationView.getMenu().findItem(R.id.motel).setChecked(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id ==R.id.motel){
            navigationView.getMenu().findItem(R.id.motel).setChecked(true);
            if(mCurrentFragment!=FRAGMENT_ROOM){
                replaceFragment(new RoomFragment());
                mCurrentFragment=FRAGMENT_ROOM;
                navigationView.getMenu().findItem(R.id.customer).setChecked(false);
                navigationView.getMenu().findItem(R.id.service).setChecked(false);
                navigationView.getMenu().findItem(R.id.invoicedetail).setChecked(false);
                navigationView.getMenu().findItem(R.id.contact).setChecked(false);
            }
        }else if(id==R.id.customer){
            navigationView.getMenu().findItem(R.id.customer).setChecked(true);
            if(mCurrentFragment!=FRAGMENT_CUSTOMER){
                replaceFragment(new CustomerFragment());
                mCurrentFragment=FRAGMENT_CUSTOMER;
                navigationView.getMenu().findItem(R.id.motel).setChecked(false);
                navigationView.getMenu().findItem(R.id.service).setChecked(false);
                navigationView.getMenu().findItem(R.id.invoicedetail).setChecked(false);
                navigationView.getMenu().findItem(R.id.contact).setChecked(false);

            }
        }else if(id==R.id.service){
            navigationView.getMenu().findItem(R.id.service).setChecked(true);
            if(mCurrentFragment!=FRAGMENT_SERVICE){
                replaceFragment(new ServiceFragment());
                mCurrentFragment=FRAGMENT_SERVICE;
                navigationView.getMenu().findItem(R.id.customer).setChecked(false);
                navigationView.getMenu().findItem(R.id.motel).setChecked(false);
                navigationView.getMenu().findItem(R.id.invoicedetail).setChecked(false);
                navigationView.getMenu().findItem(R.id.contact).setChecked(false);

            }
        }else if(id==R.id.invoicedetail){
            navigationView.getMenu().findItem(R.id.invoicedetail).setChecked(true);
            if(mCurrentFragment!=FRAGMENT_INVOICE){
                replaceFragment(new InvoiceFragment());
                mCurrentFragment=FRAGMENT_INVOICE;
                navigationView.getMenu().findItem(R.id.customer).setChecked(false);
                navigationView.getMenu().findItem(R.id.service).setChecked(false);
                navigationView.getMenu().findItem(R.id.motel).setChecked(false);
                navigationView.getMenu().findItem(R.id.contact).setChecked(false);

            }
        }else if(id==R.id.contact){
            navigationView.getMenu().findItem(R.id.contact).setChecked(true);
            if(mCurrentFragment!=FRAGMENT_CONTACT){
                replaceFragment(new ContactFragment());
                mCurrentFragment=FRAGMENT_CONTACT;
                navigationView.getMenu().findItem(R.id.customer).setChecked(false);
                navigationView.getMenu().findItem(R.id.service).setChecked(false);
                navigationView.getMenu().findItem(R.id.invoicedetail).setChecked(false);
                navigationView.getMenu().findItem(R.id.motel).setChecked(false);

            }
        }else if(id==R.id.logout){
            UserSession mySession = new UserSession(MenuDashboardActivity.this);
            mySession.logoutSession();
           Intent intent = new Intent(MenuDashboardActivity.this, LoginActivity.class);
           startActivity(intent);
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