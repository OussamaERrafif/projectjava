package com.pharmacy;

public class Employee extends Users{
    private double salary;
    private String address;

    public Employee (){

    }
    public  Employee(String userName,String fullName,String email,char sex,String password,double salary,String phoneNumber,String address){
        super(userName,fullName,email,sex,password,phoneNumber);
        this.salary = salary;
        this.address = address;
    }

    public double getSalary(){
        return salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
}
