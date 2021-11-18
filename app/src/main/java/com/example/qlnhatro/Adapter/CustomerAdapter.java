package com.example.qlnhatro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Model.Customer;
import com.example.qlnhatro.R;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private ArrayList<Customer> alCus;
    private Context context;

    public CustomerAdapter(ArrayList<Customer> alCus,Context context) {
        this.context = context;
        this.alCus = alCus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTen.setText(alCus.get(position).getHoTen());
        holder.txtGioitinh.setText(alCus.get(position).getGioiTinh());
    }


    @Override
    public int getItemCount() {
        return alCus.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTen, txtGioitinh;
        public ImageButton btnSua, btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtGioitinh = itemView.findViewById(R.id.txtGioitinh);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);

        };
    }
}
