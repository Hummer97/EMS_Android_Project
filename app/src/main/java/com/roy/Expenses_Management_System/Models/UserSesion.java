package com.roy.Expenses_Management_System.Models;

public class UserSesion {
    private String userid;



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public UserSesion(String userid) {
        this.userid = userid;
    }
}
