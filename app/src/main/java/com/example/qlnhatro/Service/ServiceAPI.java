package com.example.qlnhatro.Service;

import com.example.qlnhatro.Model.Room;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ServiceAPI {
    String BASE_Service = "https://192.168.1.16:26620/";

    @GET("api/PHONG")
    Observable<ArrayList<Room>> GetAllRoom();


}
