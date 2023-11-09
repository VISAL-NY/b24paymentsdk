package com.bill24.b24paymentsdk.main;

import androidx.fragment.app.FragmentManager;

import com.bill24.b24paymentsdk.bottomsheetDialogFragment.BottomSheet;

public class B24PaymentSdk {
    public B24PaymentSdk(){
    }

    public static void initSdk(
            FragmentManager fragmentManager,
            String tranId,
            String refererKey){
        BottomSheet bottomSheet=new BottomSheet(tranId,refererKey);
        bottomSheet.show(fragmentManager, bottomSheet.getTag());
    }
    public static void initSdk(
            FragmentManager fragmentManager,
            String tranId,
            String refererKey,
            String language
    ){
        BottomSheet bottomSheet=new BottomSheet(tranId,refererKey,language);
        bottomSheet.show(fragmentManager,bottomSheet.getTag());

    }

    public static void initSdk(
            FragmentManager fragmentManager,
            String tranId,
            String refererKey,
            boolean darkMode){
        BottomSheet bottomSheet=new BottomSheet(tranId,refererKey,darkMode);
        bottomSheet.show(fragmentManager,bottomSheet.getTag());

    }
    public static void initSdk(
            FragmentManager fragmentManager,
            String tranId,
            String refererKey,
            boolean darkMode,
            boolean isProduction){
        BottomSheet bottomSheet=new BottomSheet(tranId,refererKey,darkMode,isProduction);
        bottomSheet.show(fragmentManager,bottomSheet.getTag());

    }

    public static void initSdk(
            FragmentManager fragmentManager,
            String tranId,
            String refererKey,
            String language,
            boolean darkMode,
            boolean isProduction
    ){
        BottomSheet bottomSheet=new BottomSheet(tranId,refererKey,language,darkMode,isProduction);
        bottomSheet.show(fragmentManager,bottomSheet.getTag());
    }





}
