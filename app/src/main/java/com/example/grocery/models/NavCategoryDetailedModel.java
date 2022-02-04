package com.example.grocery.models;

public class NavCategoryDetailedModel {
    String name;
    String type;
    String img_uri;
    String price;

    public NavCategoryDetailedModel() {
    }

    public NavCategoryDetailedModel(String name, String type, String img_uri, String price) {
        this.name = name;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
