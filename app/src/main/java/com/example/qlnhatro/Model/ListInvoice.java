package com.example.qlnhatro.Model;

import java.util.ArrayList;

public class ListInvoice {
    private ArrayList<HoaDonDichVu> invoices;

    public ListInvoice(ArrayList<HoaDonDichVu> invoices) {
        this.invoices = invoices;
    }

    public ArrayList<HoaDonDichVu> getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList<HoaDonDichVu> invoices) {
        this.invoices = invoices;
    }
}
