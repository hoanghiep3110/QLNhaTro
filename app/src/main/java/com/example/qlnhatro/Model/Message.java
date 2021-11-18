package com.example.qlnhatro.Model;

public class Message {
    private int Status;
    private String Notification;

    public Message(int status, String notification) {
        Status = status;
        Notification = notification;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getNotification() {
        return Notification;
    }

    public void setNotification(String notification) {
        Notification = notification;
    }
}
