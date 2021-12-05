package com.example.qlnhatro.Detail;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qlnhatro.Model.Customer;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerDetailActivity extends AppCompatActivity {

    private TextView edt1, edt2, edt3, edt4, edt5;
    private ImageView imgCMND;
    private Button btnSuaCTKH,btnThoatCTKH;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        edt1 = findViewById(R.id.edtHoTen);
        edt2 = findViewById(R.id.edtSDTKH);
        edt3 = findViewById(R.id.edtGioiTinh);
        edt4 = findViewById(R.id.edtQueQuan);
        edt5 = findViewById(R.id.edtHKTT);
        imgCMND = findViewById(R.id.imgCMND);
        btnThoatCTKH = findViewById(R.id.btnThoatCTKH);

        intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        getDetailCustomer(id);

//        btnSuaCTKH.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String ten = edt1.getText().toString();
//                String sdt = edt2.getText().toString();
//                String gioitinh = edt3.getText().toString();
//                String quequan = edt4.getText().toString();
//                String hktt = edt5.getText().toString();
//                String cmnd = imgCMND.getText().toString();
//
//                Customer customer = new Customer(ten, sdt, gioitinh, quequan, hktt, cmnd);
//                editCustommer(id, customer);
//            }
//            private void editCustommer(int id, Customer customer) {
//                ServiceAPI requestInterface = new Retrofit.Builder()
//                        .baseUrl(BASE_Service)
//                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build().create(ServiceAPI.class);
//
//                new CompositeDisposable().add(requestInterface.PutCustomer(id, customer)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeOn(Schedulers.io())
//                        .subscribe(this::handleResponse, this::handleError)
//                );
//            }
//            private void handleResponse(Message message) {
//                dismissProgressDialog();
//                try {
//                    Toast.makeText(CustomerDetail.this, message.getNotification(), Toast.LENGTH_SHORT).show();
//                    if (message.getStatus() == 1) {
//                        startActivity(new Intent(CustomerDetail.this, MenuDashboardActivity.class));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            private void handleError(Throwable throwable) {
//                dismissProgressDialog();
//            }
//        });

        btnThoatCTKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDetailCustomer(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetDetailCustomer(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }
    private void handleResponse(Customer customer) {
        edt1.setText(customer.getHoTen());
        edt2.setText(customer.getSdt());
        edt3.setText(customer.getGioiTinh());
        edt4.setText(customer.getQueQuan());
        edt5.setText(customer.getHKTT());
        Glide.with(CustomerDetailActivity.this)
                .load("https://nhatro3h.somee.com" + customer.getSoCMND())
                .into(imgCMND);
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
    }
}