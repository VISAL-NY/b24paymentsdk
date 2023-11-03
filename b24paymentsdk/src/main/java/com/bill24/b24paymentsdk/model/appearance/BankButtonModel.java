package com.bill24.b24paymentsdk.model.appearance;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class BankButtonModel implements Parcelable{


    protected BankButtonModel(Parcel in) {
        backgroundColor = in.readString();
        textPrimary = in.readString();
        textSecondary = in.readString();
    }

    public static final Creator<BankButtonModel> CREATOR = new Creator<BankButtonModel>() {
        @Override
        public BankButtonModel createFromParcel(Parcel in) {
            return new BankButtonModel(in);
        }

        @Override
        public BankButtonModel[] newArray(int size) {
            return new BankButtonModel[size];
        }
    };

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextPrimary() {
        return textPrimary;
    }

    public void setTextPrimary(String textPrimary) {
        this.textPrimary = textPrimary;
    }

    public String getTextSecondary() {
        return textSecondary;
    }

    public void setTextSecondary(String textSecondary) {
        this.textSecondary = textSecondary;
    }


    @SerializedName("background_color")
    private String backgroundColor;
    @SerializedName("text_primary")
    private String textPrimary;
    @SerializedName("text_secondary")
    private String textSecondary;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(backgroundColor);
        parcel.writeString(textPrimary);
        parcel.writeString(textSecondary);
    }
}
