package com.example.qlnhatro.Detail;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;
import static com.example.qlnhatro.other.ShowNotifyUser.showProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Adapter.InvoiceDetailAdapter;
import com.example.qlnhatro.AddDetailInvoiceActivity;
import com.example.qlnhatro.Fragment.InvoiceFragment;
import com.example.qlnhatro.Model.ChiTietHoaDon;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvoiceDetailActivity extends AppCompatActivity {

    private Intent intent;
    private ArrayList<ChiTietHoaDon> listID;
    private RecyclerView rclIDList;
    private InvoiceDetailAdapter invoiceDetailAdapter;
    private int id;
    private ArrayList<ChiTietHoaDon> listtest = new ArrayList<ChiTietHoaDon>();
    private FloatingActionButton btnAddID, btnThoatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_invoice_detail);

        rclIDList = findViewById(R.id.rclInvoiceDetailList);
        btnAddID = findViewById(R.id.btnAddID);
        btnThoatID = findViewById(R.id.btnThoatID);
        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        showProgressDialog(InvoiceDetailActivity.this, "Đang tải dữ liệu. Vui lòng chờ !");
        getID(id);

        btnAddID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InvoiceDetailActivity.this, AddDetailInvoiceActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        btnThoatID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(InvoiceDetailActivity.this, InvoiceFragment.class);
//                intent.putExtra()
                finish();
            }
        });
    }

    private void getID(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetDetailInvoice(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<ChiTietHoaDon> chiTietHoaDon) {
        try {
            rclIDList.setHasFixedSize(true);
            rclIDList.setLayoutManager(new LinearLayoutManager(this));
            invoiceDetailAdapter = new InvoiceDetailAdapter(chiTietHoaDon, InvoiceDetailActivity.this);
            rclIDList.setAdapter(invoiceDetailAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
    }
}