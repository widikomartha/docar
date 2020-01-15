package com.example.docar;

public class MenuLayout {
    private String title;
    private int images;
    public MenuLayout(String title, int images){
        this.title=title;
        this.images=images;
    }
    public String getTitle(){
        return title;
    }
    public int getImages(){
        return images;
    }
}
