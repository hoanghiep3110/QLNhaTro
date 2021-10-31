package com.example.qlnhatro.House;

public class ChiTietHoaDon {
    private int idHoaDon,idService,chiSoCu,chiSoMoi,thanhTien;
    private String date1,date2;

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getChiSoCu() {
        return chiSoCu;
    }

    public void setChiSoCu(int chiSoCu) {
        this.chiSoCu = chiSoCu;
    }

    public int getChiSoMoi() {
        return chiSoMoi;
    }

    public void setChiSoMoi(int chiSoMoi) {
        this.chiSoMoi = chiSoMoi;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public ChiTietHoaDon(int idHoaDon, int idService, int chiSoCu, int chiSoMoi, int thanhTien, String date1, String date2) {
        this.idHoaDon = idHoaDon;
        this.idService = idService;
        this.chiSoCu = chiSoCu;
        this.chiSoMoi = chiSoMoi;
        this.thanhTien = thanhTien;
        this.date1 = date1;
        this.date2 = date2;
    }

    public ChiTietHoaDon() {
    }
}
