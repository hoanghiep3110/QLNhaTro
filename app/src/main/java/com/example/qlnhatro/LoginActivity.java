package com.example.qlnhatro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlnhatro.Database.Database;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUser, edtPass;
    private TextView txtDk;
    private Button btnDn;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        db = new Database(this);

        txtDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        edtPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN){
                        btnDn.callOnClick();
                    }
                    return true;
                }
                return false;
            }
        });

        btnDn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUser.getText().toString().trim().equals("")||edtPass.getText().toString().trim().equals("")){
                    Toast.makeText(LoginActivity.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {

                    if(db.checkUser(edtUser.getText().toString().trim(),edtPass.getText().toString().trim())){
                        edtUser.setText("");
                        edtPass.setText("");
                        Intent intent = new Intent(LoginActivity.this, MenuDashboardActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void init(){
        edtUser = findViewById(R.id.edtTk);
        edtPass = findViewById(R.id.edtMk);
        txtDk = findViewById(R.id.tvRegisterHere);
        btnDn = findViewById(R.id.btnDn1);
        db = new Database(this);
    }

}