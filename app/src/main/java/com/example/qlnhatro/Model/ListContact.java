package com.example.qlnhatro.Model;

import java.util.ArrayList;

public class ListContact {

    private ArrayList<Contact> contacts;

    public ListContact(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
