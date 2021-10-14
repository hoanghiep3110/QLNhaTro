package com.example.qlnhatro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Database.Account;
import House.Accounts;

public class MainActivity extends AppCompatActivity {
    private EditText edtUser, edtPass;
    private TextView txtDk;
    private Button btnDn;
    Account db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUser = findViewById(R.id.edtTk);
        edtPass = findViewById(R.id.edtMk);
        txtDk = findViewById(R.id.tvRegisterHere);
        btnDn = findViewById(R.id.btnDn1);

        db = new Account(this);

        txtDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        btnDn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                Cursor cursor = db.getAllAccount();
                while (cursor.moveToNext()) {
                    String taikhoan = cursor.getString(4);
                    String matkhau = cursor.getString(5);
                    if (taikhoan.equals(user) && matkhau.equals(pass)) {
                        String ten = cursor.getString(1);
                        Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                        intent.putExtra("ten", ten);
                        startActivity(intent);

                    }
                }
                cursor.moveToFirst();
                db.close();
//
            }
        });
    }

}