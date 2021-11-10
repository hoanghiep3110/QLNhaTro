package com.example.qlnhatro.Service;

import android.database.Observable;

import com.example.qlnhatro.Model.Room;


import java.util.ArrayList;

import retrofit2.http.GET;


public interface ServiceAPI {
    String BASE_Service = "https://192.168.1.19:3000/";

    @GET("api/PHONG")
    Observable<ArrayList<Room>> GetAllRoom();


}
