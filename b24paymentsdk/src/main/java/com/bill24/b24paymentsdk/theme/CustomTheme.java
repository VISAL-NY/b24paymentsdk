package com.bill24.b24paymentsdk.theme;

import com.bill24.b24paymentsdk.helper.ConvertColorHexa;
import com.bill24.b24paymentsdk.model.CheckoutPageConfigModel;
import com.bill24.b24paymentsdk.model.appearance.darkMode.DarkModeModel;
import com.bill24.b24paymentsdk.model.appearance.lightMode.LightModeModel;

public class CustomTheme {

    public String getPrimaryTextColor() {
        return primaryTextColor;
    }

    public void setPrimaryTextColor(String primaryTextColor) {
        this.primaryTextColor = primaryTextColor;
    }

    public String getPrimaryBackgroundColor() {
        return primaryBackgroundColor;
    }

    public void setPrimaryBackgroundColor(String primaryBackgroundColor) {
        this.primaryBackgroundColor = primaryBackgroundColor;
    }

    public String getSecondaryTextColor() {
        return secondaryTextColor;
    }

    public void setSecondaryTextColor(String secondaryTextColor) {
        this.secondaryTextColor = secondaryTextColor;
    }

    public String getSecondaryBackgroundColor() {
        return secondaryBackgroundColor;
    }

    public void setSecondaryBackgroundColor(String secondaryBackgroundColor) {
        this.secondaryBackgroundColor = secondaryBackgroundColor;
    }

    public String getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(String indicatorColor) {
        this.indicatorColor = indicatorColor;
    }

    public String getLabelTextColor() {
        return labelTextColor;
    }

    public void setLabelTextColor(String labelTextColor) {
        this.labelTextColor = labelTextColor;
    }

    public String getLabelBackgroundColor() {
        return labelBackgroundColor;
    }

    public void setLabelBackgroundColor(String labelBackgroundColor) {
        this.labelBackgroundColor = labelBackgroundColor;
    }

    public String getAlertTextColor() {
        return alertTextColor;
    }

    public void setAlertTextColor(String alertTextColor) {
        this.alertTextColor = alertTextColor;
    }

    public String getAlertBackgroundColor() {
        return alertBackgroundColor;
    }

    public void setAlertBackgroundColor(String alertBackgroundColor) {
        this.alertBackgroundColor = alertBackgroundColor;
    }

    public String getFavoriteButtonTextColor() {
        return favoriteButtonTextColor;
    }

    public void setFavoriteButtonTextColor(String favoriteButtonTextColor) {
        this.favoriteButtonTextColor = favoriteButtonTextColor;
    }

    public String getFavoriteButtonBackgroundColor() {
        return favoriteButtonBackgroundColor;
    }

    public void setFavoriteButtonBackgroundColor(String favoriteButtonBackgroundColor) {
        this.favoriteButtonBackgroundColor = favoriteButtonBackgroundColor;
    }

    public String getBankButtonBackgroundColor() {
        return bankButtonBackgroundColor;
    }

    public void setBankButtonBackgroundColor(String bankButtonBackgroundColor) {
        this.bankButtonBackgroundColor = bankButtonBackgroundColor;
    }

    public String getActionButtonTextColor() {
        return actionButtonTextColor;
    }

    public void setActionButtonTextColor(String actionButtonTextColor) {
        this.actionButtonTextColor = actionButtonTextColor;
    }

    public String getActionButtonBackgroundColor() {
        return actionButtonBackgroundColor;
    }

    public void setActionButtonBackgroundColor(String actionButtonBackgroundColor) {
        this.actionButtonBackgroundColor = actionButtonBackgroundColor;
    }

    public String getButtonTextColor() {
        return buttonTextColor;
    }

    public void setButtonTextColor(String buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
    }

    public String getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

    public void setButtonBackgroundColor(String buttonBackgroundColor) {
        this.buttonBackgroundColor = buttonBackgroundColor;
    }
    public String getBankButtonTextPrimaryColor() {
        return bankButtonTextPrimaryColor;
    }

    public void setBankButtonTextPrimaryColor(String bankButtonTextPrimaryColor) {
        this.bankButtonTextPrimaryColor = bankButtonTextPrimaryColor;
    }

    public String getBanButtonTextSecondaryColor() {
        return banButtonTextSecondaryColor;
    }

    public void setBanButtonTextSecondaryColor(String banButtonTextSecondaryColor) {
        this.banButtonTextSecondaryColor = banButtonTextSecondaryColor;
    }

    private String primaryTextColor;
    private String primaryBackgroundColor;
    private  String secondaryTextColor;
    private String secondaryBackgroundColor;
    private String indicatorColor;

    private String labelTextColor;
    private String labelBackgroundColor;
    private String alertTextColor;
    private String alertBackgroundColor;
    private String favoriteButtonTextColor;
    private String favoriteButtonBackgroundColor="#1F57BC1E";
    private String bankButtonTextPrimaryColor;
    private String banButtonTextSecondaryColor;
    private String bankButtonBackgroundColor;
    private String actionButtonTextColor;
    private String actionButtonBackgroundColor;
    private String buttonTextColor;
    private String buttonBackgroundColor;


    public static CustomTheme getThemeFromAPI(boolean isLightMode, CheckoutPageConfigModel checkoutPageConfigModel){
        CustomTheme customTheme=new CustomTheme();
        if(isLightMode){
            LightModeModel lightModeModel=checkoutPageConfigModel.getAppearance().getLightMode();

            String priTextColor=lightModeModel.getPrimaryColor().getTextColor();
            String priTextColorHexa=ConvertColorHexa.convertHex(priTextColor);
            customTheme.setPrimaryTextColor(priTextColorHexa);

            String priBackColor=lightModeModel.getPrimaryColor().getBackgroundColor();
            String priBackColorHexa=ConvertColorHexa.convertHex(priBackColor);
            customTheme.setPrimaryBackgroundColor(priBackColorHexa);

            String secTextColor=lightModeModel.getSecondaryColor().getTextColor();
            String secTextColorHexa=ConvertColorHexa.convertHex(secTextColor);
            customTheme.setSecondaryTextColor(secTextColorHexa);

            String secBackColor=lightModeModel.getSecondaryColor().getBackgroundColor();
            String secBackColorHexa=ConvertColorHexa.convertHex(secBackColor);
            customTheme.setSecondaryBackgroundColor(secBackColorHexa);

            String indicColor=lightModeModel.getIndicatorColor();
            String indicColorHexa=ConvertColorHexa.convertHex(indicColor);
            customTheme.setIndicatorColor(indicColorHexa);

            String labelTextColor=lightModeModel.getLabel().getTextColor();
            String labelTextColorHexa=ConvertColorHexa.convertHex(labelTextColor);
            customTheme.setLabelTextColor(labelTextColorHexa);

            String labalBackColor=lightModeModel.getLabel().getBackgroundColor();
            String labelBackColorHexa=ConvertColorHexa.convertHex(labalBackColor);
            customTheme.setLabelBackgroundColor(labelBackColorHexa);

            String alertTextColor=lightModeModel.getAlert().getTextColor();
            String alertTextColorHexa=ConvertColorHexa.convertHex(alertTextColor);
            customTheme.setAlertTextColor(alertTextColorHexa);

            String alertBackColor=lightModeModel.getAlert().getBackgroundColor();
            String alertBackColorHexa=ConvertColorHexa.convertHex(alertBackColor);
            customTheme.setAlertBackgroundColor(alertBackColorHexa);

            String favButtonTextColor=lightModeModel.getButton().getFavoriteButton().getTextColor();
            String favButtonTextColorHexa=ConvertColorHexa.convertHex(favButtonTextColor);
            customTheme.setFavoriteButtonTextColor(favButtonTextColorHexa);

            String favButtonBackColor;
            if (
                    lightModeModel.getButton().getFavoriteButton().getBackgroundColor().isBlank()
                    || lightModeModel.getButton().getFavoriteButton().getBackgroundColor().isEmpty()
            ){
                favButtonBackColor= customTheme.getFavoriteButtonBackgroundColor();
            }
            else {
                favButtonBackColor=lightModeModel.getButton().getFavoriteButton().getBackgroundColor();
            }
            String favButtonBackColorHexa=ConvertColorHexa.convertHex(favButtonBackColor);
            customTheme.setFavoriteButtonBackgroundColor(favButtonBackColorHexa);

            String bankBtnTextPriColor=lightModeModel.getButton().getBankButton().getTextPrimary();
            String bankBtnTextPriColorHexa=ConvertColorHexa.convertHex(bankBtnTextPriColor);
            customTheme.setBankButtonTextPrimaryColor(bankBtnTextPriColorHexa);

            String bankBtnTextSecColor=lightModeModel.getButton().getBankButton().getTextSecondary();
            String bankBtnTextSecColorHexa=ConvertColorHexa.convertHex(bankBtnTextSecColor);
            customTheme.setBanButtonTextSecondaryColor(bankBtnTextSecColorHexa);

            String bankBtnBackColor=lightModeModel.getButton().getBankButton().getBackgroundColor();
            String bankBtnBackColorHexa=ConvertColorHexa.convertHex(bankBtnBackColor);
            customTheme.setBankButtonBackgroundColor(bankBtnBackColorHexa);

            String actBtnTextColor=lightModeModel.getButton().getActionButton().getTextColor();
            String actBtnTextColorHexa=ConvertColorHexa.convertHex(actBtnTextColor);
            customTheme.setActionButtonTextColor(actBtnTextColorHexa);

            String actBtnBackColor=lightModeModel.getButton().getActionButton().getBackgroundColor();
            String actBtnBackColorHexa=ConvertColorHexa.convertHex(actBtnBackColor);
            customTheme.setActionButtonBackgroundColor(actBtnBackColorHexa);

            String btnTextColor=lightModeModel.getButton().getTextColor();
            String btnTextColorHexa=ConvertColorHexa.convertHex(btnTextColor);
            customTheme.setButtonTextColor(btnTextColorHexa);

            String btnBackColor=lightModeModel.getButton().getBackgroundColor();
            String btnBackColorHexa=ConvertColorHexa.convertHex(btnBackColor);
            customTheme.setButtonBackgroundColor(btnBackColorHexa);

            return customTheme;
        }else {
            DarkModeModel darkModeModel=checkoutPageConfigModel.getAppearance().getDarkMode();

            String priTextColor=darkModeModel.getPrimaryColor().getTextColor();
            String priTextColorHexa=ConvertColorHexa.convertHex(priTextColor);
            customTheme.setPrimaryTextColor(priTextColorHexa);

            String priBackColor=darkModeModel.getPrimaryColor().getBackgroundColor();
            String priBackColorHexa=ConvertColorHexa.convertHex(priBackColor);
            customTheme.setPrimaryBackgroundColor(priBackColorHexa);

            String secTextColor=darkModeModel.getSecondaryColor().getTextColor();
            String secTextColorHexa=ConvertColorHexa.convertHex(secTextColor);
            customTheme.setSecondaryTextColor(secTextColorHexa);

            String secBackColor=darkModeModel.getSecondaryColor().getBackgroundColor();
            String secBackColorHexa=ConvertColorHexa.convertHex(secBackColor);
            customTheme.setSecondaryBackgroundColor(secBackColorHexa);

            String indicColor=darkModeModel.getIndicatorColor();
            String indicColorHexa=ConvertColorHexa.convertHex(indicColor);
            customTheme.setIndicatorColor(indicColorHexa);

            String labelTextColor=darkModeModel.getLabel().getTextColor();
            String labelTextColorHexa=ConvertColorHexa.convertHex(labelTextColor);
            customTheme.setLabelTextColor(labelTextColorHexa);

            String labalBackColor=darkModeModel.getLabel().getBackgroundColor();
            String labelBackColorHexa=ConvertColorHexa.convertHex(labalBackColor);
            customTheme.setLabelBackgroundColor(labelBackColorHexa);

            String alertTextColor=darkModeModel.getAlert().getTextColor();
            String alertTextColorHexa=ConvertColorHexa.convertHex(alertTextColor);
            customTheme.setAlertTextColor(alertTextColorHexa);

            String alertBackColor=darkModeModel.getAlert().getBackgroundColor();
            String alertBackColorHexa=ConvertColorHexa.convertHex(alertBackColor);
            customTheme.setAlertBackgroundColor(alertBackColorHexa);

            String favButtonTextColor=darkModeModel.getButton().getFavoriteButton().getTextColor();
            String favButtonTextColorHexa=ConvertColorHexa.convertHex(favButtonTextColor);
            customTheme.setFavoriteButtonTextColor(favButtonTextColorHexa);

            String favButtonBackColor=darkModeModel.getButton().getFavoriteButton().getBackgroundColor();
            String favButtonBackColorHexa=ConvertColorHexa.convertHex(favButtonBackColor);
            customTheme.setFavoriteButtonBackgroundColor(favButtonBackColorHexa);

            String bankBtnTextPriColor=darkModeModel.getButton().getBankButton().getTextPrimary();
            String bankBtnTextPriColorHexa=ConvertColorHexa.convertHex(bankBtnTextPriColor);
            customTheme.setBankButtonTextPrimaryColor(bankBtnTextPriColorHexa);

            String bankBtnTextSecColor=darkModeModel.getButton().getBankButton().getTextSecondary();
            String bankBtnTextSecColorHexa=ConvertColorHexa.convertHex(bankBtnTextSecColor);
            customTheme.setBanButtonTextSecondaryColor(bankBtnTextSecColorHexa);

            String bankBtnBackColor=darkModeModel.getButton().getBankButton().getBackgroundColor();
            String bankBtnBackColorHexa=ConvertColorHexa.convertHex(bankBtnBackColor);
            customTheme.setBankButtonBackgroundColor(bankBtnBackColorHexa);

            String actBtnTextColor=darkModeModel.getButton().getActionButton().getTextColor();
            String actBtnTextColorHexa=ConvertColorHexa.convertHex(actBtnTextColor);
            customTheme.setActionButtonTextColor(actBtnTextColorHexa);

            String actBtnBackColor=darkModeModel.getButton().getActionButton().getBackgroundColor();
            String actBtnBackColorHexa=ConvertColorHexa.convertHex(actBtnBackColor);
            customTheme.setActionButtonBackgroundColor(actBtnBackColorHexa);

            String btnTextColor=darkModeModel.getButton().getTextColor();
            String btnTextColorHexa=ConvertColorHexa.convertHex(btnTextColor);
            customTheme.setButtonTextColor(btnTextColorHexa);

            String btnBackColor=darkModeModel.getButton().getBackgroundColor();
            String btnBackColorHexa=ConvertColorHexa.convertHex(btnBackColor);
            customTheme.setButtonBackgroundColor(btnBackColorHexa);

            return customTheme;
        }

    }

}
