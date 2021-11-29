package com.example.qlnhatro.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Detail.ContactDetailActivity;
import com.example.qlnhatro.Model.Contact;
import com.example.qlnhatro.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> alCon;
    private Fragment context;

    public ContactAdapter(ArrayList<Contact> alCon, Fragment context) {
        this.context = context;
        this.alCon = alCon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenNguoiThue.setText(alCon.get(position).getHoTen());
        holder.txtPhongThue.setText(alCon.get(position).getTenPhong());

        holder.btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (holder.getAdapterPosition());
                int id = alCon.get(position).getIdThue();
                Intent intent = new Intent(context.getContext(), ContactDetailActivity.class);
                intent.putExtra("id",id);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {return alCon.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTenNguoiThue, txtPhongThue;
        public ImageButton btnXemChiTiet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenNguoiThue = itemView.findViewById(R.id.txtTenNguoiThue);
            txtPhongThue = itemView.findViewById(R.id.txtPhongThue);
            btnXemChiTiet = itemView.findViewById(R.id.btnXemChiTiet);
        }
    }
}
