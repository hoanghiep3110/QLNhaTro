package com.example.qlnhatro.Model;

public class Contact {
    private int IdThue,IdKhachHang,IdPhong,TienDatCoc;
    private String NgayBatDau,NgayKetThuc;
    private String HoTen,TenPhong ,FileHopDong, LinkDownLoad;

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

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public String getFileHopDong() {
        return FileHopDong;
    }

    public void setFielHopDong(String fielHopDong) {
        FileHopDong = fielHopDong;
    }

    public String getLinkDownLoad() {
        return LinkDownLoad;
    }

    public void setLinkDownLoad(String linkDownLoad) {
        LinkDownLoad = linkDownLoad;
    }
}
