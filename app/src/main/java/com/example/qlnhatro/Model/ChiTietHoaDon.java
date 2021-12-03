package com.example.qlnhatro.Model;

public class ChiTietHoaDon {
    private int IdHoaDon,IdDichVu,ChiSoCu,ChiSoMoi,ThanhTien;
    private String HoTen,TuNgay,ToiNgay,TenDichVu;

    public int getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        IdHoaDon = idHoaDon;
    }

    public int getIdDichVu() {
        return IdDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        IdDichVu = idDichVu;
    }

    public int getChiSoCu() {
        return ChiSoCu;
    }

    public void setChiSoCu(int chiSoCu) {
        ChiSoCu = chiSoCu;
    }

    public int getChiSoMoi() {
        return ChiSoMoi;
    }

    public void setChiSoMoi(int chiSoMoi) {
        ChiSoMoi = chiSoMoi;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int thanhTien) {
        ThanhTien = thanhTien;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getTuNgay() {
        return TuNgay;
    }

    public void setTuNgay(String tuNgay) {
        TuNgay = tuNgay;
    }

    public String getToiNgay() {
        return ToiNgay;
    }

    public void setToiNgay(String toiNgay) {
        ToiNgay = toiNgay;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        TenDichVu = tenDichVu;
    }

    public ChiTietHoaDon(int idDichVu, String tuNgay, String toiNgay, int chiSoCu, int chiSoMoi) {
        IdDichVu = idDichVu;
        ChiSoCu = chiSoCu;
        ChiSoMoi = chiSoMoi;
        TuNgay = tuNgay;
        ToiNgay = toiNgay;
    }
}
