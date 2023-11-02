package com.bill24.onlinepaymentsdk.model.appearance;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LabelModel implements Parcelable {
    protected LabelModel(Parcel in) {
        textColor = in.readString();
        backgroundColor = in.readString();
    }

    public static final Creator<LabelModel> CREATOR = new Creator<LabelModel>() {
        @Override
        public LabelModel createFromParcel(Parcel in) {
            return new LabelModel(in);
        }

        @Override
        public LabelModel[] newArray(int size) {
            return new LabelModel[size];
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
