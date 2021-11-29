package com.example.qlnhatro.Model;

import java.io.Serializable;

public class Accounts implements Serializable {

    private int IdTaiKhoan ;
    private String HoTen;
    private String Sdt;
    private String DiaChi;
    private String Username;
    private String Password;

    public Accounts(String hoTen, String sdt, String diaChi, String username, String password) {
        HoTen = hoTen;
        Sdt = sdt;
        DiaChi = diaChi;
        Username = username;
        Password = password;
    }

    public int getIdTaiKhoan() {
        return IdTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        IdTaiKhoan = idTaiKhoan;
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

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "IdTaiKhoan=" + IdTaiKhoan +
                ", HoTen='" + HoTen + '\'' +
                ", Sdt='" + Sdt + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
