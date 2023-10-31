package com.bill24.onlinepaymentsdk.model.appearance;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.bill24.onlinepaymentsdk.model.appearance.darkMode.DarkModeModel;
import com.bill24.onlinepaymentsdk.model.appearance.lightMode.LightModeModel;
import com.google.gson.annotations.SerializedName;

public class AppearanceModel implements Parcelable {

    protected AppearanceModel(Parcel in) {
        lightMode = in.readParcelable(LightModeModel.class.getClassLoader());
        darkMode = in.readParcelable(DarkModeModel.class.getClassLoader());
    }

    public static final Creator<AppearanceModel> CREATOR = new Creator<AppearanceModel>() {
        @Override
        public AppearanceModel createFromParcel(Parcel in) {
            return new AppearanceModel(in);
        }

        @Override
        public AppearanceModel[] newArray(int size) {
            return new AppearanceModel[size];
        }
    };

    public LightModeModel getLightMode() {
        return lightMode;
    }

    public void setLightMode(LightModeModel lightMode) {
        this.lightMode = lightMode;
    }

    public DarkModeModel getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(DarkModeModel darkMode) {
        this.darkMode = darkMode;
    }

    @SerializedName("light_mode")
    private LightModeModel lightMode;
    @SerializedName("dark_mode")
    private DarkModeModel darkMode;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(lightMode, i);
        parcel.writeParcelable(darkMode, i);
    }
}
