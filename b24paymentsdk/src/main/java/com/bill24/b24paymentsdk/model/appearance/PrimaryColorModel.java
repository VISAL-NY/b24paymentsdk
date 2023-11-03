package com.bill24.b24paymentsdk.model.appearance;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class PrimaryColorModel implements Parcelable{


    protected PrimaryColorModel(Parcel in) {
        textColor = in.readString();
        backgroundColor = in.readString();
    }

    public static final Creator<PrimaryColorModel> CREATOR = new Creator<PrimaryColorModel>() {
        @Override
        public PrimaryColorModel createFromParcel(Parcel in) {
            return new PrimaryColorModel(in);
        }

        @Override
        public PrimaryColorModel[] newArray(int size) {
            return new PrimaryColorModel[size];
        }
    };

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @SerializedName("text_color")
    private String textColor;
    @SerializedName("background_color")
    private String backgroundColor;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(textColor);
        parcel.writeString(backgroundColor);
    }
}
