package com.example.qlnhatro.Model;

public class Room {
    private int IdPhong;
    private String TenPhong;

    public Room(String tenPhong) {
        TenPhong = tenPhong;
    }

    public Room(String tenPhong, int trangThai) {
        TenPhong = tenPhong;
        TrangThai = trangThai;
    }

    public Room(int idPhong, String tenPhong, int trangThai) {
        IdPhong = idPhong;
        TenPhong = tenPhong;
        TrangThai = trangThai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    private int TrangThai;

    public int getIdPhong() {
        return IdPhong;
    }

    public void setIdPhong(int idPhong) {
        IdPhong = idPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public Room() {
    }
}
