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
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface ServiceAPI {
    String BASE_Service = "https://d60e-2402-800-638a-c712-6991-3efa-76d4-8f29.ngrok.io/";

    //api phong
    @GET("api/PHONG")
    Observable<ArrayList<Room>> GetAllRoom();

    @GET("api/PHONG")
    Observable<Room> GetDetailRoom(@Query("id") int id);

    @POST("api/PHONG")
    Observable<Message> AddRoom(@Body Room room);

    @DELETE("api/PHONG")
    Observable<Message> DeleteRoom(@Query("id") int id);

    @PUT("api/PHONG")
    Observable<Message> PutRoom(@Query("id") int id, @Body Room room);
    //-----------------------------------------------------------------------//

    //api khachhang
    @GET("api/KHACHHANG")
    Observable<ArrayList<Customer>> GetAllCustomer();

    @GET("api/KHACHHANG")
    Observable<Customer> GetDetailCustomer(@Query("id") int id);

    @POST("api/KHACHHANG")
    Observable<Message> AddCustomer(@Body Customer customer);

    @DELETE("api/KHACHHANG")
    Observable<Message> DeleteCustomer(@Query("id") int id);

    @PUT("api/KHACHHANG")
    Observable<Message> PutCustomer(@Query("id") int id, @Body Customer customer);

    //-----------------------------------------------------------------------//

    //api dichvu
    @GET("api/DICHVU")
    Observable<ArrayList<Service>> GetAllService();

    @GET("api/DICHVU")
    Observable<Service> GetDetailService(@Query("id") int id);

    @POST("api/DICHVU")
    Observable<Message> AddService(@Body Service service);

    @DELETE("api/DICHVU")
    Observable<Message> DeleteService(@Query("id") int id);

    @PUT("api/DICHVU")
    Observable<Message> PutService(@Query("id") int id, @Body Service service);


}
