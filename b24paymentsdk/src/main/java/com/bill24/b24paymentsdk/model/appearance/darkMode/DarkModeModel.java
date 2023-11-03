package com.bill24.b24paymentsdk.model.appearance.darkMode;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.bill24.b24paymentsdk.model.appearance.AlertModel;
import com.bill24.b24paymentsdk.model.appearance.ButtonModel;
import com.bill24.b24paymentsdk.model.appearance.LabelModel;
import com.bill24.b24paymentsdk.model.appearance.PrimaryColorModel;
import com.bill24.b24paymentsdk.model.appearance.SecondaryColorModel;
import com.google.gson.annotations.SerializedName;

public class DarkModeModel implements Parcelable{


    protected DarkModeModel(Parcel in) {
        primaryColor = in.readParcelable(PrimaryColorModel.class.getClassLoader());
        indicatorColor = in.readString();
        secondaryColor = in.readParcelable(SecondaryColorModel.class.getClassLoader());
        label = in.readParcelable(LabelModel.class.getClassLoader());
        alert = in.readParcelable(AlertModel.class.getClassLoader());
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
    public String getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(String indicatorColor) {
        this.indicatorColor = indicatorColor;
    }

    public LabelModel getLabel() {
        return label;
    }

    public void setLabel(LabelModel label) {
        this.label = label;
    }

    public AlertModel getAlert() {
        return alert;
    }

    public void setAlert(AlertModel alert) {
        this.alert = alert;
    }
    @SerializedName("primary_color")
    private PrimaryColorModel primaryColor;
    @SerializedName("indicator_color")
    private String indicatorColor;
    @SerializedName("secondary_color")
    private SecondaryColorModel secondaryColor;

    @SerializedName("label")
    private LabelModel label;
    @SerializedName("alert")
    private AlertModel alert;
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
        parcel.writeParcelable(label, i);
        parcel.writeParcelable(alert, i);
        parcel.writeParcelable(button, i);
    }
}
