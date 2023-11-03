package com.bill24.b24paymentsdk.model.main;

import androidx.fragment.app.FragmentManager;

import com.bill24.b24paymentsdk.bottomsheetDialogFragment.BottomSheet;

public class B24PaymentSdk {
    public B24PaymentSdk(){
    }
    public static void init(
            FragmentManager fragmentManager,
            String transactionId,
            String refererKey,
            boolean isLightMode,
            String language,
            String env){

        BottomSheet bottomSheet=new BottomSheet(transactionId,refererKey,isLightMode,language,env);
        bottomSheet.show(fragmentManager,bottomSheet.getTag());

    }
    public static void init(
            FragmentManager fragmentManager,
            String transactionId,
            String refererKey,
            boolean isLightMode,
            String language){
        BottomSheet bottomSheet=new BottomSheet(transactionId,refererKey,isLightMode,language);
        bottomSheet.show(fragmentManager,bottomSheet.getTag());

    }

    public static void init(
            FragmentManager fragmentManager,
            String transactionId,
            String refererKey,
            boolean isLightMode){
        BottomSheet bottomSheet=new BottomSheet(transactionId,refererKey,isLightMode);
        bottomSheet.show(fragmentManager, bottomSheet.getTag());
    }

}
