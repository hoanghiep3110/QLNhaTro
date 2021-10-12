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
                String taikhoan = edtUser.getText().toString();
                String matkhau = edtPass.getText().toString();
                Cursor cursor = db.getAllAccount();
                while (cursor.moveToNext()) {
                    String dattaikhoan = cursor.getString(cursor.getColumnNames());
                    String datmatkhau = cursor.getColumnName(5);
                    if (dattaikhoan.equals(taikhoan) && datmatkhau.equals(matkhau)) {
                        String ten = cursor.getString(2);
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