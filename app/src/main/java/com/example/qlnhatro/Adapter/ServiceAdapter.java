package com.example.qlnhatro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Model.Service;
import com.example.qlnhatro.R;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private ArrayList<Service> alService;
    private Context context;

    public ServiceAdapter(ArrayList<Service> alService,Context context) {
        this.context = context;
        this.alService = alService;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTendichvu.setText(alService.get(position).getTenDichVu());
        holder.txtDongia.setText(String.valueOf(alService.get(position).getDonGia()) + " VND");
    }

    @Override
    public int getItemCount() {
        return alService.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTendichvu, txtDongia;
        public ImageButton btnSua, btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTendichvu = itemView.findViewById(R.id.txtTendichvu);
            txtDongia = itemView.findViewById(R.id.txtDongia);
            btnSua = itemView.findViewById(R.id.btnEditService);
            btnXoa = itemView.findViewById(R.id.btnDeleteService);
        };
    }
}
