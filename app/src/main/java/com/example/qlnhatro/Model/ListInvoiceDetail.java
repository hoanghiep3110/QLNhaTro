package com.example.qlnhatro.Model;

import java.util.ArrayList;

public class ListInvoiceDetail {
    private ArrayList<ChiTietHoaDon> invoiceDetail;

    public ListInvoiceDetail(ArrayList<ChiTietHoaDon> invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }

    public ArrayList<ChiTietHoaDon> getInvoices() {
        return invoiceDetail;
    }

    public void setInvoices(ArrayList<ChiTietHoaDon> invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }
}
