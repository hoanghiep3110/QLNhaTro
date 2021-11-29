package com.example.qlnhatro.Model;

import java.util.ArrayList;

public class ListAccounts {

    private ArrayList<Accounts> accounts;

    public ListAccounts(ArrayList<Accounts> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Accounts> accounts) {
        this.accounts = accounts;
    }
}
