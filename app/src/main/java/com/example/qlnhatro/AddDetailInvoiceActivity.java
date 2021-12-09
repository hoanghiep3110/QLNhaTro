package com.example.qlnhatro;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qlnhatro.Detail.InvoiceDetailActivity;
import com.example.qlnhatro.Model.ChiTietHoaDon;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Service;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddDetailInvoiceActivity extends AppCompatActivity {

    public EditText edt2,edt3,edt4,edt5;
    public Button btn1, btn2;
    private Intent intent;
    private ImageButton lich1, lich2;
    private Spinner spnDichVu;
    private int dichvu;

    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail_invoice);

        spnDichVu= findViewById(R.id.spnDichVu);
        edt2= findViewById(R.id.edtTunNgay);
        edt3= findViewById(R.id.edtToiNgay);
        edt4= findViewById(R.id.edtChiSoCu);
        edt5= findViewById(R.id.edtChiSoMoi);
        btn1= findViewById(R.id.btnThemHoaDon);
        btn2= findViewById(R.id.btnThoatHoaDon);
        lich1 = findViewById(R.id.btnStartDate);
        lich2 = findViewById(R.id.btnEndDate);

        getListDichVu();
        spnDichVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Service service = (Service)parent.getSelectedItem();
                dichvu = service.getIdDichVu();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        simpleDateFormat  = new SimpleDateFormat("yyyy/MM/dd");

        intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        lich1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseStartDate();
            }
        });

        lich2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseEndDate();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt2.getText().toString().equals("") || edt3.getText().toString().equals("") || edt4.getText().toString().equals("") || edt5.getText().toString().equals("")){
                    Toast.makeText(AddDetailInvoiceActivity.this, "Vui lòng nhập đầy đủ thông tin !", Toast.LENGTH_LONG).show();
                }else {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(dichvu, edt2.getText().toString(), edt3.getText().toString(), Integer.parseInt(edt4.getText().toString()), Integer.parseInt(edt5.getText().toString()));
                    addInvoiceDetail(id, chiTietHoaDon);
                }
            }

            private void addInvoiceDetail(int id, ChiTietHoaDon chiTietHoaDon) {
                ServiceAPI requestInterface = new Retrofit.Builder()
                        .baseUrl(BASE_Service)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ServiceAPI.class);

                new CompositeDisposable().add(requestInterface.AddInvoice(id, chiTietHoaDon)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError)
                );
            }
            private void handleResponse(Message message) {
                dismissProgressDialog();
                try {
                    Toast.makeText(AddDetailInvoiceActivity.this, message.getNotification(), Toast.LENGTH_SHORT).show();
                    if (message.getStatus() == 1) {
                        Intent intent = new Intent(AddDetailInvoiceActivity.this, InvoiceDetailActivity.class);
                        intent.putExtra("id", id);
                        finish();
                        startActivity(intent);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            private void handleError(Throwable throwable) {
                dismissProgressDialog();
            }
        });
    }

    private void getListDichVu() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllService()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<Service> services) {
        try {
                ArrayAdapter<Service> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, services);
                spnDichVu.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }
    private void handleError(Throwable error) {
        dismissProgressDialog();
    }

    public void  chooseStartDate() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                edt2.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
    public void  chooseEndDate() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                edt3.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}