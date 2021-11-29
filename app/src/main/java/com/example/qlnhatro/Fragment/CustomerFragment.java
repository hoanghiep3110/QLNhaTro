package com.example.qlnhatro.Fragment;

import static com.example.qlnhatro.Service.ServiceAPI.BASE_Service;
import static com.example.qlnhatro.other.ShowNotifyUser.dismissProgressDialog;
import static com.example.qlnhatro.other.ShowNotifyUser.showProgressDialog;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhatro.Adapter.CustomerAdapter;
import com.example.qlnhatro.Model.Customer;
import com.example.qlnhatro.Model.Message;
import com.example.qlnhatro.R;
import com.example.qlnhatro.Service.ServiceAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import gun0912.tedbottompicker.TedRxBottomPicker;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerFragment extends Fragment {

    private ArrayList<Customer> alCustomer;
    private RecyclerView rclCusList;
    private CustomerAdapter customerAdapter;
    private FloatingActionButton btnAddCustommer;

    @Override
    public void onResume() {
        getCustomer();
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_customer, container, false);
        rclCusList = view.findViewById(R.id.rclCusList);
        btnAddCustommer = view.findViewById(R.id.btnAddCustommer);
        alCustomer = new ArrayList<>();
        showProgressDialog(getActivity(), "Đang tải dữ liệu. Vui lòng chờ !");
        getCustomer();
        btnAddCustommer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        return view;
    }

    public void getCustomer() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllCustomer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<Customer> customers) {
        try {
            rclCusList.setHasFixedSize(true);
            rclCusList.setLayoutManager(new LinearLayoutManager(getActivity()));
            customerAdapter = new CustomerAdapter(customers, CustomerFragment.this);
            rclCusList.setAdapter(customerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }

    private void handleError(Throwable error) {
        dismissProgressDialog();
    }

    public void openDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.addcustomer_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtHoTen = dialog.findViewById(R.id.edtHoTen);
        EditText edtSDTKH = dialog.findViewById(R.id.edtSDTKH);
        EditText edtGioiTinh = dialog.findViewById(R.id.edtGioiTinh);
        EditText edtQueQuan = dialog.findViewById(R.id.edtQueQuan);
        EditText edtHKTT = dialog.findViewById(R.id.edtHKTT);
        ImageView showhinh = dialog.findViewById(R.id.showhinh);
        Button btnThemKH = dialog.findViewById(R.id.btnThemKH);
        Button btnThoatKH = dialog.findViewById(R.id.btnThoatKH);
        Button nhaphinh = dialog.findViewById(R.id.nhaphinh);

        nhaphinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhapHinh();
            }

            private void nhapHinh() {
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(getActivity(), "Cấp quyền thành công !", Toast.LENGTH_SHORT).show();
                        showHinh();
                    }

                    private void showHinh() {
                        TedBottomPicker.with(getActivity()).show( new TedBottomSheetDialogFragment.OnImageSelectedListener() {
                            @Override
                            public void onImageSelected(Uri uri) {
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                                    showhinh.setImageBitmap(bitmap);
                                }catch(IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(getActivity(), "Cấp quyền thất bại !\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }
                };
                TedPermission.create()
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("Nếu bạn từ chối cấp quyền, bạn không thể dùng ứng dụng\n\nVui lòng cấp quyền tại [Cài đặt] > [Quyền]")
                        .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
            }
        });

        btnThoatKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = new Customer(edtHoTen.getText().toString(), edtSDTKH.getText().toString(), edtGioiTinh.getText().toString(),
                        edtQueQuan.getText().toString(), edtHKTT.getText().toString());
                addCustomer(customer);
            }

            private void addCustomer(Customer customer) {
                ServiceAPI requestInterface = new Retrofit.Builder()
                        .baseUrl(BASE_Service)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ServiceAPI.class);

                new CompositeDisposable().add(requestInterface.AddCustomer(customer)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError)
                );
            }
            private void handleResponse(Message message) {
                dismissProgressDialog();
                try {
                    Toast.makeText(getActivity(), message.getNotification(), Toast.LENGTH_SHORT).show();
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
        dialog.show();
    }
}
