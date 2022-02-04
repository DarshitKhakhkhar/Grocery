package com.example.grocery.models;

public class CategoryModel {
    String name;
    String description;
    String discount;
    String img_uri;
    String type;

    public CategoryModel() {
    }

    public CategoryModel(String name, String description, String discount, String img_uri, String type) {
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.img_uri = img_uri;
        this.type = type;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(String img_uri) {
        this.img_uri = img_uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

