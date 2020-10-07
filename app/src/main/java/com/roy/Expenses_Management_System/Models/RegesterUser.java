package com.roy.Expenses_Management_System.Models;

import com.google.firebase.database.ServerValue;

import java.sql.Timestamp;

public class RegesterUser {

    private String User_name,Mobile_No,User_key,Group_ID,Group_Name;
    private Object timeStamp;

    public RegesterUser(String user_name, String mobile_No,  String group_ID, String group_Name) {
        User_name = user_name;
        Mobile_No = mobile_No;
        Group_ID = group_ID;
        Group_Name = group_Name;
        timeStamp = ServerValue.TIMESTAMP;
    }

    public String getUser_name() {
        return User_name;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public String getGroup_Name() {
        return Group_Name;
    }

    public void setGroup_Name(String group_Name) {
        Group_Name = group_Name;
    }

    public String getUser_key() {
        return User_key;
    }

    public String getGroup_ID() {
        return Group_ID;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }


    public void setUser_key(String user_key) {
        User_key = user_key;
    }

    public void setGroup_ID(String group_ID) {
        Group_ID = group_ID;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
