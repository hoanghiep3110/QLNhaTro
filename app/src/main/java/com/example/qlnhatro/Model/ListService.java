package com.example.qlnhatro.Model;

import java.util.ArrayList;

public class ListService {
    private ArrayList<Service> services;

    public ListService(ArrayList<Service> services) {
        this.services = services;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }
}
