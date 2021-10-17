package com.example.qlnhatro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        init();


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
//                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
//                startActivity(intent);
                if(edtUser.getText().toString().trim().equals("")||edtPass.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this,"Vui Lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(db.checkUser(edtUser.getText().toString().trim(),edtPass.getText().toString().trim())){
                        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    }
                }
            }


//                Cursor cursor = db.getAllAccount();
//
//                while (cursor.moveToNext()) {
//                    String username = cursor.getString(4);
//                    String password = cursor.getString(5);
//                    if (username.equals(user) && password.equals(pass)) {
//                        String ten = cursor.getString(1);
//                        Log.e( "onClick: ",null );
//                        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
//                        intent.putExtra("ten", ten);
//                        startActivity(intent);
//                    }
//                }
//                cursor.moveToFirst();
//                db.close();

        });
    }
    public void init(){
        edtUser = findViewById(R.id.edtTk);
        edtPass = findViewById(R.id.edtMk);
        txtDk = findViewById(R.id.tvRegisterHere);
        btnDn = findViewById(R.id.btnDn1);

        db = new Account(this);
    }

}