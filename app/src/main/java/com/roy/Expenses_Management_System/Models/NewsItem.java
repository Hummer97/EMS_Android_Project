package com.roy.Expenses_Management_System.Models;

public class NewsItem {
    String Title,Context,Date;
    int userPhoto;


    public NewsItem(String title, String context, String date, int userPhoto) {
        Title = title;
        Context = context;
        Date = date;
        this.userPhoto = userPhoto;
    }

    public String getTitle() {
        return Title;
    }

    public String getContext() {
        return Context;
    }

    public String getDate() {
        return Date;
    }

    public int getUserPhoto() {
        return userPhoto;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setContext(String context) {
        Context = context;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setUserPhoto(int userPhoto) {
        this.userPhoto = userPhoto;
    }
}
