package com.example.qlnhatro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import com.example.qlnhatro.Adapter.ApartmentAdapter;
import com.example.qlnhatro.Model.Room;

public class MainActivity4 extends AppCompatActivity {

    private Button btnChange;
    private ArrayList<Room> alRoom;


    private RecyclerView rclRoomList;
    private ApartmentAdapter roomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_room);

        rclRoomList = findViewById(R.id.rclRoomList);

        alRoom = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            alRoom.add(new Room(i,"Ten Phong " + i));
        }

        rclRoomList.setHasFixedSize(true);

        roomAdapter = new ApartmentAdapter(alRoom,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        rclRoomList.setLayoutManager(linearLayoutManager);
        rclRoomList.setAdapter(roomAdapter);
    }
}