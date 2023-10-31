package com.bill24.onlinepaymentsdk.model.appearance.lightMode;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.bill24.onlinepaymentsdk.model.appearance.ButtonModel;
import com.bill24.onlinepaymentsdk.model.appearance.PrimaryColorModel;
import com.bill24.onlinepaymentsdk.model.appearance.SecondaryColorModel;
import com.google.gson.annotations.SerializedName;

public class LightModeModel implements Parcelable{

    public LightModeModel(Parcel in) {
        primaryColor = in.readParcelable(PrimaryColorModel.class.getClassLoader());
        indicatorColor = in.readString();
        secondaryColor = in.readParcelable(SecondaryColorModel.class.getClassLoader());
        labelBackgroundColor = in.readString();
        button = in.readParcelable(ButtonModel.class.getClassLoader());
    }

    public static final Creator<LightModeModel> CREATOR = new Creator<LightModeModel>() {
        @Override
        public LightModeModel createFromParcel(Parcel in) {
            return new LightModeModel(in);
        }

        @Override
        public LightModeModel[] newArray(int size) {
            return new LightModeModel[size];
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

    @SerializedName("primary_color")
    private PrimaryColorModel primaryColor;

    public String getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(String indicatorColor) {
        this.indicatorColor = indicatorColor;
    }
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
