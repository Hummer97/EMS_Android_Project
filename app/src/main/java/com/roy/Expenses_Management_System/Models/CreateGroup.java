package com.roy.Expenses_Management_System.Models;

import com.google.firebase.database.ServerValue;

public class CreateGroup {

    private String group_Name;
    private String group_size;
    private String group_Generated_Date;
    private String group_key;
    private Object timeStamp;


    public CreateGroup(String group_Name, String group_size, String group_Generated_Date) {
        this.group_Name = group_Name;
        this.group_size = group_size;
        this.group_Generated_Date = group_Generated_Date;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public CreateGroup() {
    }

    public String getGroup_Name() {
        return group_Name;
    }

    public String getGroup_size() {
        return group_size;
    }

    public String getGroup_Generated_Date() {
        return group_Generated_Date;
    }

    public String getGroup_key() {
        return group_key;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setGroup_Name(String group_Name) {
        this.group_Name = group_Name;
    }

    public void setGroup_size(String group_size) {
        this.group_size = group_size;
    }

    public void setGroup_Generated_Date(String group_Generated_Date) {
        this.group_Generated_Date = group_Generated_Date;
    }

    public void setGroup_key(String group_key) {
        this.group_key = group_key;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}


