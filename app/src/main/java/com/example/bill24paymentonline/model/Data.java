package com.example.bill24paymentonline.model;

import com.google.gson.annotations.SerializedName;

public class Data {
    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public String getKhqrString() {
        return khqrString;
    }

    public void setKhqrString(String khqrString) {
        this.khqrString = khqrString;
    }

    public String getTranID() {
        return tranID;
    }

    public void setTranID(String tranID) {
        this.tranID = tranID;
    }

    @SerializedName("identity_code")
    private String identityCode;
    @SerializedName("payment_link")
    private String paymentLink;
    @SerializedName("khqr_string")
    private String khqrString;
    @SerializedName("tran_id")
    private String tranID;
}
