package com.example.qlnhatro.Service;

import com.example.qlnhatro.Model.Customer;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Room;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ServiceAPI {
    String BASE_Service = " https://b2e6-2402-800-638a-3977-b1ef-565f-b230-4ab5.ngrok.io/";

    @GET("api/PHONG")
    Observable<ArrayList<Room>> GetAllRoom();

    @POST("api/PHONG")
    Observable<Message> AddRoom(@Body Room room);

    @GET("api/KHACHHANG")
    Observable<ArrayList<Customer>> GetAllCustomer();


}
