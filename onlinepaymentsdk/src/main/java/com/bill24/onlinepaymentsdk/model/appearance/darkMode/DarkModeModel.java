package com.bill24.onlinepaymentsdk.model.appearance.darkMode;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.bill24.onlinepaymentsdk.model.appearance.ButtonModel;
import com.bill24.onlinepaymentsdk.model.appearance.PrimaryColorModel;
import com.bill24.onlinepaymentsdk.model.appearance.SecondaryColorModel;
import com.google.gson.annotations.SerializedName;

public class DarkModeModel implements Parcelable{


    public DarkModeModel(Parcel in) {
        primaryColor = in.readParcelable(PrimaryColorModel.class.getClassLoader());
        indicatorColor = in.readString();
        secondaryColor = in.readParcelable(SecondaryColorModel.class.getClassLoader());
        labelBackgroundColor = in.readString();
        button = in.readParcelable(ButtonModel.class.getClassLoader());
    }

    public static final Creator<DarkModeModel> CREATOR = new Creator<DarkModeModel>() {
        @Override
        public DarkModeModel createFromParcel(Parcel in) {
            return new DarkModeModel(in);
        }

        @Override
        public DarkModeModel[] newArray(int size) {
            return new DarkModeModel[size];
        }
    };

    public PrimaryColorModel getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(PrimaryColorModel primaryColor) {
        this.primaryColor = primaryColor;
    }

    public SecondaryColorModel getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(SecondaryColorModel secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public ButtonModel getButton() {
        return button;
    }

    public void setButton(ButtonModel button) {
        this.button = button;
    }
    public String getLabelBackgroundColor() {
        return labelBackgroundColor;
    }

    public void setLabelBackgroundColor(String labelBackgroundColor) {
        this.labelBackgroundColor = labelBackgroundColor;
    }
    public String getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(String indicatorColor) {
        this.indicatorColor = indicatorColor;
    }

    @SerializedName("primary_color")
    private PrimaryColorModel primaryColor;
    @SerializedName("indicator_color")
    private String indicatorColor;
    @SerializedName("secondary_color")
    private SecondaryColorModel secondaryColor;
    @SerializedName("label_background_color")
    private String labelBackgroundColor;
    @SerializedName("button")
    private ButtonModel button;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(primaryColor, i);
        parcel.writeString(indicatorColor);
        parcel.writeParcelable(secondaryColor, i);
        parcel.writeString(labelBackgroundColor);
        parcel.writeParcelable(button, i);
    }
}
