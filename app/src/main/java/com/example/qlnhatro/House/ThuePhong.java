package com.example.qlnhatro.House;

public class ThuePhong {
    private int idThue,idRoom,idCustomer,tienDatCoc;
    private String dateBegin,dateFinish;

    public ThuePhong() {
    }

    public int getIdThue() {
        return idThue;
    }

    public void setIdThue(int idThue) {
        this.idThue = idThue;
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

    public int getTienDatCoc() {
        return tienDatCoc;
    }

    public void setTienDatCoc(int tienDatCoc) {
        this.tienDatCoc = tienDatCoc;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public ThuePhong(int idThue, int idRoom, int idCustomer, int tienDatCoc, String dateBegin, String dateFinish) {
        this.idThue = idThue;
        this.idRoom = idRoom;
        this.idCustomer = idCustomer;
        this.tienDatCoc = tienDatCoc;
        this.dateBegin = dateBegin;
        this.dateFinish = dateFinish;
    }
}
