package com.example.qlnhatro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
        holder.txtTenphong.setText(alRoom.get(position).getNameRoom());
        //holder.txtTrangthai.setText(alRoom.get(position).get());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
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

        public TextView txtTenphong,txtTrangthai;
        public ImageButton btnEdit,btnDelete;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtTenphong = itemView.findViewById(R.id.txtTenphong);
            txtTrangthai = itemView.findViewById(R.id.txtTrangthai);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }

    //Room apt = alRoom.get(2);
}
