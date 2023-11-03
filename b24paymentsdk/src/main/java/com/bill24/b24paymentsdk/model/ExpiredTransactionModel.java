package com.bill24.b24paymentsdk.model;

import com.google.gson.annotations.SerializedName;

public class ExpiredTransactionModel {
    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    @SerializedName("expired_date")
    String expiredDate;
}
