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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.qlnhatro.MenuDashboardActivity;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomDetail extends AppCompatActivity {

    private EditText edtTenPhong;
    private Button btnSuaPhong,btnThoat;
    private Intent intent;
    private RadioButton rbtnTrong, rbtnCoNguoi, rbtnDangSua;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        edtTenPhong = findViewById(R.id.edtTenPhong);
        btnSuaPhong = findViewById(R.id.btnSuaPhong);
        btnThoat = findViewById(R.id.btnThoat);

        rbtnTrong = findViewById(R.id.rbtnTrong);
        rbtnCoNguoi = findViewById(R.id.rbtnCoNguoi);
        rbtnDangSua = findViewById(R.id.rbtnDangSua);

        intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        getDetailRoom(id);

        btnSuaPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTenPhong.getText().toString();
                int trangthai = -1;
                if( rbtnDangSua.isChecked()){
                    trangthai = 2;
                }
                else if(rbtnCoNguoi.isChecked()){
                    trangthai = 1;
                }
                else{
                    trangthai = 0;
                }
                Room room = new Room(ten, trangthai);
                editRoom(id, room);

            }

            private void editRoom(int id, Room room) {
                ServiceAPI requestInterface = new Retrofit.Builder()
                        .baseUrl(BASE_Service)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ServiceAPI.class);

                new CompositeDisposable().add(requestInterface.PutRoom(id, room)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError)
                );
            }

            private void handleResponse(Message message) {
                dismissProgressDialog();
                try {
                    Toast.makeText(RoomDetail.this, message.getNotification(), Toast.LENGTH_SHORT).show();
                    if (message.getStatus() == 1) {
                        startActivity(new Intent(RoomDetail.this, MenuDashboardActivity.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            private void handleError(Throwable throwable) {
                dismissProgressDialog();
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoomDetail.this, MenuDashboardActivity.class));
            }
        });
    }

    private void getDetailRoom(int id) {
            ServiceAPI requestInterface = new Retrofit.Builder()
                    .baseUrl(BASE_Service)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ServiceAPI.class);

            new CompositeDisposable().add(requestInterface.GetDetailRoom(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError)
            );
    }

    private void handleResponse(Room room) {
        edtTenPhong.setText(room.getTenPhong());
        int id = room.getTrangThai();
        if( id == 0 ){
            rbtnTrong.setChecked(true);
        }else if(id == 1){
            rbtnCoNguoi.setChecked(true);
        }
        else{
            rbtnDangSua.setChecked(true);
        }
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
    }

}