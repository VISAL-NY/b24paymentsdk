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

    private String primaryTextColor="#375C94";
    private String primaryBackgroundColor="#FFFFFF";
    private  String secondaryTextColor="#9E9E9E";
    private String secondaryBackgroundColor="#FBFBFB";
    private String indicatorColor="#E0E0E0";
    private String labelTextColor="#707070";
    private String labelBackgroundColor="#F7F7F7";
    private String alertTextColor="#57BC1E";
    private String alertBackgroundColor="#1F57BC1E";
    private String favoriteButtonTextColor="#1F57BC1E";
    private String favoriteButtonBackgroundColor="#1F57BC1E";
    private String bankButtonTextPrimaryColor="#171716";
    private String banButtonTextSecondaryColor="#707070";
    private String bankButtonBackgroundColor="#FFFFFF";
    private String actionButtonTextColor="#0476FB";
    private String actionButtonBackgroundColor="#2B0476FB";
    private String buttonTextColor="#FFFFFF";
    private String buttonBackgroundColor="#528A36";


    public static CustomTheme getThemeFromAPI(boolean isLightMode, CheckoutPageConfigModel checkoutPageConfigModel){
        CustomTheme customTheme=new CustomTheme();

        if(isLightMode){
            LightModeModel lightModeModel=checkoutPageConfigModel.getAppearance().getLightMode();

            String priTextColor;
            if(lightModeModel.getPrimaryColor().getTextColor().isEmpty() || lightModeModel.getPrimaryColor().getTextColor().isBlank()){
                priTextColor=customTheme.getPrimaryTextColor();
            }else {
                priTextColor=lightModeModel.getPrimaryColor().getTextColor();
            }
            String priTextColorHexa=ConvertColorHexa.convertHex(priTextColor);
            customTheme.setPrimaryTextColor(priTextColorHexa);

            String priBackColor;
            if(lightModeModel.getPrimaryColor().getBackgroundColor().isBlank() || lightModeModel.getPrimaryColor().getBackgroundColor().isEmpty()){
                priBackColor=customTheme.getPrimaryBackgroundColor();
            }else {
                priBackColor=lightModeModel.getPrimaryColor().getBackgroundColor();
            }
            String priBackColorHexa=ConvertColorHexa.convertHex(priBackColor);
            customTheme.setPrimaryBackgroundColor(priBackColorHexa);

            String secTextColor;
            if(lightModeModel.getSecondaryColor().getTextColor().isEmpty() || lightModeModel.getSecondaryColor().getTextColor().isBlank()){
                secTextColor=customTheme.getSecondaryTextColor();
            }else {
                secTextColor=lightModeModel.getSecondaryColor().getTextColor();
            }
            String secTextColorHexa=ConvertColorHexa.convertHex(secTextColor);
            customTheme.setSecondaryTextColor(secTextColorHexa);

            String secBackColor;
            if(lightModeModel.getSecondaryColor().getBackgroundColor().isBlank() || lightModeModel.getSecondaryColor().getBackgroundColor().isEmpty()){
                secBackColor=customTheme.getSecondaryBackgroundColor();
            }else {
                secBackColor=lightModeModel.getSecondaryColor().getBackgroundColor();
            }
            String secBackColorHexa=ConvertColorHexa.convertHex(secBackColor);
            customTheme.setSecondaryBackgroundColor(secBackColorHexa);

            String indicColor;
            if(lightModeModel.getIndicatorColor().isEmpty() || lightModeModel.getIndicatorColor().isBlank()){
                indicColor= customTheme.getIndicatorColor();
            }else {
                indicColor=lightModeModel.getIndicatorColor();
            }
            String indicColorHexa=ConvertColorHexa.convertHex(indicColor);
            customTheme.setIndicatorColor(indicColorHexa);

            String labelTextColor;
            if(lightModeModel.getLabel().getTextColor().isBlank() || lightModeModel.getLabel().getTextColor().isEmpty()){
                labelTextColor= customTheme.getLabelTextColor();
            }else {
                labelTextColor=lightModeModel.getLabel().getTextColor();
            }
            String labelTextColorHexa=ConvertColorHexa.convertHex(labelTextColor);
            customTheme.setLabelTextColor(labelTextColorHexa);

            String labalBackColor;
            if(lightModeModel.getLabel().getBackgroundColor().isEmpty() || lightModeModel.getLabel().getBackgroundColor().isBlank()){
                labalBackColor=customTheme.getLabelBackgroundColor();
            }else {
                labalBackColor=lightModeModel.getLabel().getBackgroundColor();
            }
            String labelBackColorHexa=ConvertColorHexa.convertHex(labalBackColor);
            customTheme.setLabelBackgroundColor(labelBackColorHexa);

            String alertTextColor;
            if(lightModeModel.getAlert().getTextColor().isBlank() || lightModeModel.getAlert().getTextColor().isEmpty()){
                alertTextColor= customTheme.getAlertTextColor();
            }else {
                alertTextColor=lightModeModel.getAlert().getTextColor();
            }
            String alertTextColorHexa=ConvertColorHexa.convertHex(alertTextColor);
            customTheme.setAlertTextColor(alertTextColorHexa);

            String alertBackColor;
            if(lightModeModel.getAlert().getBackgroundColor().isEmpty() || lightModeModel.getAlert().getBackgroundColor().isBlank()){
                alertBackColor=customTheme.getAlertBackgroundColor();
            }else {
                alertBackColor=lightModeModel.getAlert().getBackgroundColor();
            }
            String alertBackColorHexa=ConvertColorHexa.convertHex(alertBackColor);
            customTheme.setAlertBackgroundColor(alertBackColorHexa);

            String favButtonTextColor;
            if(lightModeModel.getButton().getFavoriteButton().getTextColor().isBlank() || lightModeModel.getButton().getFavoriteButton().getTextColor().isEmpty()){
                favButtonTextColor= customTheme.getFavoriteButtonTextColor();
            }else {
                favButtonTextColor=lightModeModel.getButton().getFavoriteButton().getTextColor();
            }
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

            String bankBtnTextPriColor;
            if(lightModeModel.getButton().getBankButton().getTextPrimary().isEmpty() || lightModeModel.getButton().getBankButton().getTextPrimary().isBlank()){
                bankBtnTextPriColor= customTheme.getBankButtonTextPrimaryColor();
            }else {
                bankBtnTextPriColor=lightModeModel.getButton().getBankButton().getTextPrimary();
            }
            String bankBtnTextPriColorHexa=ConvertColorHexa.convertHex(bankBtnTextPriColor);
            customTheme.setBankButtonTextPrimaryColor(bankBtnTextPriColorHexa);

            String bankBtnTextSecColor;
            if(lightModeModel.getButton().getBankButton().getTextSecondary().isBlank() || lightModeModel.getButton().getBankButton().getTextSecondary().isEmpty()){
                bankBtnTextSecColor= customTheme.getBanButtonTextSecondaryColor();
            }else {
                bankBtnTextSecColor=lightModeModel.getButton().getBankButton().getTextSecondary();
            }
            String bankBtnTextSecColorHexa=ConvertColorHexa.convertHex(bankBtnTextSecColor);
            customTheme.setBanButtonTextSecondaryColor(bankBtnTextSecColorHexa);

            String bankBtnBackColor;
            if(lightModeModel.getButton().getBankButton().getBackgroundColor().isEmpty() || lightModeModel.getButton().getBankButton().getBackgroundColor().isBlank()){
                bankBtnBackColor=customTheme.getBankButtonBackgroundColor();
            }else {
                bankBtnBackColor=lightModeModel.getButton().getBankButton().getBackgroundColor();
            }
            String bankBtnBackColorHexa=ConvertColorHexa.convertHex(bankBtnBackColor);
            customTheme.setBankButtonBackgroundColor(bankBtnBackColorHexa);

            String actBtnTextColor;
            if(lightModeModel.getButton().getActionButton().getTextColor().isBlank() || lightModeModel.getButton().getActionButton().getTextColor().isEmpty()){
                actBtnTextColor=customTheme.getActionButtonTextColor();
            }else {
                actBtnTextColor=lightModeModel.getButton().getActionButton().getTextColor();
            }
            String actBtnTextColorHexa=ConvertColorHexa.convertHex(actBtnTextColor);
            customTheme.setActionButtonTextColor(actBtnTextColorHexa);

            String actBtnBackColor;
            if(lightModeModel.getButton().getActionButton().getBackgroundColor().isEmpty() || lightModeModel.getButton().getActionButton().getBackgroundColor().isBlank()){
                actBtnBackColor=customTheme.getActionButtonBackgroundColor();
            }else {
                actBtnBackColor=lightModeModel.getButton().getActionButton().getBackgroundColor();
            }
            String actBtnBackColorHexa=ConvertColorHexa.convertHex(actBtnBackColor);
            customTheme.setActionButtonBackgroundColor(actBtnBackColorHexa);

            String btnTextColor;
            if(lightModeModel.getButton().getTextColor().isBlank() || lightModeModel.getButton().getTextColor().isEmpty()){
                btnTextColor=customTheme.getButtonTextColor();
            }else {
                btnTextColor=lightModeModel.getButton().getTextColor();
            }
            String btnTextColorHexa=ConvertColorHexa.convertHex(btnTextColor);
            customTheme.setButtonTextColor(btnTextColorHexa);

            String btnBackColor;
            if(lightModeModel.getButton().getBackgroundColor().isEmpty() || lightModeModel.getButton().getBackgroundColor().isBlank()){
                btnBackColor=customTheme.getButtonBackgroundColor();
            }else {
                btnBackColor=lightModeModel.getButton().getBackgroundColor();
            }
            String btnBackColorHexa=ConvertColorHexa.convertHex(btnBackColor);
            customTheme.setButtonBackgroundColor(btnBackColorHexa);

            return customTheme;
        }else {
            DarkModeModel darkModeModel=checkoutPageConfigModel.getAppearance().getDarkMode();

            String priTextColor;
            if(darkModeModel.getPrimaryColor().getTextColor().isEmpty() || darkModeModel.getPrimaryColor().getTextColor().isBlank()){
                priTextColor=customTheme.getPrimaryTextColor();
            }else {
                priTextColor=darkModeModel.getPrimaryColor().getTextColor();
            }
            String priTextColorHexa=ConvertColorHexa.convertHex(priTextColor);
            customTheme.setPrimaryTextColor(priTextColorHexa);

            String priBackColor;
            if(darkModeModel.getPrimaryColor().getBackgroundColor().isBlank() || darkModeModel.getPrimaryColor().getBackgroundColor().isEmpty()){
                priBackColor=customTheme.getPrimaryBackgroundColor();
            }else {
                priBackColor=darkModeModel.getPrimaryColor().getBackgroundColor();
            }
            String priBackColorHexa=ConvertColorHexa.convertHex(priBackColor);
            customTheme.setPrimaryBackgroundColor(priBackColorHexa);

            String secTextColor;
            if(darkModeModel.getSecondaryColor().getTextColor().isEmpty() || darkModeModel.getSecondaryColor().getTextColor().isBlank()){
                secTextColor=customTheme.getSecondaryTextColor();
            }else {
                secTextColor=darkModeModel.getSecondaryColor().getTextColor();
            }
            String secTextColorHexa=ConvertColorHexa.convertHex(secTextColor);
            customTheme.setSecondaryTextColor(secTextColorHexa);

            String secBackColor;
            if(darkModeModel.getSecondaryColor().getBackgroundColor().isBlank() || darkModeModel.getSecondaryColor().getBackgroundColor().isEmpty()){
                secBackColor=customTheme.getSecondaryBackgroundColor();
            }else {
                secBackColor=darkModeModel.getSecondaryColor().getBackgroundColor();
            }
            String secBackColorHexa=ConvertColorHexa.convertHex(secBackColor);
            customTheme.setSecondaryBackgroundColor(secBackColorHexa);

            String indicColor;
            if(darkModeModel.getIndicatorColor().isEmpty() || darkModeModel.getIndicatorColor().isBlank()){
                indicColor= customTheme.getIndicatorColor();
            }else {
                indicColor=darkModeModel.getIndicatorColor();
            }
            String indicColorHexa=ConvertColorHexa.convertHex(indicColor);
            customTheme.setIndicatorColor(indicColorHexa);

            String labelTextColor;
            if(darkModeModel.getLabel().getTextColor().isBlank() || darkModeModel.getLabel().getTextColor().isEmpty()){
                labelTextColor= customTheme.getLabelTextColor();
            }else {
                labelTextColor=darkModeModel.getLabel().getTextColor();
            }
            String labelTextColorHexa=ConvertColorHexa.convertHex(labelTextColor);
            customTheme.setLabelTextColor(labelTextColorHexa);

            String labalBackColor;
            if(darkModeModel.getLabel().getBackgroundColor().isEmpty() || darkModeModel.getLabel().getBackgroundColor().isBlank()){
                labalBackColor=customTheme.getLabelBackgroundColor();
            }else {
                labalBackColor=darkModeModel.getLabel().getBackgroundColor();
            }
            String labelBackColorHexa=ConvertColorHexa.convertHex(labalBackColor);
            customTheme.setLabelBackgroundColor(labelBackColorHexa);

            String alertTextColor;
            if(darkModeModel.getAlert().getTextColor().isBlank() || darkModeModel.getAlert().getTextColor().isEmpty()){
                alertTextColor= customTheme.getAlertTextColor();
            }else {
                alertTextColor=darkModeModel.getAlert().getTextColor();
            }
            String alertTextColorHexa=ConvertColorHexa.convertHex(alertTextColor);
            customTheme.setAlertTextColor(alertTextColorHexa);

            String alertBackColor;
            if(darkModeModel.getAlert().getBackgroundColor().isEmpty() || darkModeModel.getAlert().getBackgroundColor().isBlank()){
                alertBackColor=customTheme.getAlertBackgroundColor();
            }else {
                alertBackColor=darkModeModel.getAlert().getBackgroundColor();
            }
            String alertBackColorHexa=ConvertColorHexa.convertHex(alertBackColor);
            customTheme.setAlertBackgroundColor(alertBackColorHexa);

            String favButtonTextColor;
            if(darkModeModel.getButton().getFavoriteButton().getTextColor().isBlank() || darkModeModel.getButton().getFavoriteButton().getTextColor().isEmpty()){
                favButtonTextColor= customTheme.getFavoriteButtonTextColor();
            }else {
                favButtonTextColor=darkModeModel.getButton().getFavoriteButton().getTextColor();
            }
            String favButtonTextColorHexa=ConvertColorHexa.convertHex(favButtonTextColor);
            customTheme.setFavoriteButtonTextColor(favButtonTextColorHexa);

            String favButtonBackColor;
            if (
                    darkModeModel.getButton().getFavoriteButton().getBackgroundColor().isBlank()
                            || darkModeModel.getButton().getFavoriteButton().getBackgroundColor().isEmpty()
            ){
                favButtonBackColor= customTheme.getFavoriteButtonBackgroundColor();
            }
            else {
                favButtonBackColor=darkModeModel.getButton().getFavoriteButton().getBackgroundColor();
            }
            String favButtonBackColorHexa=ConvertColorHexa.convertHex(favButtonBackColor);
            customTheme.setFavoriteButtonBackgroundColor(favButtonBackColorHexa);

            String bankBtnTextPriColor;
            if(darkModeModel.getButton().getBankButton().getTextPrimary().isEmpty() || darkModeModel.getButton().getBankButton().getTextPrimary().isBlank()){
                bankBtnTextPriColor= customTheme.getBankButtonTextPrimaryColor();
            }else {
                bankBtnTextPriColor=darkModeModel.getButton().getBankButton().getTextPrimary();
            }
            String bankBtnTextPriColorHexa=ConvertColorHexa.convertHex(bankBtnTextPriColor);
            customTheme.setBankButtonTextPrimaryColor(bankBtnTextPriColorHexa);

            String bankBtnTextSecColor;
            if(darkModeModel.getButton().getBankButton().getTextSecondary().isBlank() || darkModeModel.getButton().getBankButton().getTextSecondary().isEmpty()){
                bankBtnTextSecColor= customTheme.getBanButtonTextSecondaryColor();
            }else {
                bankBtnTextSecColor=darkModeModel.getButton().getBankButton().getTextSecondary();
            }
            String bankBtnTextSecColorHexa=ConvertColorHexa.convertHex(bankBtnTextSecColor);
            customTheme.setBanButtonTextSecondaryColor(bankBtnTextSecColorHexa);

            String bankBtnBackColor;
            if(darkModeModel.getButton().getBankButton().getBackgroundColor().isEmpty() || darkModeModel.getButton().getBankButton().getBackgroundColor().isBlank()){
                bankBtnBackColor=customTheme.getBankButtonBackgroundColor();
            }else {
                bankBtnBackColor=darkModeModel.getButton().getBankButton().getBackgroundColor();
            }
            String bankBtnBackColorHexa=ConvertColorHexa.convertHex(bankBtnBackColor);
            customTheme.setBankButtonBackgroundColor(bankBtnBackColorHexa);

            String actBtnTextColor;
            if(darkModeModel.getButton().getActionButton().getTextColor().isBlank() || darkModeModel.getButton().getActionButton().getTextColor().isEmpty()){
                actBtnTextColor=customTheme.getActionButtonTextColor();
            }else {
                actBtnTextColor=darkModeModel.getButton().getActionButton().getTextColor();
            }
            String actBtnTextColorHexa=ConvertColorHexa.convertHex(actBtnTextColor);
            customTheme.setActionButtonTextColor(actBtnTextColorHexa);

            String actBtnBackColor;
            if(darkModeModel.getButton().getActionButton().getBackgroundColor().isEmpty() || darkModeModel.getButton().getActionButton().getBackgroundColor().isBlank()){
                actBtnBackColor=customTheme.getActionButtonBackgroundColor();
            }else {
                actBtnBackColor=darkModeModel.getButton().getActionButton().getBackgroundColor();
            }
            String actBtnBackColorHexa=ConvertColorHexa.convertHex(actBtnBackColor);
            customTheme.setActionButtonBackgroundColor(actBtnBackColorHexa);

            String btnTextColor;
            if(darkModeModel.getButton().getTextColor().isBlank() || darkModeModel.getButton().getTextColor().isEmpty()){
                btnTextColor=customTheme.getButtonTextColor();
            }else {
                btnTextColor=darkModeModel.getButton().getTextColor();
            }
            String btnTextColorHexa=ConvertColorHexa.convertHex(btnTextColor);
            customTheme.setButtonTextColor(btnTextColorHexa);

            String btnBackColor;
            if(darkModeModel.getButton().getBackgroundColor().isEmpty() || darkModeModel.getButton().getBackgroundColor().isBlank()){
                btnBackColor=customTheme.getButtonBackgroundColor();
            }else {
                btnBackColor=darkModeModel.getButton().getBackgroundColor();
            }
            String btnBackColorHexa=ConvertColorHexa.convertHex(btnBackColor);
            customTheme.setButtonBackgroundColor(btnBackColorHexa);

            return customTheme;
        }

    }

}
