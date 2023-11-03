package com.bill24.b24paymentsdk.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bill24.b24paymentsdk.R;
import com.bill24.b24paymentsdk.SuccessActivity;
import com.bill24.b24paymentsdk.adapter.PaymentMethodAdapter;
import com.bill24.b24paymentsdk.bottomsheetDialogFragment.BottomSheet;
import com.bill24.b24paymentsdk.core.RequestAPI;
import com.bill24.b24paymentsdk.helper.ChangLanguage;
import com.bill24.b24paymentsdk.helper.ConnectivityState;
import com.bill24.b24paymentsdk.helper.ConvertColorHexa;
import com.bill24.b24paymentsdk.helper.SetFont;
import com.bill24.b24paymentsdk.helper.SharePreferenceCustom;
import com.bill24.b24paymentsdk.helper.StickyHeaderItemDecoration;
import com.bill24.b24paymentsdk.helper.Translate;
import com.bill24.b24paymentsdk.model.BankPaymentMethodItemModel;
import com.bill24.b24paymentsdk.model.BankPaymentMethodModel;
import com.bill24.b24paymentsdk.model.BillerModel;
import com.bill24.b24paymentsdk.model.CheckoutDetailModel;
import com.bill24.b24paymentsdk.model.CheckoutPageConfigModel;
import com.bill24.b24paymentsdk.model.ExpiredTransactionModel;
import com.bill24.b24paymentsdk.model.GenerateLinkDeepLinkModel;
import com.bill24.b24paymentsdk.model.TransactionInfoModel;
import com.bill24.b24paymentsdk.model.appearance.darkMode.DarkModeModel;
import com.bill24.b24paymentsdk.model.appearance.lightMode.LightModeModel;
import com.bill24.b24paymentsdk.model.baseResponseModel.BaseResponse;
import com.bill24.b24paymentsdk.model.conts.Bank;
import com.bill24.b24paymentsdk.model.conts.Constant;
import com.bill24.b24paymentsdk.model.conts.LanguageCode;
import com.bill24.b24paymentsdk.model.requestModel.ExpiredRequestModel;
import com.bill24.b24paymentsdk.model.requestModel.GenerateDeeplinkRequestModel;
import com.google.android.material.divider.MaterialDivider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethodFragment extends Fragment
        implements PaymentMethodAdapter.PaymentMethodClickListener,
        ConnectivityState.ConnectivityListener {
    private ConnectivityState connectivityState;
    private RecyclerView recyclerViewPaymentMethod;
    private AppCompatTextView textVersion,
            textTotalAmount,textCurrency,textPaymentMethod,
            textTotalAmountTitle,texBill24,textPowerby;
    private MaterialDivider verticalDivider;
    private LinearLayoutCompat containerPaymentMethod;
    private FrameLayout bottomPaymentContainer,bottomDashLine;
    private List<BankPaymentMethodModel> bankPaymentMethodModelList;
    private TransactionInfoModel transactionInfoModel=new TransactionInfoModel();
    private CheckoutPageConfigModel checkoutPageConfigModel=new CheckoutPageConfigModel();
    private String refererKey,language,baseUrl;
    private boolean isLightMode;
    private CheckoutDetailModel checkoutDetailModel=new CheckoutDetailModel();
    public PaymentMethodFragment(){

    }
    
    private void initView(View view){
        textPaymentMethod=view.findViewById(R.id.text_payment_method);
        recyclerViewPaymentMethod=view.findViewById(R.id.recycler_payment_method);
        textTotalAmountTitle=view.findViewById(R.id.text_total_amount_title);
        textVersion=view.findViewById(R.id.text_version);
        textCurrency=view.findViewById(R.id.text_total_amount_currency);
        textTotalAmount =view.findViewById(R.id.text_total_amount);
        containerPaymentMethod=view.findViewById(R.id.container_payment_method);
        texBill24=view.findViewById(R.id.bill24);
        textPowerby=view.findViewById(R.id.text_power_by);
        verticalDivider=view.findViewById(R.id.vertical_divider);
        bottomPaymentContainer=view.findViewById(R.id.bottom_payment_method_container);
        bottomDashLine=view.findViewById(R.id.bottom_dash_line_container);
    }

    private void bindView(){
        textTotalAmount.setText(transactionInfoModel.getTranAmountDisplay());
        textCurrency.setText(transactionInfoModel.getCurrency());
    }

    private void toggleBill24(){
        if(checkoutPageConfigModel.isDisplayBill24Info()){
            texBill24.setVisibility(View.VISIBLE);
            textPowerby.setVisibility(View.VISIBLE);
            verticalDivider.setVisibility(View.VISIBLE);

        }else {
            texBill24.setVisibility(View.GONE);
            textPowerby.setVisibility(View.GONE);
            verticalDivider.setVisibility(View.GONE);
        }
    }

    private void translateLanguage(){
        if(language.equals(LanguageCode.EN)){
            textPaymentMethod.setText(Translate.PAYMENT_METHOD_EN);
            textTotalAmountTitle.setText(Translate.TOTAL_AMOUNT_EN);
        }
        if(language.equals(LanguageCode.KH)){
            textPaymentMethod.setText(Translate.PAYMENT_METHOD_KM);
            textTotalAmountTitle.setText(Translate.TOTAL_AMOUNT_KM);
        }
    }

    private void updateFont(){
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(getContext(),language);

        textPaymentMethod.setTypeface(typeface);
        textPaymentMethod.setTextSize(16);
        textPaymentMethod.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        textPaymentMethod.setTextColor(getResources().getColor(R.color.header_font_color));

        textTotalAmountTitle.setTypeface(typeface);
        textTotalAmountTitle.setTextSize(13);
        textTotalAmountTitle.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);

    }

    private void setupRecyclerView(PaymentMethodAdapter.PaymentMethodClickListener listener){

        if(bankPaymentMethodModelList !=null && !bankPaymentMethodModelList.isEmpty()){
            PaymentMethodAdapter adapter=new PaymentMethodAdapter();

            adapter.setPaymentMethod(
                    checkoutPageConfigModel,
                    transactionInfoModel,
                    bankPaymentMethodModelList,
                    transactionInfoModel.getTranNo(),
                    refererKey,
                    isLightMode,
                    language,
                    baseUrl);

            adapter.setOnItemClickListener(listener);
            recyclerViewPaymentMethod.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewPaymentMethod.setAdapter(adapter);
            //Set Header to Sticky
            StickyHeaderItemDecoration stickyHeaderItemDecoration=new StickyHeaderItemDecoration((StickyHeaderItemDecoration.StickyHeaderInterface)adapter);
            recyclerViewPaymentMethod.addItemDecoration(stickyHeaderItemDecoration);
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayOsVersion(){

           // PackageInfo packageInfo=getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),0);
            //String version=packageInfo.versionName;
            //int versionCode=packageInfo.versionCode;
            String version="1.0.0";
            String versionCode="0";
            textVersion.setText("V" + version + "." + versionCode);
    }
    private void postExpiredTran(ExpiredRequestModel model){
        RequestAPI requestAPI=new RequestAPI(refererKey,baseUrl);
        Call<BaseResponse<ExpiredTransactionModel>> call=requestAPI.postExpireTran(model);
        call.enqueue(new Callback<BaseResponse<ExpiredTransactionModel>>() {
            @Override
            public void onResponse(Call<BaseResponse<ExpiredTransactionModel>> call, Response<BaseResponse<ExpiredTransactionModel>> response) {

            }
            @Override
            public void onFailure(Call<BaseResponse<ExpiredTransactionModel>> call, Throwable t) {

            }
        });
    }


    private void launchDeeplink(String url){
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(intent);


    }

    private void getPreference(){
        SharedPreferences preferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        String bankPaymentMethodJson=preferences.getString(Constant.KEY_PAYMENT_METHOD,"");
        bankPaymentMethodModelList= SharePreferenceCustom.convertFromJsonToListObject(bankPaymentMethodJson,BankPaymentMethodModel.class);
        String transactionInfoJson=preferences.getString(Constant.KEY_TRANSACTION_INFO,"");
        transactionInfoModel=SharePreferenceCustom.converJsonToObject(transactionInfoJson, TransactionInfoModel.class);
        String checkoutPageConfigJson=preferences.getString(Constant.KEY_CHECKOUT_PAGE_CONFIG,"");
        checkoutPageConfigModel=SharePreferenceCustom.converJsonToObject(checkoutPageConfigJson, CheckoutPageConfigModel.class);

        //get language
        language=preferences.getString(Constant.KEY_LANGUAGE_CODE,"");
        //get refererKey
        refererKey=preferences.getString(Constant.KEY_REFERER_KEY,"");

        //get isLightMode
        isLightMode=preferences.getBoolean(Constant.IS_LIGHT_MODE,true);

        //get base url
        baseUrl=preferences.getString(Constant.BASE_URL_ENV,"");

    }


    private void applyStyleLightMode(){
        LightModeModel lightModeModel=checkoutPageConfigModel.getAppearance().getLightMode();

        //text header
        String headerColor=lightModeModel.getLabel().getTextColor();
        String convertHeaderColor= ConvertColorHexa.convertHex(headerColor);
        textPaymentMethod.setTextColor(Color.parseColor(convertHeaderColor));;

        //recyclerView bg
        String bgRecylcerView=lightModeModel.getSecondaryColor().getBackgroundColor();
        String bgRecyclerViewHexa=ConvertColorHexa.convertHex(bgRecylcerView);
        recyclerViewPaymentMethod.setBackgroundColor(Color.parseColor(bgRecyclerViewHexa));

        //bottom Container
        bottomPaymentContainer.setBackgroundColor(Color.parseColor(bgRecyclerViewHexa));

        // total amount text power_by bill24 version
        String totalAmountTitleColor=lightModeModel.getSecondaryColor().getTextColor();
        String totalAmountTitleHexa=ConvertColorHexa.convertHex(totalAmountTitleColor);
        textTotalAmountTitle.setTextColor(Color.parseColor(totalAmountTitleHexa));
        textPowerby.setTextColor(Color.parseColor(totalAmountTitleHexa));
        texBill24.setTextColor(Color.parseColor(totalAmountTitleHexa));
        verticalDivider.setDividerColor(Color.parseColor(totalAmountTitleHexa));
        textVersion.setTextColor(Color.parseColor(totalAmountTitleHexa));

        //total amount currency
        String totalAmountColor=lightModeModel.getPrimaryColor().getTextColor();
        String totalAmountHexa=ConvertColorHexa.convertHex(totalAmountColor);
        textTotalAmount.setTextColor(Color.parseColor(totalAmountHexa));
        textCurrency.setTextColor(Color.parseColor(totalAmountHexa));


        //dash line
        String dashLineColor=lightModeModel.getIndicatorColor();
        String dashLineColorHexa=ConvertColorHexa.convertHex(dashLineColor);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        int width=(int) (1 * getContext().getResources().getDisplayMetrics().density);
        int dashWidthHeight=(int)(5*getContext().getResources().getDisplayMetrics().density);
        gradientDrawable.setStroke(width,
                Color.parseColor(dashLineColorHexa),
                dashWidthHeight,
                dashWidthHeight);
        int cornerRadius=(int)(18 *getContext().getResources().getDisplayMetrics().density); // Set the stroke color and width
        gradientDrawable.setCornerRadius(cornerRadius); // Set the corner radius
        gradientDrawable.setDither(true);
        bottomDashLine.setBackground(gradientDrawable);




    }
    private void applyStyleDarkMode(){
        DarkModeModel darkModeModel=checkoutPageConfigModel.getAppearance().getDarkMode();

        //text header
        String headerColor=darkModeModel.getLabel().getTextColor();
        String convertHeaderColor= ConvertColorHexa.convertHex(headerColor);
        textPaymentMethod.setTextColor(Color.parseColor(convertHeaderColor));

        //recyclerView bg
        String bgRecylcerView=darkModeModel.getSecondaryColor().getBackgroundColor();
        String bgRecyclerViewHexa=ConvertColorHexa.convertHex(bgRecylcerView);
        recyclerViewPaymentMethod.setBackgroundColor(Color.parseColor(bgRecyclerViewHexa));

        //bottom Container
        bottomPaymentContainer.setBackgroundColor(Color.parseColor(bgRecyclerViewHexa));

        // total amount text power_by bill24 version
        String totalAmountTitleColor=darkModeModel.getSecondaryColor().getTextColor();
        String totalAmountTitleHexa=ConvertColorHexa.convertHex(totalAmountTitleColor);
        textTotalAmountTitle.setTextColor(Color.parseColor(totalAmountTitleHexa));
        textPowerby.setTextColor(Color.parseColor(totalAmountTitleHexa));
        texBill24.setTextColor(Color.parseColor(totalAmountTitleHexa));
        verticalDivider.setDividerColor(Color.parseColor(totalAmountTitleHexa));
        textVersion.setTextColor(Color.parseColor(totalAmountTitleHexa));

        //total amount currency
        String totalAmountColor=darkModeModel.getPrimaryColor().getTextColor();
        String totalAmountHexa=ConvertColorHexa.convertHex(totalAmountColor);
        textTotalAmount.setTextColor(Color.parseColor(totalAmountHexa));
        textCurrency.setTextColor(Color.parseColor(totalAmountHexa));


        //dash line
        String dashLineColor=darkModeModel.getIndicatorColor();
        String dashLineColorHexa=ConvertColorHexa.convertHex(dashLineColor);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        int width=(int) (1 * getContext().getResources().getDisplayMetrics().density);
        int dashWidthHeight=(int)(5*getContext().getResources().getDisplayMetrics().density);
        gradientDrawable.setStroke(width,
                Color.parseColor(dashLineColorHexa),
                dashWidthHeight,
                dashWidthHeight);
        int cornerRadius=(int)(18 *getContext().getResources().getDisplayMetrics().density); // Set the stroke color and width
        gradientDrawable.setCornerRadius(cornerRadius); // Set the corner radius
        gradientDrawable.setDither(true);
        bottomDashLine.setBackground(gradientDrawable);




    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        connectivityState=new ConnectivityState(this);//Init BroadCastReceiver

        getPreference();

        ChangLanguage.setLanguage(language,getContext());



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.payment_method_fragment_layout,container,false);
        initView(view);


        //Update Font
        updateFont();

        bindView();

        //initStyle
        if (isLightMode){
            applyStyleLightMode();
        }else {
            applyStyleDarkMode();
        }

        //translate
        translateLanguage();

        //set container of webview height to 90%
        int screenHeight=getResources().getDisplayMetrics().heightPixels;
        int newHeight=(int) (screenHeight*0.9);
        LinearLayoutCompat.LayoutParams layoutParams=new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,newHeight);
        containerPaymentMethod.setLayoutParams(layoutParams);

        //show hide bill24
        toggleBill24();

        texBill24.setOnClickListener(v->{
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(Constant.BILL24_URL));
            startActivity(intent);
        });



        //Set Up RecyclerView
        setupRecyclerView(this);

        displayOsVersion();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        //unregister broadcast when fragment is pause
        requireContext().unregisterReceiver(connectivityState);
    }

    @Override
    public void onResume() {
        super.onResume();
        //register broadcast when connectivity change
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        requireContext().registerReceiver(connectivityState,intentFilter);
    }

    @Override
    public void OnItemPaymentMethodClick(BankPaymentMethodItemModel itemModel) {
        ExpiredRequestModel expiredRequestModel=new ExpiredRequestModel(transactionInfoModel.getTranId());
        switch (itemModel.getId()){
            case Bank.KHQR:
                postExpiredTran(expiredRequestModel);
                Fragment fragment=getParentFragment();
                if(fragment !=null && fragment instanceof BottomSheet){
                    ((BottomSheet)getParentFragment()).showFragment(new KhqrFragment(transactionInfoModel.getTranNo()));
                }
                break;
            case Bank.MASTERCARD:

                Intent intent=new Intent(requireActivity(), SuccessActivity.class);
                intent.putExtra(Constant.KEY_LANGUAGE_CODE,transactionInfoModel.getLanguage());
                intent.putExtra(Constant.IS_LIGHT_MODE,isLightMode);
                intent.putExtra(Constant.KEY_TRANSACTION_INFO,transactionInfoModel);
                intent.putExtra(Constant.KEY_CHECKOUT_PAGE_CONFIG,checkoutPageConfigModel);
                intent.putExtra(Constant.KEY_BILLER,new BillerModel());

                startActivity(intent);

                //todo handle when click on mastercard

                break;
            default:
                postExpiredTran(expiredRequestModel);
                GenerateDeeplinkRequestModel generateDeeplinkRequestModel=new GenerateDeeplinkRequestModel(itemModel.getId(), transactionInfoModel.getTranNo());

                Log.d("tranNo", "OnItemPaymentMethodClick: "+transactionInfoModel.getTranNo());

                RequestAPI requestAPI=new RequestAPI(refererKey,baseUrl);

                Call<BaseResponse<GenerateLinkDeepLinkModel>> call=requestAPI.postGenerateDeeplink(generateDeeplinkRequestModel);
                call.enqueue(new Callback<BaseResponse<GenerateLinkDeepLinkModel>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<GenerateLinkDeepLinkModel>> call, Response<BaseResponse<GenerateLinkDeepLinkModel>> response) {
                        GenerateLinkDeepLinkModel generateLinkDeepLinkModel;
                        if(response.isSuccessful()){
                            BaseResponse<GenerateLinkDeepLinkModel> deeplink=response.body();
                            if(deeplink !=null){
                                generateLinkDeepLinkModel=deeplink.getData();
//                                if(itemModel.isSupportDeeplink()){
                                    if(!generateLinkDeepLinkModel.getMobileDeepLink().isEmpty()){
                                        launchDeeplink(generateLinkDeepLinkModel.getMobileDeepLink());
                                        return;
                                    }
                               // }
                                //if(itemModel.isSupportCheckoutPage()){
                                    if(!generateLinkDeepLinkModel.getWebPaymentLink().isEmpty()){
                                        ((BottomSheet)getParentFragment()).showFragment(new WebViewCheckoutFragment(generateLinkDeepLinkModel.getWebPaymentLink()));
                                    }
                               // }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<BaseResponse<GenerateLinkDeepLinkModel>> call, Throwable t) {
                        Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected, boolean wasResortConnection) {
        if(isConnected){
            if(!wasResortConnection){
                //todo handle display connection restore
                //Toast.makeText(getContext(), "have internet", Toast.LENGTH_SHORT).show();
            }
        }else {
            //todo handle display lose connection
            //Toast.makeText(getContext(), "no internet", Toast.LENGTH_SHORT).show();

        }
    }
}


