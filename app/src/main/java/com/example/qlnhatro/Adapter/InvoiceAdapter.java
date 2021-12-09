package com.example.qlnhatro.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Detail.InvoiceDetailActivity;
import com.example.qlnhatro.Model.HoaDonDichVu;
import com.example.qlnhatro.R;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder>{

    private ArrayList<HoaDonDichVu> alInvoice;
    private Fragment context;

    public InvoiceAdapter(ArrayList<HoaDonDichVu> alInvoice, Fragment context) {
        this.alInvoice = alInvoice;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_invoice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtHoTenHD.setText(alInvoice.get(position).getHoTen());
        holder.txtPhongHD.setText(alInvoice.get(position).getTenPhong());
        holder.txtTienThanhToan.setText(String.valueOf(alInvoice.get(position).getTienThanhToan()));
        boolean b = alInvoice.get(position).isTrangThaiThanhToan();
        if (b == true) {
            holder.txtTrangThaiThanhToan.setText("ĐÃ THANH TOÁN");
            holder.txtTrangThaiThanhToan.setTextColor(Color.parseColor("#4CAF50"));
        } else{
            holder.txtTrangThaiThanhToan.setText("CHƯA THANH TOÁN");
            holder.txtTrangThaiThanhToan.setTextColor(Color.parseColor("#E81708"));
        }

        holder.btnXemChiTietHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (holder.getAdapterPosition());
                int id = alInvoice.get(position).getIdHoaDon();
                Intent intent = new Intent(context.getContext(), InvoiceDetailActivity.class);
                intent.putExtra("id",id);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return alInvoice.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHoTenHD, txtPhongHD, txtTienThanhToan, txtTrangThaiThanhToan;
        public ImageButton btnXemChiTietHD;

        public ViewHolder(View itemview) {
            super(itemview);
            txtHoTenHD = itemView.findViewById(R.id.txtHoTenHD);
            txtPhongHD = itemView.findViewById(R.id.txtPhongHD);
            txtTienThanhToan = itemView.findViewById(R.id.txtTienThanhToan);
            txtTrangThaiThanhToan = itemView.findViewById(R.id.txtTrangThaiThanhToan);
            btnXemChiTietHD = itemView.findViewById(R.id.btnXemChiTietHD);

        }
    }
}
