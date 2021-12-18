package com.example.qlnhatro.Fragment;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;
import static com.example.qlnhatro.other.ShowNotifyUser.showProgressDialog;

import android.app.Dialog;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Adapter.ServiceAdapter;
import com.example.qlnhatro.MenuDashboardActivity;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Service;
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

public class ServiceFragment extends Fragment {

    private ArrayList<Service> alService;
    private RecyclerView rclServiceList;
    private ServiceAdapter serviceAdapter;
    private FloatingActionButton btnAdd;

    @Override
    public void onResume() {
        getService();
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.list_service,container,false);
        rclServiceList = view.findViewById(R.id.rclServiceList);
        btnAdd = view.findViewById(R.id.btnAddService);

        showProgressDialog(getActivity(),"Đang tải dữ liệu. Vui lòng chờ !");
        getService();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        return view;
    }

    public void getService() {
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
            rclServiceList.setHasFixedSize(true);
            rclServiceList.setLayoutManager(new LinearLayoutManager(getActivity()));
            serviceAdapter = new ServiceAdapter(services, ServiceFragment.this);
            rclServiceList.setAdapter(serviceAdapter);
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
        dialog.setContentView(R.layout.addservice_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenDichVu = dialog.findViewById(R.id.edtTenDichVu);
        EditText edtDonGia = dialog.findViewById(R.id.edtDonGia);
        Button btnThemDichVu = dialog.findViewById(R.id.btnThemDichVu);
        Button btnThoatDV = dialog.findViewById(R.id.btnThoatDV);

        btnThoatDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        edtDonGia.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN){
                        btnThemDichVu.callOnClick();
                    }
                    return true;
                }
                return false;
            }
        });

        btnThemDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTenDichVu.getText().toString().equals("") || edtDonGia.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin !", Toast.LENGTH_LONG).show();
                } else {
                    Service service = new Service(edtTenDichVu.getText().toString(), Integer.parseInt(edtDonGia.getText().toString()));
                    addService(service);
                    replaceFragment(new ServiceFragment());
                    dialog.dismiss();
                }
            }

            private void replaceFragment(Fragment fragment){
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,fragment);
                transaction.commit();
            }

            private void addService(Service service) {
                ServiceAPI requestInterface = new Retrofit.Builder()
                        .baseUrl(BASE_Service)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ServiceAPI.class);

                new CompositeDisposable().add(requestInterface.AddService(service)
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
                        startActivity(new Intent(getActivity(), MenuDashboardActivity.class));
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
