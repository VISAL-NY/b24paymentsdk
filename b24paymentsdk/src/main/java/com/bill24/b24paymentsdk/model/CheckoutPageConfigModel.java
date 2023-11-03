package com.bill24.b24paymentsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.bill24.b24paymentsdk.model.appearance.AppearanceModel;
import com.google.gson.annotations.SerializedName;

public class CheckoutPageConfigModel implements Parcelable{


    public CheckoutPageConfigModel(){}

    protected CheckoutPageConfigModel(Parcel in) {
        logo = in.readString();
        backgroundImage = in.readString();
        displayBill24Info = in.readByte() != 0;
        displaySuccessPage = in.readByte() != 0;
        appearance = in.readParcelable(AppearanceModel.class.getClassLoader());
    }

    public static final Creator<CheckoutPageConfigModel> CREATOR = new Creator<CheckoutPageConfigModel>() {
        @Override
        public CheckoutPageConfigModel createFromParcel(Parcel in) {
            return new CheckoutPageConfigModel(in);
        }

        @Override
        public CheckoutPageConfigModel[] newArray(int size) {
            return new CheckoutPageConfigModel[size];
        }
    };

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public boolean isDisplayBill24Info() {
        return displayBill24Info;
    }

    public void setDisplayBill24Info(boolean displayBill24Info) {
        this.displayBill24Info = displayBill24Info;
    }

    public FaviIconModel getFavicon() {
        return favicon;
    }

    public void setFavicon(FaviIconModel favicon) {
        this.favicon = favicon;
    }
    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }
    public AppearanceModel getAppearance() {
        return appearance;
    }

    public void setAppearance(AppearanceModel appearance) {
        this.appearance = appearance;
    }
    public boolean isDisplaySuccessPage() {
        return displaySuccessPage;
    }

    public void setDisplaySuccessPage(boolean displaySuccessPage) {
        this.displaySuccessPage = displaySuccessPage;
    }

    @SerializedName("logo")
    private String logo;
    @SerializedName("background_image")
    private  String backgroundImage;
    @SerializedName("display_bill24_info")
    private  boolean displayBill24Info;
    @SerializedName("display_success_page")
    private boolean displaySuccessPage;
    @SerializedName("favicon")
    private FaviIconModel favicon;
    @SerializedName("setting")
    private Setting setting;
    @SerializedName("appearance")
    private AppearanceModel appearance;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(logo);
        parcel.writeString(backgroundImage);
        parcel.writeByte((byte) (displayBill24Info ? 1 : 0));
        parcel.writeByte((byte) (displaySuccessPage ? 1 : 0));
        parcel.writeParcelable(appearance, i);
    }
}
