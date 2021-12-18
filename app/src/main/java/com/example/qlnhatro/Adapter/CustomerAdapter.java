package com.example.qlnhatro.Adapter;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Detail.CustomerDetailActivity;
import com.example.qlnhatro.Fragment.CustomerFragment;
import com.example.qlnhatro.Model.Customer;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private ArrayList<Customer> alCus;
    private Fragment context;

    public CustomerAdapter(ArrayList<Customer> alCus, Fragment context) {
        this.context = context;
        this.alCus = alCus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.list_item_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTen.setText(alCus.get(position).getHoTen());
        holder.txtGioitinh.setText(alCus.get(position).getGioiTinh());
        String gioitinh = alCus.get(position).getGioiTinh().toLowerCase();
        if(gioitinh.equals("nam")){
            holder.img.setImageResource(R.drawable.nam);
        }else{
            holder.img.setImageResource(R.drawable.nu);
        }

        holder.btnSuaKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (holder.getAdapterPosition());
                int id = alCus.get(position).getIdKhachHang();
                Intent intent = new Intent(context.getContext(), CustomerDetailActivity.class);
                intent.putExtra("id",id);
                v.getContext().startActivity(intent);
            }
        });

        holder.btnXoaKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
            private void openDialog() {
                Dialog dialog = new Dialog(context.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.confirm_delete);
                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (holder.getAdapterPosition());
                        int id = alCus.get(position).getIdKhachHang();
                        deleteCustomer(id);

                    }
                    private void deleteCustomer(int id) {
                        ServiceAPI requestInterface = new Retrofit.Builder()
                                .baseUrl(BASE_Service)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build().create(ServiceAPI.class);

                        new CompositeDisposable().add(requestInterface.DeleteCustomer(id)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::handleResponse, this::handleError)
                        );
                    }
                    private void handleResponse(Message message) {
                        dismissProgressDialog();
                        try {
                            Toast.makeText(context.getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                            if (message.getStatus() == 1) {
                                replaceFragment(new CustomerFragment());
                                dialog.dismiss();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    private void replaceFragment(Fragment fragment){
                        FragmentTransaction transaction = context.getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_frame,fragment);
                        transaction.commit();
                    }

                    private void handleError(Throwable throwable) {
                        dismissProgressDialog();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return alCus.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTen, txtGioitinh;
        public ImageButton btnSuaKhach, btnXoaKhach;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtGioitinh = itemView.findViewById(R.id.txtGioitinh);
            btnSuaKhach = itemView.findViewById(R.id.btnSuaKhach);
            btnXoaKhach = itemView.findViewById(R.id.btnXoaKhach);
            img = itemView.findViewById(R.id.hinhgioitinh);

        };
    }
}
