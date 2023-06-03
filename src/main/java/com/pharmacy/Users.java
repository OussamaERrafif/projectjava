package com.pharmacy;

abstract public class Users {
    protected String userName;
    protected String fullName;
    protected String email;
    protected char sex;
    protected String password;
    protected String phoneNumber;


    Users(String userName, String fullName, String email, char sex, String password,String phoneNumber){
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.sex = sex;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    Users(String fullName, String email, char sex,String phoneNumber){
        this.fullName = fullName;
        this.email = email;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }
    Users(String userName,String fullName, String email, char sex,String phoneNumber){
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }
    Users(){

    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public char getSex(){
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
