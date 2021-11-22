package com.example.qlnhatro.Fragment;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;
import static com.example.qlnhatro.other.ShowNotifyUser.showProgressDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Adapter.CustomerAdapter;
import com.example.qlnhatro.Adapter.RoomAdapter;
import com.example.qlnhatro.MenuDashboardActivity;
import com.example.qlnhatro.Model.Customer;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Room;
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

public class CustomerFragment extends Fragment {

    private ArrayList<Customer> alCustomer;
    private RecyclerView rclCusList;
    private CustomerAdapter customerAdapter;
    private FloatingActionButton btnAddCustommer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_customer, container, false);
        rclCusList = view.findViewById(R.id.rclCusList);
        btnAddCustommer = view.findViewById(R.id.btnAddCustommer);
        alCustomer = new ArrayList<>();
        showProgressDialog(getActivity(),"Đang tải dữ liệu nè. Vui lòng chờ !");
        getCustomer();
        btnAddCustommer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        return view;
    }

    public void getCustomer() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllCustomer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<Customer> customers) {
        try {
            rclCusList.setHasFixedSize(true);
            rclCusList.setLayoutManager(new LinearLayoutManager(getActivity()));
            customerAdapter = new CustomerAdapter(customers, CustomerFragment.this);
            rclCusList.setAdapter(customerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }
    private void handleError(Throwable error) {
        dismissProgressDialog();
    }

    public void openDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.addcustomer_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtHoTen = dialog.findViewById(R.id.edtHoTen);
        EditText edtSDTKH = dialog.findViewById(R.id.edtSDTKH);
        EditText edtGioiTinh = dialog.findViewById(R.id.edtGioiTinh);
        EditText edtQueQuan = dialog.findViewById(R.id.edtQueQuan);
        EditText edtHKTT = dialog.findViewById(R.id.edtHKTT);
        EditText edtCMND = dialog.findViewById(R.id.edtCMND);
        Button btnThemKH = dialog.findViewById(R.id.btnThemKH);
        Button btnThoatKH = dialog.findViewById(R.id.btnThoatKH);

        btnThoatKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        edtCMND.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN){
                        btnThemKH.callOnClick();
                    }
                    return true;
                }
                return false;
            }
        });

        btnThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = new Customer(edtHoTen.getText().toString(), edtSDTKH.getText().toString(), edtGioiTinh.getText().toString(),
                        edtQueQuan.getText().toString(), edtHKTT.getText().toString(), edtCMND.getText().toString());
                addCustomer(customer);
            }

            private void addCustomer(Customer customer) {
                ServiceAPI requestInterface = new Retrofit.Builder()
                        .baseUrl(BASE_Service)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ServiceAPI.class);

                new CompositeDisposable().add(requestInterface.AddCustomer(customer)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError)
                );
            }
            private void handleResponse(Message message) {
                dismissProgressDialog();
                try {
                    Toast.makeText(getActivity(), message.getNotification(), Toast.LENGTH_SHORT).show();
                    if (message.getStatus() == 1) {
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            private void handleError(Throwable throwable) {
                dismissProgressDialog();
            }
        });
        dialog.show();
    }
}
