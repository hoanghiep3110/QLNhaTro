package com.example.qlnhatro.Adapter;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;

import android.app.Activity;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Detail.InvoiceDetailActivity;
import com.example.qlnhatro.Model.ChiTietHoaDon;
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


public class InvoiceDetailAdapter extends RecyclerView.Adapter<InvoiceDetailAdapter.ViewHolder>{

    private ArrayList<ChiTietHoaDon> alID;
    private Context  context;

    public InvoiceDetailAdapter(ArrayList<ChiTietHoaDon> alID, Context  context) {
        this.alID = alID;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_invoice_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tuNgay = alID.get(position).getTuNgay().split("T")[0];
        String toiNgay = alID.get(position).getToiNgay().split("T")[0];
        tuNgay = tuNgay.split("-")[2] +"/"+ tuNgay.split("-")[1] +"/" + tuNgay.split("-")[0] ;
        toiNgay = toiNgay.split("-")[2] +"/"+ toiNgay.split("-")[1] +"/" + toiNgay.split("-")[0] ;
        holder.txt1.setText(alID.get(position).getTenDichVu());
        holder.txt2.setText("từ : " + tuNgay);
        holder.txt3.setText("đến : " + toiNgay);
        holder.txt4.setText(String.valueOf(alID.get(position).getThanhTien()) + " đ");

        int iddichvu = alID.get(position).getIdDichVu();
        int idhoadon = alID.get(position).getIdHoaDon();

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openDiaLog(); }

            private void openDiaLog() {
                Dialog dialog = new Dialog(holder.itemView.getContext());
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
                        deleteInvoice(idhoadon,iddichvu);
                    }
                    private void deleteInvoice(int id, int iddv) {
                        ServiceAPI requestInterface = new Retrofit.Builder()
                                .baseUrl(BASE_Service)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build().create(ServiceAPI.class);

                        new CompositeDisposable().add(requestInterface.DeleteInvoiceDetail(id,iddv)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(this::handleResponse, this::handleError)
                        );
                    }

                    private void handleResponse(Message message) {
                        dismissProgressDialog();
                        try {
                            Toast.makeText(holder.itemView.getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                            if (message.getStatus() == 1) {
                                Intent intent = new Intent(holder.itemView.getContext(), InvoiceDetailActivity.class);
                                intent.putExtra("id", idhoadon);
                                context.startActivity(intent);
                                ((Activity) context).finish();
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
        return alID.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt1, txt2, txt3, txt4;
        public ImageButton btn1;

        public ViewHolder(View itemview) {
            super(itemview);
            txt1 = itemView.findViewById(R.id.txtTendv);
            txt2 = itemView.findViewById(R.id.txtNgay1);
            txt3 = itemView.findViewById(R.id.txtNgay2);
            txt4 = itemView.findViewById(R.id.txtThanhtien);
            btn1 = itemview.findViewById(R.id.btnXoaCTHD);
        }
    }
}
