package com.example.qlnhatro.Fragment;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Adapter.CustomerAdapter;
import com.example.qlnhatro.Model.Customer;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerFragment extends Fragment {

    private ArrayList<Customer> alCustomer;

    private RecyclerView rclCusList;
    private CustomerAdapter customerAdapter;
    private Context context;
    private Button btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_customer, container, false);
        rclCusList = view.findViewById(R.id.rclCusList);
        btnAdd = view.findViewById(R.id.btnAdd);
        alCustomer = new ArrayList<>();
//        for (int i = 1; i < 10; i++) {
//            alCustomer.add(new Customer(i,"a"));
//        }
//        rclCusList.setHasFixedSize(true);
//        customerAdapter = new CustomerAdapter(alCustomer, context);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        rclCusList.setLayoutManager(linearLayoutManager);
//        rclCusList.setAdapter(customerAdapter);

        getCustomer();
        return view;
    }

    public void getCustomer() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllCustomer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<Customer> customers) {
        try {
            rclCusList.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rclCusList.setLayoutManager(linearLayoutManager);
            customerAdapter = new CustomerAdapter(customers,context);
            rclCusList.setAdapter(customerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dismissProgressDialog();
    }
    private void handleError(Throwable error) {
        //dismissProgressDialog();
    }
}
