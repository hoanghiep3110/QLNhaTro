package com.example.qlnhatro;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.qlnhatro.Model.Accounts;
import com.example.qlnhatro.Model.Message;

import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtTen, edtDc, edtSdt, edtTK, edtMk;
    private Button btnDk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtTen = findViewById(R.id.edtTen);
        edtDc = findViewById(R.id.edtDc);
        edtSdt = findViewById(R.id.edtSdt);
        edtTK = findViewById(R.id.edtTk);
        edtMk = findViewById(R.id.edtMk);
        btnDk = findViewById(R.id.btnDk);

        edtMk.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        btnDk.callOnClick();
                    }
                    return true;
                }
                return false;
            }
        });

        btnDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtTen.getText().toString().trim();
                String phone = edtSdt.getText().toString().trim();
                String address = edtDc.getText().toString().trim();
                String username = edtTK.getText().toString().trim();
                String password = edtMk.getText().toString().trim();


                if (name.equals("") || address.equals("")
                        || phone.equals("") || username.equals("")
                        || password.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Không được bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Accounts accounts = new Accounts(name, phone, address, username, password);
                    addAccounts(accounts);
                }
            }

            private void addAccounts(Accounts accounts) {
                ServiceAPI requestInterface = new Retrofit.Builder()
                        .baseUrl(BASE_Service)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ServiceAPI.class);

                new CompositeDisposable().add(requestInterface.AddAccounts(accounts)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError)
                );
            }

            private void handleResponse(Message message) {
                try {
                    Toast.makeText(RegisterActivity.this, message.getNotification(), Toast.LENGTH_SHORT).show();
                    if (message.getStatus() == 1) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void handleError(Throwable throwable) {
            }
        });
    }
}