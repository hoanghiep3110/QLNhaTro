package com.example.qlnhatro.Fragment;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;
import static com.example.qlnhatro.other.ShowNotifyUser.showProgressDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.example.qlnhatro.Adapter.RoomAdapter;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.example.qlnhatro.other.ShowNotifyUser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomFragment extends Fragment {

    private ArrayList<Room> alRoom;

    private RecyclerView rclRoomList;
    private RoomAdapter roomAdapter;
    private FloatingActionButton btnAdd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_room, container, false);
        rclRoomList = view.findViewById(R.id.rclRoomList);
        btnAdd = view.findViewById(R.id.btnAdd);
        showProgressDialog(getActivity(),"Đang tải dữ liệu nè !");
        getRoom();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        return view;
    }

    public void getRoom() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllRoom()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<Room> rooms) {
        try {
            rclRoomList.setHasFixedSize(true);
            rclRoomList.setLayoutManager(new LinearLayoutManager(getActivity()));
            roomAdapter = new RoomAdapter(rooms, RoomFragment.this);
            rclRoomList.setAdapter(roomAdapter);
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
        dialog.setContentView(R.layout.addroom_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtPhong = dialog.findViewById(R.id.edtPhong);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btnThoat = dialog.findViewById(R.id.btnThoat);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room room = new Room(edtPhong.getText().toString());
                addRoom(room);
            }

            private void addRoom(Room room) {
                ServiceAPI requestInterface = new Retrofit.Builder()
                        .baseUrl(BASE_Service)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ServiceAPI.class);

                new CompositeDisposable().add(requestInterface.AddRoom(room)
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
                        Toast.makeText(getActivity(),"Thêm thành công !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void handleError(Throwable throwable) {
                dismissProgressDialog();
                Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();
            }

        });
        dialog.show();
    }
}
