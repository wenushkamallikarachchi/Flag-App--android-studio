package com.example.cwflag;

public class CountryItem {
    String name;
    int image;

    public CountryItem(String name,Integer image){
        this.name=name;
        this.image=image;

    }
//getter and setter for name and image

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }
}
