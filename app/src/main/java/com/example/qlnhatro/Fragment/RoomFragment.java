package com.example.qlnhatro.Fragment;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Adapter.ApartmentAdapter;
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
        getRoom();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCheckoutDialog(Gravity.CENTER);
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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rclRoomList.setLayoutManager(linearLayoutManager);
            roomAdapter = new ApartmentAdapter(rooms, context);
            rclRoomList.setAdapter(roomAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dismissProgressDialog();
    }
    private void handleError(Throwable error) {
        //dismissProgressDialog();
    }

    public void openCheckoutDialog(int gravity) {
        final Dialog dialog = new Dialog(context);
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
                //Room room = new Room(edtPhong.getText().toString());

//                Intent intent = new Intent(RoomDetail.this,ListRoomActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
        dialog.show();
    }
}
