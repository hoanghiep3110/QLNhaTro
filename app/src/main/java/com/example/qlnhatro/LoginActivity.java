package com.example.qlnhatro;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qlnhatro.Helper.MD5Hash;
import com.example.qlnhatro.Model.Accounts;
import com.example.qlnhatro.Service.ServiceAPI;
import com.example.qlnhatro.Service.UserSession;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUser, edtPass;
    private TextView txtDk;
    private Button btnDn;
    private List<Accounts>  mListUser;
    private Accounts mUser;

    @Override
    protected void onStart() {
        // check session
        UserSession mySession = new UserSession(LoginActivity.this);
        if(mySession.checkLogin() == true)
        {
            startActivity(new Intent(LoginActivity.this, MenuDashboardActivity.class));
        }
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        mListUser = new ArrayList<>();
        
        getListUser();

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
                clickButtonDn();
                
            }

            private void clickButtonDn() {
                String tdn = edtUser.getText().toString().trim();
                String pass = MD5Hash.encrypt(edtPass.getText().toString().trim());

                if(mListUser == null || mListUser.isEmpty())
                {
                    return;
                }

                boolean hasUser = false;
                for(Accounts ac : mListUser){
                    if(tdn.equals(ac.getUsername()) && pass.equals(ac.getPassword())){
                        hasUser = true;
                        mUser = ac;
                        break;
                    }
                }
                if(hasUser == true){
                    UserSession mySession = new UserSession(LoginActivity.this);
                    mySession.createLoginSession(mUser.getIdTaiKhoan(), mUser.getUsername(), mUser.getHoTen(), mUser.getSdt(), mUser.getDiaChi());
                    edtUser.setText("");
                    edtPass.setText("");
                    Intent intent = new Intent(LoginActivity.this, MenuDashboardActivity.class);
                    startActivity(intent);
                }
                else {
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Có biến rồi !")
                            .setContentText("Sai tên đăng nhập hoặc mật khẩu")
                            .show();
                }
            }
        });
    }
    private void getListUser() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllAccouunts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<Accounts> accounts) {
        try {
                mListUser = accounts;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleError(Throwable error) {
    }

    public void init(){
        edtUser = findViewById(R.id.edtTk);
        edtPass = findViewById(R.id.edtMk);
        txtDk = findViewById(R.id.tvRegisterHere);
        btnDn = findViewById(R.id.btnDn1);
    }
}