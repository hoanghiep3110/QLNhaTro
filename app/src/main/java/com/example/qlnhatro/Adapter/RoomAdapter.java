package com.example.qlnhatro.Adapter;


import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Detail.RoomDetailActivity;

import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.R;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private ArrayList<Room> alRoom;
    private Context context;

    public RoomAdapter(ArrayList<Room> alRoom, Context context) {
        this.alRoom = alRoom;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(alRoom.get(position).getTenPhong());
        Boolean b = alRoom.get(position).isTrangThai();
        String str = Boolean.toString(b);
        if (str == "true") {
            holder.txtStatus.setText("Đã có người thuê");
        } else {
            holder.txtStatus.setText("Phòng đang trống");
        }

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (holder.getAdapterPosition());
                int id = alRoom.get(position).getIdPhong();
                Intent intent = new Intent(context, RoomDetailActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alRoom.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName, txtStatus;
        public ImageButton btnEdit, btnDelete;
        public Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtTenphong);
            txtStatus = itemView.findViewById(R.id.txtTrangthai);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);


            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //DeleteRoom();
                }
            });
        }
    }
}
