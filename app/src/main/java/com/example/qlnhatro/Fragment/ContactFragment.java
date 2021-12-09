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

import com.example.qlnhatro.Adapter.ContactAdapter;;
import com.example.qlnhatro.Model.Contact;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactFragment extends Fragment {

    private ArrayList<Contact> alContact;
    private RecyclerView rclConList;
    private ContactAdapter contactAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_contact, container, false);
        rclConList = view.findViewById(R.id.rclConList);
        alContact = new ArrayList<>();
        showProgressDialog(getActivity(), "Đang tải dữ liệu. Vui lòng chờ !");
        getContact();
        return view;
    }

    private void getContact() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllContact()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleError(Throwable throwable) {dismissProgressDialog();}

    private void handleResponse(ArrayList<Contact> contacts) {
        try {
            rclConList.setHasFixedSize(true);
            rclConList.setLayoutManager(new LinearLayoutManager(getActivity()));
            contactAdapter = new ContactAdapter(contacts, ContactFragment.this);
            rclConList.setAdapter(contactAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }
}
