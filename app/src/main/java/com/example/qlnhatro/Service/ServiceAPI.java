package com.example.qlnhatro.Service;

import com.example.qlnhatro.Model.Customer;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.Model.Service;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ServiceAPI {
    String BASE_Service = " https://3954-2402-800-638a-3977-39c2-e7af-f94b-6cbe.ngrok.io/";

    //api phong
    @GET("api/PHONG")
    Observable<ArrayList<Room>> GetAllRoom();

    @GET("api/PHONG")
    Observable<Room> GetDetailRoom(@Query("id") int id);

    @POST("api/PHONG")
    Observable<Message> AddRoom(@Body Room room);

    @DELETE("api/PHONG")
    Observable<Message> DeleteRoom(@Query("id") int id);

    //api khachhang
    @GET("api/KHACHHANG")
    Observable<ArrayList<Customer>> GetAllCustomer();

    //api dichvu
    @GET("api/DICHVU")
    Observable<ArrayList<Service>> GetAllService();


}
