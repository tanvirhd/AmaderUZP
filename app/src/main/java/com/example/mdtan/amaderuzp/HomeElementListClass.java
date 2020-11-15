package com.example.mdtan.amaderuzp;

public class HomeElementListClass {

    private int photo;
    private String title;


    public HomeElementListClass(int man1, int close){

    }

    public HomeElementListClass(int photo, String title) {
        this.photo = photo;
        this.title = title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
