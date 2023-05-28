package com.example.mybookstoreapp;

public class MyProduct {
    public  String pid,pname,pquantity,price;


    public MyProduct() {
    }

    public MyProduct(String pid, String pname, String pquantity, String price) {
        this.pid = pid;
        this.pname = pname;
        this.pquantity = pquantity;
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPquantity() {
        return pquantity;
    }

    public void setPquantity(String pquantity) {
        this.pquantity = pquantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
