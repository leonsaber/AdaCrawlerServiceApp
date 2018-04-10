package com.client.leonsaber.adacrawlerserviceapp.entity;

import com.google.gson.annotations.SerializedName;

public class ProductInfo {
    @SerializedName("id")
    public String productID;
    @SerializedName("productQty")
    public String productQty;
    @SerializedName("productPrice")
    public String productPrice;
    @SerializedName("productName")
    public String productName;
    @SerializedName("productStatus")
    public String productStatus;
    @SerializedName("productURL")
    public String productURL;
    public ProductInfo () {}
    public ProductInfo (
            String productID,
            String productQty,
            String productPrice,
            String productName,
            String productStatus,
            String productURL) {
        this.productID = productID;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productStatus = productStatus;
        this.productURL = productURL;
    }
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }



}
