package com.example.qlnhatro.Service;

import com.example.qlnhatro.Model.Customer;
import com.example.qlnhatro.Model.Room;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ServiceAPI {
    String BASE_Service = " https://6afd-2402-800-638a-d1f7-4e7-e14f-e71e-d4ea.ngrok.io/";

    @GET("api/PHONG")
    Observable<ArrayList<Room>> GetAllRoom();

    @GET("api/KHACHHANG")
    Observable<ArrayList<Customer>> GetAllCustomer();


}
