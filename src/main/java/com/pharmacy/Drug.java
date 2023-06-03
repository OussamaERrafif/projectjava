package com.pharmacy;

public class Drug {
    private int id;
    private String name;
    private String manufacturer;
    private String expireDate;
    private String description;
    private int amount;
    private double price;
    public static int count;

    public Drug(){
        id = count;
        count++;
    }

    public Drug(String name,String manufacturer,String expireDate, String description, int amount, double price){
        this.name = name;
        this.manufacturer = manufacturer;
        this.expireDate = expireDate;
        this.description = description;
        this.amount = amount;
        this.price = price;
        count++;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getExpireDate(){
        return expireDate;
    }

    public void setExpireDate(String expireDate){
        this.expireDate = expireDate;
    }

}