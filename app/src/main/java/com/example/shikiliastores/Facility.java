package com.example.shikiliastores;

import android.graphics.drawable.Drawable;

import androidx.annotation.Keep;

import java.io.Serializable;


@Keep
public class Facility implements Serializable {
    public String name;
    public String phoneNo;
    public String location;
    public String capacity;
    public String price;
    public String category;
    public Drawable thumbnail;
    public String image;

    public Facility() {
    }

    public Facility(String name, String phoneNo, String location, String capacity, String price, String category, Drawable thumbnail) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.location = location;
        this.capacity = capacity;
        this.price = price;
        this.category = category;
        this.thumbnail = thumbnail;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Drawable getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Drawable thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
