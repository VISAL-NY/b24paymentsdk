package com.bill24.b24paymentsdk.model.appearance;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class FavoriteButtonModel implements Parcelable{

    protected FavoriteButtonModel(Parcel in) {
        textColor = in.readString();
        backgroundColor = in.readString();
    }

    public static final Creator<FavoriteButtonModel> CREATOR = new Creator<FavoriteButtonModel>() {
        @Override
        public FavoriteButtonModel createFromParcel(Parcel in) {
            return new FavoriteButtonModel(in);
        }

        @Override
        public FavoriteButtonModel[] newArray(int size) {
            return new FavoriteButtonModel[size];
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
