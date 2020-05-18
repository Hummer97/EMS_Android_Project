package com.roy.Expenses_Management_System.Models;

public class IntroScreenItem {
    String Title,Description;
    int Intro_img;

    public IntroScreenItem(String title, String description, int intro_img) {
        Title = title;
        Description = description;
        Intro_img = intro_img;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setIntro_img(int intro_img) {
        Intro_img = intro_img;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getIntro_img() {
        return Intro_img;
    }
}
