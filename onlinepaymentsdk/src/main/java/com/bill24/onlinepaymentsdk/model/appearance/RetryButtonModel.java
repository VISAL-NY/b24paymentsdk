package com.bill24.onlinepaymentsdk.model.appearance;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class RetryButtonModel  implements Parcelable{


    public RetryButtonModel(Parcel in) {
        backgroundColor = in.readString();
        textColor = in.readString();
    }

    public static final Creator<RetryButtonModel> CREATOR = new Creator<RetryButtonModel>() {
        @Override
        public RetryButtonModel createFromParcel(Parcel in) {
            return new RetryButtonModel(in);
        }

        @Override
        public RetryButtonModel[] newArray(int size) {
            return new RetryButtonModel[size];
        }
    };

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
    @SerializedName("background_color")
    private String backgroundColor;
    @SerializedName("text_color")
    private String textColor;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(backgroundColor);
        parcel.writeString(textColor);
    }
}
