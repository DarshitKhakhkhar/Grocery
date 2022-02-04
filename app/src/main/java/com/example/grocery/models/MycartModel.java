package com.example.grocery.models;

import android.net.wifi.p2p.WifiP2pManager;

import java.io.Serializable;

public class MycartModel implements Serializable {
    String ProductName;
    String ProductPrice;
    String CurrentDate;
    String CurrentTime;
    String TotalQuantity;
    String DocumentId;

    int TotalPrice;

    public MycartModel() {
    }

    public MycartModel(String productName, String productPrice, String currentDate,
                       String currentTime, String totalQuantity, int totalPrice) {
        ProductName = productName;
        ProductPrice = productPrice;
        CurrentDate = currentDate;
        CurrentTime = currentTime;
        TotalQuantity = totalQuantity;
        TotalPrice = totalPrice;
    }

    public String getDocumentId() {
        return DocumentId;
    }

    public void setDocumentId(String documentId) {
        this.DocumentId = documentId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }

    public String getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(String currentTime) {
        CurrentTime = currentTime;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }
}
