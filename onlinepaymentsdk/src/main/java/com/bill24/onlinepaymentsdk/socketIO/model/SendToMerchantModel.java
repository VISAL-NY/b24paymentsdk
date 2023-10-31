package com.bill24.onlinepaymentsdk.socketIO.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class SendToMerchantModel implements Parcelable {


    public  SendToMerchantModel(){

    }
    public SendToMerchantModel(Parcel in) {
        tranNo = in.readString();
        tranAmount = in.readDouble();
        totalAmount = in.readDouble();
        currency = in.readString();
        merchantName = in.readString();
        tranDate = in.readString();
        bankRef = in.readString();
    }

    public static final Creator<SendToMerchantModel> CREATOR = new Creator<SendToMerchantModel>() {
        @Override
        public SendToMerchantModel createFromParcel(Parcel in) {
            return new SendToMerchantModel(in);
        }

        @Override
        public SendToMerchantModel[] newArray(int size) {
            return new SendToMerchantModel[size];
        }
    };

    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public double getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(double tranAmount) {
        this.tranAmount = tranAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getBankRef() {
        return bankRef;
    }

    public void setBankRef(String bankRef) {
        this.bankRef = bankRef;
    }

    @SerializedName("transaction_no")
    private String tranNo;
    @SerializedName("transaction_amount")
    private double tranAmount;
    @SerializedName("total_amount")
    private double totalAmount;
    @SerializedName("currency")
    private String currency;
    @SerializedName("merchant_name")
    private String merchantName;
    @SerializedName("transaction_date")
    private String tranDate;
    @SerializedName("bank_ref")
    private String bankRef;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(tranNo);
        parcel.writeDouble(tranAmount);
        parcel.writeDouble(totalAmount);
        parcel.writeString(currency);
        parcel.writeString(merchantName);
        parcel.writeString(tranDate);
        parcel.writeString(bankRef);
    }
}
