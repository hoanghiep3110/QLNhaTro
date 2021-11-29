package com.example.qlnhatro.Detail;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.qlnhatro.Model.Contact;
import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView edtHoTenKH,edtTenPhongHD, edtTienDatCoc, edtNgayBatDau, edtNgayKetThuc,edtFileHopDong ;
    private Button btnThoatCTHD;
    private Intent intent;
    private String linkDownLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        edtHoTenKH = findViewById(R.id.edtHoTenKH);
        edtTenPhongHD = findViewById(R.id.edtTenPhongHD);
        edtTienDatCoc = findViewById(R.id.edtTienDatCoc);
        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        edtFileHopDong = findViewById(R.id.edtFileHopDong);
        btnThoatCTHD = findViewById(R.id.btnThoatCTHD);

        intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        getDetailContact(id);

        edtFileHopDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkDownLoad));
                startActivity(browserIntent);
            }
        });

        btnThoatCTHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDetailContact(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetDetailContact(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(Contact contact) {
        edtHoTenKH.setText(contact.getHoTen());
        edtTenPhongHD.setText(contact.getTenPhong());
        edtTienDatCoc.setText(String.valueOf(contact.getTienDatCoc()));
        edtNgayBatDau.setText(contact.getNgayBatDau());
        edtNgayKetThuc.setText(contact.getNgayKetThuc());
        edtFileHopDong.setText(contact.getFileHopDong().split("/")[3]);
        linkDownLoad = contact.getLinkDownLoad();
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
    }
}