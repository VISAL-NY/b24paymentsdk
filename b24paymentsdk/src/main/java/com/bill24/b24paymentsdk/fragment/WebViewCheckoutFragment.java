package com.bill24.b24paymentsdk.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bill24.b24paymentsdk.R;
import com.bill24.b24paymentsdk.helper.ConvertColorHexa;
import com.bill24.b24paymentsdk.helper.SharePreferenceCustom;
import com.bill24.b24paymentsdk.model.CheckoutPageConfigModel;
import com.bill24.b24paymentsdk.model.appearance.darkMode.DarkModeModel;
import com.bill24.b24paymentsdk.model.appearance.lightMode.LightModeModel;
import com.bill24.b24paymentsdk.model.conts.Constant;


public class WebViewCheckoutFragment extends Fragment {

    private String url;
    private CheckoutPageConfigModel checkoutPageConfigModel;
    private boolean isLightMode=true;
    public WebViewCheckoutFragment(String url){
        this.url=url;
    }


    private void getSharePreference(){
        SharedPreferences preferences= getActivity().getPreferences(Context.MODE_PRIVATE);
        //checkout page config
        String checkoutPageConfigJson=preferences.getString(Constant.KEY_CHECKOUT_PAGE_CONFIG,"");
        checkoutPageConfigModel= SharePreferenceCustom.converJsonToObject(checkoutPageConfigJson, CheckoutPageConfigModel.class);

        //get isLight mode
        isLightMode=preferences.getBoolean(Constant.IS_LIGHT_MODE,true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.webview_web_checkout,container,false);
        WebView webView=view.findViewById(R.id.web_view_web_checkout);
        ProgressBar progressBar=view.findViewById(R.id.progress_loading_webview);
        FrameLayout containerWebView=view.findViewById(R.id.container_webview);
        FrameLayout loadingContainer=view.findViewById(R.id.testing_container);

        getSharePreference();

        if(isLightMode){
            LightModeModel lightModeModel=checkoutPageConfigModel.getAppearance().getLightMode();
            String webviewLoadingColor=lightModeModel.getSecondaryColor().getBackgroundColor();
            String webviewLoadingHexa= ConvertColorHexa.convertHex(webviewLoadingColor);

            loadingContainer.setBackgroundColor(Color.parseColor(webviewLoadingHexa));
        }else {
            DarkModeModel lightModeModel=checkoutPageConfigModel.getAppearance().getDarkMode();
            String webviewLoadingColor=lightModeModel.getSecondaryColor().getBackgroundColor();
            String webviewLoadingHexa= ConvertColorHexa.convertHex(webviewLoadingColor);

            loadingContainer.setBackgroundColor(Color.parseColor(webviewLoadingHexa));
        }

        //set container of webview height to 90%
        int screenHeight=getResources().getDisplayMetrics().heightPixels;
        int newHeight=(int) (screenHeight*0.9);
        FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,newHeight);
        containerWebView.setLayoutParams(layoutParams);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }else {
                    progressBar.setVisibility(View.VISIBLE);

                }
            }
        });

        webView.loadUrl(url);
        //webView.loadUrl("https://www.youtube.com/results?search_query=how+to+use+webview+in+bottomsheet+in+android");
       // webView.loadUrl("https://www.youtube.com/watch?v=EitoSHCDmPM&list=RDly_wjN27KJE&index=14");
        return view;
    }
}
