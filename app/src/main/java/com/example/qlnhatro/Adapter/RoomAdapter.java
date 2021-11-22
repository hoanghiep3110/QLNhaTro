package com.example.qlnhatro.Adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;

import android.app.Dialog;
import android.content.Context;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Detail.RoomDetail;
import com.example.qlnhatro.MenuDashboardActivity;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private ArrayList<Room> alRoom;
    private Fragment context;

    public RoomAdapter(ArrayList<Room> alRoom, Fragment context) {
        this.alRoom = alRoom;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(alRoom.get(position).getTenPhong());
        int b = alRoom.get(position).getTrangThai();
        if (b == 1) {
            holder.txtStatus.setText("ĐÃ CÓ NGƯỜI");
            holder.txtStatus.setTextColor(Color.parseColor("#4CAF50"));
        } else if(b == 0 ) {
            holder.txtStatus.setText("PHÒNG ĐANG TRỐNG");
            holder.txtStatus.setTextColor(Color.parseColor("#E81708"));
        }else{
            holder.txtStatus.setText("ĐANG SỬA CHỮA");
            holder.txtStatus.setTextColor(Color.parseColor("#113CFC"));}

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (holder.getAdapterPosition());
                int id = alRoom.get(position).getIdPhong();
                Intent intent = new Intent(context.getContext(), RoomDetail.class);
                intent.putExtra("id",id);
                v.getContext().startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
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
                        int id = alRoom.get(position).getIdPhong();
                        deleteRoom(id);

                    }

                    private void deleteRoom(int id) {
                        ServiceAPI requestInterface = new Retrofit.Builder()
                                .baseUrl(BASE_Service)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build().create(ServiceAPI.class);

                        new CompositeDisposable().add(requestInterface.DeleteRoom(id)
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
                                dialog.dismiss();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
        }
    }
}
