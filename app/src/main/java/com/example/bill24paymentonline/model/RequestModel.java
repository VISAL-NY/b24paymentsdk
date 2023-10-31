package com.example.bill24paymentonline.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestModel {
    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getPurposeOfTransaction() {
        return purposeOfTransaction;
    }

    public void setPurposeOfTransaction(String purposeOfTransaction) {
        this.purposeOfTransaction = purposeOfTransaction;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCancelURL() {
        return cancelURL;
    }

    public void setCancelURL(String cancelURL) {
        this.cancelURL = cancelURL;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @SerializedName("identity_code")
    private String identityCode;
    @SerializedName("purpose_of_transaction")
    private String purposeOfTransaction;
    @SerializedName("device_code")
    private String deviceCode;
    @SerializedName("description")
    private String description;
    @SerializedName("currency")
    private String currency;
    @SerializedName("amount")
    private double amount;
    @SerializedName("language")
    private String language;
    @SerializedName("cancel_url")
    private String cancelURL;
    @SerializedName("redirect_url")
    private String redirectURL;
    @SerializedName("channel_code")
    private String channelCode;
    @SerializedName("user_ref")
    private String userRef;
    @SerializedName("customers")
    private List<Customer> customers;

}
