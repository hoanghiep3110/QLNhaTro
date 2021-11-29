package com.example.qlnhatro.Model;

public class Customer {
    private int IdKhachHang;
    private String HoTen,Sdt,QueQuan,HKTT;
    private String GioiTinh,SoCMND;

    public Customer(String hoTen, String sdt, String gioiTinh, String queQuan, String HKTT) {
        HoTen = hoTen;
        Sdt = sdt;
        QueQuan = queQuan;
        this.HKTT = HKTT;
        GioiTinh = gioiTinh;
    }

    public Customer(int idKhachHang, String hoTen) {
        IdKhachHang = idKhachHang;
        HoTen = hoTen;
    }

    public int getIdKhachHang() {
        return IdKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        IdKhachHang = idKhachHang;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    public String getHKTT() {
        return HKTT;
    }

    public void setHKTT(String HKTT) {
        this.HKTT = HKTT;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(String soCMND) {
        SoCMND = soCMND;
    }

    public Customer() {
    }
}
