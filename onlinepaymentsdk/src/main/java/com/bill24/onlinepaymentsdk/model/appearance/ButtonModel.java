package com.bill24.onlinepaymentsdk.model.appearance;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ButtonModel implements Parcelable{


    protected ButtonModel(Parcel in) {
        favoriteButton = in.readParcelable(FavoriteButtonModel.class.getClassLoader());
        bankButton = in.readParcelable(BankButtonModel.class.getClassLoader());
        actionButton = in.readParcelable(ActionButtonModel.class.getClassLoader());
        textColor = in.readString();
        backgroundColor = in.readString();
    }

    public static final Creator<ButtonModel> CREATOR = new Creator<ButtonModel>() {
        @Override
        public ButtonModel createFromParcel(Parcel in) {
            return new ButtonModel(in);
        }

        @Override
        public ButtonModel[] newArray(int size) {
            return new ButtonModel[size];
        }
    };

    public FavoriteButtonModel getFavoriteButton() {
        return favoriteButton;
    }

    public void setFavoriteButton(FavoriteButtonModel favoriteButton) {
        this.favoriteButton = favoriteButton;
    }

    public BankButtonModel getBankButton() {
        return bankButton;
    }

    public void setBankButton(BankButtonModel bankButton) {
        this.bankButton = bankButton;
    }

    public ActionButtonModel getActionButton() {
        return actionButton;
    }

    public void setActionButton(ActionButtonModel actionButton) {
        this.actionButton = actionButton;
    }

    @SerializedName("favorite_button")
    private FavoriteButtonModel favoriteButton;
    @SerializedName("bank_button")
    private BankButtonModel bankButton;
    @SerializedName("action_button")
    private ActionButtonModel actionButton;

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
    private  String textColor;
    @SerializedName("backgrond_color")
    private String backgroundColor;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(favoriteButton, i);
        parcel.writeParcelable(bankButton, i);
        parcel.writeParcelable(actionButton, i);
        parcel.writeString(textColor);
        parcel.writeString(backgroundColor);
    }
}
