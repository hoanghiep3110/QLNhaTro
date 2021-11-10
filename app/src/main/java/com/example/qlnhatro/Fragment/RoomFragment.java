package com.example.qlnhatro.Fragment;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Adapter.ApartmentAdapter;
import com.example.qlnhatro.Model.ListRoom;
import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
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
    private ApartmentAdapter roomAdapter;
    private Context context;
    private Button btnAdd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_room, container, false);
        rclRoomList = view.findViewById(R.id.rclRoomList);
        btnAdd = view.findViewById(R.id.btnAdd);
//        alRoom = new ArrayList<>();
//        for (int i = 1; i < 10; i++) {
//            alRoom.add(new Room(i, "Ten Phong " + i));
//        }
//        rclRoomList.setHasFixedSize(true);
//        roomAdapter = new ApartmentAdapter(alRoom, context);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        rclRoomList.setLayoutManager(linearLayoutManager);
//        rclRoomList.setAdapter(roomAdapter);
        getRoom();
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
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(ListRoom listRoom) {
        try {
            ArrayList<Room> _alRoom = listRoom.getRooms();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rclRoomList.setLayoutManager(linearLayoutManager);
            roomAdapter = new ApartmentAdapter(_alRoom, context);
            rclRoomList.setAdapter(roomAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }


    private void handleError(Throwable error) {
        dismissProgressDialog();
        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
    }
}
