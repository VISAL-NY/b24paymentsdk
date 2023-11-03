package com.bill24.b24paymentsdk.main;

import androidx.fragment.app.FragmentManager;

import com.bill24.b24paymentsdk.bottomsheetDialogFragment.BottomSheet;

public class B24PaymentSdk {
    public B24PaymentSdk(){
    }
    public static void init(
            FragmentManager fragmentManager,
            String tranId,
            String refererKey,
            String language,
            boolean isLightMode,
            String env){
        BottomSheet bottomSheet=new BottomSheet(tranId,refererKey,language,isLightMode,env);
        bottomSheet.show(fragmentManager,bottomSheet.getTag());

    }
    public static void init(
            FragmentManager fragmentManager,
            String tranId,
            String refererKey,
            String language,
            boolean isLightMode
           ){
        BottomSheet bottomSheet=new BottomSheet(tranId,refererKey,language,isLightMode);
        bottomSheet.show(fragmentManager,bottomSheet.getTag());

    }

    public static void init(
            FragmentManager fragmentManager,
            String tranId,
            String refererKey,
            boolean isLightMode){
        BottomSheet bottomSheet=new BottomSheet(tranId,refererKey,isLightMode);
        bottomSheet.show(fragmentManager, bottomSheet.getTag());
    }

}
