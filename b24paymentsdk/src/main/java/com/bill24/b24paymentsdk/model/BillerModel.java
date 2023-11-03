package com.bill24.b24paymentsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BillerModel implements Parcelable {

    public BillerModel(){

    }
    protected BillerModel(Parcel in) {
        billerCode = in.readString();
        billerName = in.readString();
        billerNameKh = in.readString();
        billerDisplayName = in.readString();
        billerLogo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(billerCode);
        dest.writeString(billerName);
        dest.writeString(billerNameKh);
        dest.writeString(billerDisplayName);
        dest.writeString(billerLogo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BillerModel> CREATOR = new Creator<BillerModel>() {
        @Override
        public BillerModel createFromParcel(Parcel in) {
            return new BillerModel(in);
        }

        @Override
        public BillerModel[] newArray(int size) {
            return new BillerModel[size];
        }
    };

    public String getBillerCode() {
        return billerCode;
    }

    public void setBillerCode(String billerCode) {
        this.billerCode = billerCode;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getBillerNameKh() {
        return billerNameKh;
    }

    public void setBillerNameKh(String billerNameKh) {
        this.billerNameKh = billerNameKh;
    }

    public String getBillerDisplayName() {
        return billerDisplayName;
    }

    public void setBillerDisplayName(String billerDisplayName) {
        this.billerDisplayName = billerDisplayName;
    }

    public String getBillerLogo() {
        return billerLogo;
    }

    public void setBillerLogo(String billerLogo) {
        this.billerLogo = billerLogo;
    }

    @SerializedName("biller_code")
    private String billerCode;
    @SerializedName("biller_name")
    private String billerName;
    @SerializedName("biller_name_kh")
    private String billerNameKh;
    @SerializedName("biller_display_name")
    private  String  billerDisplayName;
    @SerializedName("biller_logo")
    private  String billerLogo;
}
