package com.example.qlnhatro.Model;

public class Customer {
    private int idCustomer;
    private String name,phone,gt,que,hktt;
    private char cmnn;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getHktt() {
        return hktt;
    }

    public void setHktt(String hktt) {
        this.hktt = hktt;
    }

    public char getCmnn() {
        return cmnn;
    }

    public void setCmnn(char cmnn) {
        this.cmnn = cmnn;
    }

    public Customer(int idCustomer, String name, String phone, String gt, String que, String hktt, char cmnn) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phone = phone;
        this.gt = gt;
        this.que = que;
        this.hktt = hktt;
        this.cmnn = cmnn;
    }

    public Customer() {
    }
}
