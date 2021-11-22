package com.example.qlnhatro.Detail;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;
import static com.example.qlnhatro.other.ShowNotifyUser.showProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlnhatro.MenuDashboardActivity;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.Model.Service;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceDetail extends AppCompatActivity {

    private EditText edtTenDichVu, edtDonGia;
    private Button btnSuaDV,btnThoatDV;
    private Intent intent;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        edtTenDichVu = findViewById(R.id.edtTenDV);
        edtDonGia = findViewById(R.id.edtDonGiaDV);
        btnSuaDV = findViewById(R.id.btnSuaDV);
        btnThoatDV = findViewById(R.id.btnThoatCTDV);

        intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        getDetailService(id);

        btnSuaDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTenDichVu.getText().toString();
                int dongia = Integer.parseInt(edtDonGia.getText().toString());
                Service service = new Service(ten, dongia);
                editService(id, service);

            }
            private void editService(int id, Service service) {
                ServiceAPI requestInterface = new Retrofit.Builder()
                        .baseUrl(BASE_Service)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ServiceAPI.class);

                new CompositeDisposable().add(requestInterface.PutService(id, service)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError)
                );
            }
            private void handleError(Throwable throwable) {dismissProgressDialog();}
            private void handleResponse(Message message) {
                dismissProgressDialog();
                try {
                    Toast.makeText(ServiceDetail.this, message.getNotification(), Toast.LENGTH_SHORT).show();
                    if (message.getStatus() == 1) {
                        startActivity(new Intent(ServiceDetail.this, MenuDashboardActivity.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnThoatDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceDetail.this, MenuDashboardActivity.class));
            }
        });
    }

    private void getDetailService(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetDetailService(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }
    private void handleResponse(Service service) {
        edtTenDichVu.setText(service.getTenDichVu());
        edtDonGia.setText(String.valueOf(service.getDonGia()));
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
    }
}