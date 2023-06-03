package com.pharmacy;

import java.util.Date;

public class Transaction {
    private int id;
    private String drugName;
    private String manuName;
    private double price =0;
    private int quantity;
    private Date dateD;
    private String date;

    public Transaction(){
        dateD = new Date();
        date = dateD.toString();
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getDate(){
        return date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }
    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
    public String getManuName() {
        return manuName;
    }
    public void setManuName(String manuName) {
        this.manuName = manuName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
