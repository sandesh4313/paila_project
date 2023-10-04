package com.paila.ecommerce.dto;

import com.google.gson.annotations.SerializedName;

public class CreatePayment {
    @SerializedName("items")
    Object[] items;

    @SerializedName("total")
    private double total;

    public Object[] getItems() {
        return items;
    }

//    public double getTotal() {
//        return total;
//    }
}


