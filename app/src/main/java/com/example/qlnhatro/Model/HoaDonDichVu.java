package com.example.qlnhatro.Model;

public class HoaDonDichVu {
    private int IdHoaDon,IdPhong,IdKhachHang,TienThanhToan;
    private String TenPhong,HoTen;
    private boolean TrangThaiThanhToan;

    public HoaDonDichVu(int idPhong, int idKhachHang, int tienThanhToan, String tenPhong, String hoTen, boolean trangThaiThanhToan) {
        IdPhong = idPhong;
        IdKhachHang = idKhachHang;
        TienThanhToan = tienThanhToan;
        TenPhong = tenPhong;
        HoTen = hoTen;
        TrangThaiThanhToan = trangThaiThanhToan;
    }

    public int getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        IdHoaDon = idHoaDon;
    }

    public int getIdPhong() {
        return IdPhong;
    }

    public void setIdPhong(int idPhong) {
        IdPhong = idPhong;
    }

    public int getIdKhachHang() {
        return IdKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        IdKhachHang = idKhachHang;
    }

    public int getTienThanhToan() {
        return TienThanhToan;
    }

    public void setTienThanhToan(int tienThanhToan) {
        TienThanhToan = tienThanhToan;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public boolean isTrangThaiThanhToan() {
        return TrangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(boolean trangThaiThanhToan) {
        TrangThaiThanhToan = trangThaiThanhToan;
    }
}

