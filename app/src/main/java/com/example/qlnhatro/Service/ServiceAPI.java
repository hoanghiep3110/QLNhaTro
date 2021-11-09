package com.example.qlnhatro.Service;

import android.database.Observable;

import com.example.qlnhatro.House.Room;

import java.util.ArrayList;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;



public interface ServiceAPI {
    String BASE_Service = "https://192.168.1.19:3000/";

    @GET("api/PHONG")
    Observable<ArrayList<Room>> listRoom;
}
