package com.example.qlnhatro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.qlnhatro.R;

import java.util.ArrayList;

import com.example.qlnhatro.House.Room;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ViewHolder> {

    private ArrayList<Room> alRoom;

    public ApartmentAdapter(ArrayList<Room> alRoom, Context context) {
        this.alRoom = alRoom;
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R  .layout.apartment_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.txtTenkhach.setText(alRoom.get(position).getTenKhach());
//        holder.txtTenphong.setText(alRoom.get(position).getTenPhong());
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Đang chọn nút" + (holder.getAdapterPosition()+1),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return alRoom.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTenphong,txtTenkhach;
        public Button btnDetails;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtTenphong = itemView.findViewById(R.id.txtTenphong);
            txtTenkhach = itemView.findViewById(R.id.txtTenkhach);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }

    Room apt = alRoom.get(2);
}
