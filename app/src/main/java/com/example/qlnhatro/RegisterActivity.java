package com.example.qlnhatro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.qlnhatro.Database.Database;
import com.example.qlnhatro.Model.Accounts;

import android.widget.Toast;



public class RegisterActivity extends AppCompatActivity {
    private EditText edtTen,edtDc,edtSdt,edtTK,edtMk;
    private Button btnDk;

    Database db;

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
        db = new Database(this);

        edtMk.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN){
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
                String name=edtTen.getText().toString().trim();
                String phone= edtSdt.getText().toString().trim();
                String address=edtDc.getText().toString().trim();
                String username=edtTK.getText().toString().trim();
                String password=edtMk.getText().toString().trim();
                Accounts taikhoan = create();


                if(name.equals("")||address.equals("")
                ||phone.equals("")||username.equals("")
                ||password.equals("")){
                    Toast.makeText(RegisterActivity.this, "Không được bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.insertAccount(taikhoan);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
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