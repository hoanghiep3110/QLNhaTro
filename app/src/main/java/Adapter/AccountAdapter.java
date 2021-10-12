package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import Database.Account;

public class AccountAdapter extends BaseAdapter {

    private List<Account> listAccount;
    private Context context;

    public AccountAdapter(Context context, List<Account> listAcc) {
        this.context = context;
        this.listAccount = listAcc;
    }

    @Override
    public int getCount() {
        return listAccount.size();
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
