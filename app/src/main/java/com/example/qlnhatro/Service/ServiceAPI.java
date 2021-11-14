package com.example.qlnhatro.Service;

import com.example.qlnhatro.Model.Room;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ServiceAPI {
    String BASE_Service = " https://c85a-2402-800-638a-b251-751a-c54a-359-2cb2.ngrok.io/";

    @GET("api/PHONG")
    Observable<ArrayList<Room>> GetAllRoom();


}
