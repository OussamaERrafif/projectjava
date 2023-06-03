package com.pharmacy;

public class Customer extends Users{
    private String prescription;

    Customer(String fullName, String email, char sex,String phoneNumber,String prescription){
        super(fullName,email,sex,phoneNumber);
        this.prescription = prescription;
    }
    public Customer(){

    }
    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

}
