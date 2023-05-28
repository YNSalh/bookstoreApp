package com.example.mybookstoreapp;

public class MyUser {
    public  String username,password,gender,dop,phone;
    public  MyUser(){

    }
    public  MyUser(String username,String password, String gender,String dop,String phone){
        this.username=username;
        this.gender=gender;
        this.dop=dop;
        this.phone=phone;
        this.password=password;


    }
    public  String getUsername(){return  username;}
    public  void  setUsername(String username){this.username=username;}
    public  String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
    public  String getGender(){return gender;}
    public void setGender(String gender){this.gender=gender;}
    public  String getDop(){return dop;}
    public void setDop(String dop){this.dop=dop;}
    public  String getPhone(){return phone;}
    public void setPhone(String phone){this.phone=phone;}


}
