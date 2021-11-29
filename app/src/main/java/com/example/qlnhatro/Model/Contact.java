package com.example.qlnhatro.Model;

import java.util.Date;

public class Contact {
    private int IdThue,IdKhachHang,IdPhong,TienDatCoc;
    private Date NgayBatDau,NgayKetThuc;
    private String FielHopDong;

    public int getIdThue() {
        return IdThue;
    }

    public void setIdThue(int idThue) {
        IdThue = idThue;
    }

    public int getIdKhachHang() {
        return IdKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        IdKhachHang = idKhachHang;
    }

    public int getIdPhong() {
        return IdPhong;
    }

    public void setIdPhong(int idPhong) {
        IdPhong = idPhong;
    }

    public int getTienDatCoc() {
        return TienDatCoc;
    }

    public void setTienDatCoc(int tienDatCoc) {
        TienDatCoc = tienDatCoc;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public String getFielHopDong() {
        return FielHopDong;
    }

    public void setFielHopDong(String fielHopDong) {
        FielHopDong = fielHopDong;
    }
}
