package com.example.qlnhatro.House;

public class Room {
    private int idPhong;
    private String nameRoom;
    private String status;

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Room(int idPhong, String nameRoom, String status) {
        this.idPhong = idPhong;
        this.nameRoom = nameRoom;
        this.status = status;
    }

    public Room() {
    }
}
