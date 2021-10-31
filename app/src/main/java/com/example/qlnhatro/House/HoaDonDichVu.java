package com.example.qlnhatro.House;

public class HoaDonDichVu {
    private int idHoaDon,idUser,idRoom,idCustomer,tienThanhToan;
    private String status;

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getTienThanhToan() {
        return tienThanhToan;
    }

    public void setTienThanhToan(int tienThanhToan) {
        this.tienThanhToan = tienThanhToan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HoaDonDichVu(int idHoaDon, int idUser, int idRoom, int idCustomer, int tienThanhToan, String status) {
        this.idHoaDon = idHoaDon;
        this.idUser = idUser;
        this.idRoom = idRoom;
        this.idCustomer = idCustomer;
        this.tienThanhToan = tienThanhToan;
        this.status = status;
    }

    public HoaDonDichVu() {
    }
}

