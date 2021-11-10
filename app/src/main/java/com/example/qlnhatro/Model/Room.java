package com.example.qlnhatro.Model;

public class Room {
    private int idPhong;
    private String nameRoom;
    private boolean status;

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public Room(int idPhong, String nameRoom) {
        this.idPhong = idPhong;
        this.nameRoom = nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Room(int idPhong, String nameRoom, boolean status) {
        this.idPhong = idPhong;
        this.nameRoom = nameRoom;
        this.status = status;
    }

    public Room() {
    }
}
