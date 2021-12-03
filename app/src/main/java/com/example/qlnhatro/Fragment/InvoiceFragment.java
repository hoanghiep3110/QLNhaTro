package com.example.qlnhatro.Fragment;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;
import static com.example.qlnhatro.other.ShowNotifyUser.showProgressDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.qlnhatro.Adapter.InvoiceAdapter;

import com.example.qlnhatro.Model.HoaDonDichVu;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvoiceFragment extends Fragment {

    private ArrayList<HoaDonDichVu> alHD;
    private RecyclerView rclHDlist;
    private InvoiceAdapter invoiceAdapter;

    @Override
    public void onResume() {
        getInvoice();
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_invoice, container, false);

        rclHDlist = view.findViewById(R.id.rclInvoiceList);

        alHD = new ArrayList<>();
        showProgressDialog(getActivity(), "Đang tải dữ liệu. Vui lòng chờ !");
        getInvoice();

        return view;
    }

    private void getInvoice() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllInvoice()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleError(Throwable throwable) {
        dismissProgressDialog();
    }

    private void handleResponse(ArrayList<HoaDonDichVu> hoaDonDichVus) {
        try {
            rclHDlist.setHasFixedSize(true);
            rclHDlist.setLayoutManager(new LinearLayoutManager(getActivity()));
            invoiceAdapter = new InvoiceAdapter(hoaDonDichVus, InvoiceFragment.this);
            rclHDlist.setAdapter(invoiceAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }


}

