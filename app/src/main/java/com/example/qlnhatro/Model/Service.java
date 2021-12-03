package com.example.qlnhatro.Model;

public class Service {
    private int IdDichVu;

    public Service(int idDichVu, int donGia, String tenDichVu) {
        IdDichVu = idDichVu;
        DonGia = donGia;
        TenDichVu = tenDichVu;
    }

    @Override
    public String toString() {
        return TenDichVu;
    }

    private int DonGia;

    public Service(String tenDichVu, int donGia) {
        DonGia = donGia;
        TenDichVu = tenDichVu;
    }

    private String TenDichVu;

    public int getIdDichVu() {
        return IdDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        IdDichVu = idDichVu;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        DonGia = donGia;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        TenDichVu = tenDichVu;
    }
}
