package com.example.qlnhatro.Model;

public class Room {
    private int IdPhong;
    private String TenPhong;

    public Room(String tenPhong) {
        TenPhong = tenPhong;
    }

    private boolean TrangThai;

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

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public Room(int idPhong, String tenPhong, boolean trangThai) {
        IdPhong = idPhong;
        TenPhong = tenPhong;
        TrangThai = trangThai;
    }

    public Room() {
    }
}
