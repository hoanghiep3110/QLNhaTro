package com.example.qlnhatro.Model;

import java.util.ArrayList;

public class ListRoom {
    private ArrayList<Room> rooms;


    public ListRoom(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
