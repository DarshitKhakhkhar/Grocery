package com.example.grocery.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String name;
    String description;
    String rating;
    String type;
    String img_uri;

    int price;

    public ViewAllModel() {
    }

    public ViewAllModel(String name, String description, String rating, String type, String img_uri, int price) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.type = type;
        this.img_uri = img_uri;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(String img_uri) {
        this.img_uri = img_uri;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
