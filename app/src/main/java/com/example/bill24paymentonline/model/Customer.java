package com.example.bill24paymentonline.model;

import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("branch_code")
    private String branchCode;
    @SerializedName("branch_name")
    private String branchName;
    @SerializedName("customer_code")
    private String customerCode;
    @SerializedName("customer_name")
    private String customerName;
    @SerializedName("customer_name_latin")
    private String customerNameLatin;
    @SerializedName("bill_no")
    private String billNo;
    @SerializedName("amount")
    private double amount;
    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNameLatin() {
        return customerNameLatin;
    }

    public void setCustomerNameLatin(String customerNameLatin) {
        this.customerNameLatin = customerNameLatin;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
