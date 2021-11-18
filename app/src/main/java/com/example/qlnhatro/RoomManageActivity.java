package com.example.qlnhatro;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlnhatro.Fragment.RoomFragment;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomManageActivity extends AppCompatActivity {

    private Button btnThem;
    private EditText edtThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroom);

        btnThem = findViewById(R.id.btnThemPhong);
        edtThem = findViewById(R.id.edtThemPhong);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room room = new Room(edtThem.getText().toString());
                addRoom(room);
            }
        });
    }

        private void addRoom(Room room) {
            ServiceAPI requestInterface = new Retrofit.Builder()
                    .baseUrl(BASE_Service)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ServiceAPI.class);

            new CompositeDisposable().add(requestInterface.AddRoom(room)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError)
            );
        }

    private void handleResponse(Message message) {
        try {
            Toast.makeText(getApplicationContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
            if (message.getStatus() == 1) {
                startActivity(new Intent(RoomManageActivity.this, RoomFragment.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleError(Throwable error) {
        Toast.makeText(RoomManageActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
}