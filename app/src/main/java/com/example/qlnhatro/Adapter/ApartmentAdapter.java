package com.example.qlnhatro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Model.Room;
import com.example.qlnhatro.R;

import java.util.ArrayList;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ViewHolder> {

    private ArrayList<Room> alRoom;
    private Context context;

    public ApartmentAdapter(ArrayList<Room> alRoom, Context context) {
        this.alRoom = alRoom;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.apartment_item, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return alRoom.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName, txtStatus;
        public ImageButton btnEdit, btnDelete;

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
//        public void DeleteRoom(){
//            ServiceAPI requestInterface = new Retrofit.Builder()
//                    .baseUrl(BASE_Service)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build().create(ServiceAPI.class);
//
//            new CompositeDisposable().add(requestInterface.DeleteRoom(20)
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(this::handleResponse, this::handleError)
//            );
//        }
//
//        private void handleError(Throwable throwable) {
//        }
//
//        private void handleResponse(Void unused) {
//        }


        //Room apt = alRoom.get(2);
    }
}
