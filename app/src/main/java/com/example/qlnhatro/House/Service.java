package com.example.qlnhatro.House;

public class Service {
    private int idService,donGia;
    private String namService;

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getNamService() {
        return namService;
    }

    public void setNamService(String namService) {
        this.namService = namService;
    }

    public Service(int idService, int donGia, String namService) {
        this.idService = idService;
        this.donGia = donGia;
        this.namService = namService;
    }

    public Service() {
    }
}
