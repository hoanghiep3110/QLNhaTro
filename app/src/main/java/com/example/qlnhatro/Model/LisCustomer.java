package com.example.qlnhatro.Model;

import java.util.ArrayList;

public class LisCustomer {
    private ArrayList<Customer> customers;

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public LisCustomer(ArrayList<Customer> customers) {
        this.customers = customers;
    }
}
