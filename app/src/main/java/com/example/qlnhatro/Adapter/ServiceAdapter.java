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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Detail.ServiceDetailActivity;
import com.example.qlnhatro.Fragment.ServiceFragment;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Service;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private ArrayList<Service> alService;
    private Fragment context;

    public ServiceAdapter(ArrayList<Service> alService, Fragment context) {
        this.context = context;
        this.alService = alService;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTendichvu.setText(alService.get(position).getTenDichVu());
        holder.txtDongia.setText(alService.get(position).getDonGia() + " VND");

        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (holder.getAdapterPosition());
                int id = alService.get(position).getIdDichVu();
                Intent intent = new Intent(context.getContext(), ServiceDetailActivity.class);
                intent.putExtra("id",id);
                v.getContext().startActivity(intent);
            }
        });

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
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
                        int id = alService.get(position).getIdDichVu();
                        deleteService(id);

                    }

                    private void deleteService(int id) {
                        ServiceAPI requestInterface = new Retrofit.Builder()
                                .baseUrl(BASE_Service)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build().create(ServiceAPI.class);

                        new CompositeDisposable().add(requestInterface.DeleteService(id)
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
                                replaceFragment(new ServiceFragment());
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
        }
    }
}
