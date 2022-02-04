package com.example.grocery.models;

import android.widget.ImageView;

public class RecommendedModel {
    String rec_img;
    String rec_name;
    String rec_dec;
    String rec_rating;
    int price;

    public RecommendedModel() {
    }

    public RecommendedModel(String rec_img, String rec_name, String rec_dec, String rec_rating,int price) {
        this.rec_img = rec_img;
        this.rec_name = rec_name;
        this.rec_dec = rec_dec;
        this.rec_rating = rec_rating;
        this.price=price;
    }

    public String getRec_img() {
        return rec_img;
    }

    public void setRec_img(String rec_img) {
        this.rec_img = rec_img;
    }

    public String getRec_name() {
        return rec_name;
    }

    public void setRec_name(String rec_name) {
        this.rec_name = rec_name;
    }

    public String getRec_dec() {
        return rec_dec;
    }

    public void setRec_dec(String rec_dec) {
        this.rec_dec = rec_dec;
    }

    public String getRec_rating() {
        return rec_rating;
    }

    public void setRec_rating(String rec_rating) {
        this.rec_rating = rec_rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
