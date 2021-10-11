package com.example.qlnhatro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import Adapter.ApartmentAdapter;
import House.Apartment;

public class MainActivity4 extends AppCompatActivity {

    private Button btnChange;
    private ArrayList<Apartment> alApartment;

    private RecyclerView rclApartmentList;
    private ApartmentAdapter apartmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        rclApartmentList = findViewById(R.id.rclApartmentList);

        alApartment = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            alApartment.add(new Apartment("Ten Phong " +i,"Ten Khach"+i));
        }

        rclApartmentList.setHasFixedSize(true);

        apartmentAdapter = new ApartmentAdapter(alApartment,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        rclApartmentList.setLayoutManager(linearLayoutManager);
        rclApartmentList.setAdapter(apartmentAdapter);
    }
}