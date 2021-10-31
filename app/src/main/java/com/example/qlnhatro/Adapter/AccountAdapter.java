package com.example.qlnhatro.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import com.example.qlnhatro.Database.Database;

public class AccountAdapter extends BaseAdapter {

    private List<Database> listDatabase;
    private Context context;

    public AccountAdapter(Context context, List<Database> listAcc) {
        this.context = context;
        this.listDatabase = listAcc;
    }

    @Override
    public int getCount() {
        return listDatabase.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    class ViewHolder{

    }
}
