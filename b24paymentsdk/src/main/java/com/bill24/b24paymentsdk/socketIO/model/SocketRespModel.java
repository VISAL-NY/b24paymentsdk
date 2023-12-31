package com.bill24.b24paymentsdk.socketIO.model;

import com.google.gson.annotations.SerializedName;

public class SocketRespModel {

    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    @SerializedName("transactionId")
    private String tranNo;
}
