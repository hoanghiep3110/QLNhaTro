package com.example.qlnhatro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import Database.Account;
import House.Accounts;

import java.util.ArrayList;
import java.util.List;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class MainActivity2 extends AppCompatActivity {
    private EditText edtTen,edtDc,edtSdt,edtTK,edtMk;
    private Button btnDk;

    Account db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtTen = findViewById(R.id.edtTen);
        edtDc = findViewById(R.id.edtDc);
        edtSdt = findViewById(R.id.edtSdt);
        edtTK = findViewById(R.id.edtTk);
        edtMk = findViewById(R.id.edtMk);
        btnDk = findViewById(R.id.btnDk);
        db = new Account(this);

        btnDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtTen.getText().toString().trim();
                String phone= edtSdt.getText().toString().trim();
                String address=edtDc.getText().toString().trim();
                String username=edtTK.getText().toString().trim();
                String password=edtMk.getText().toString().trim();
                Accounts taikhoan = create();


                if(name.equals("")||address.equals("")
                ||phone.equals("")||username.equals("")
                ||password.equals("")){
                    Toast.makeText(MainActivity2.this, "Không được bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.insertAccount(taikhoan);
                    Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity2.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public Accounts create(){
        String name=edtTen.getText().toString().trim();
        String phone= edtSdt.getText().toString().trim();
        String address=edtDc.getText().toString().trim();
        String username=edtTK.getText().toString().trim();
        String password=edtMk.getText().toString().trim();
        Accounts account1 = new Accounts(name,phone,address,username,password);
        return account1;
    }
}