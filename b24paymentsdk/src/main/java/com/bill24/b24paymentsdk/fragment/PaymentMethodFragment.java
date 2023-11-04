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
import com.bill24.b24paymentsdk.helper.CustomSnackbar;
import com.bill24.b24paymentsdk.helper.SetFont;
import com.bill24.b24paymentsdk.helper.SharePreferenceCustom;
import com.bill24.b24paymentsdk.helper.StickyHeaderItemDecoration;
import com.bill24.b24paymentsdk.helper.Translate;
import com.bill24.b24paymentsdk.helper.translateLanguage.TranslateLanguage;
import com.bill24.b24paymentsdk.model.BankPaymentMethodItemModel;
import com.bill24.b24paymentsdk.model.BankPaymentMethodModel;
import com.bill24.b24paymentsdk.model.BillerModel;
import com.bill24.b24paymentsdk.model.CheckoutPageConfigModel;
import com.bill24.b24paymentsdk.model.ExpiredTransactionModel;
import com.bill24.b24paymentsdk.model.GenerateLinkDeepLinkModel;
import com.bill24.b24paymentsdk.model.TransactionInfoModel;
import com.bill24.b24paymentsdk.model.baseResponseModel.BaseResponse;
import com.bill24.b24paymentsdk.model.conts.Bank;
import com.bill24.b24paymentsdk.model.conts.Constant;
import com.bill24.b24paymentsdk.model.conts.LanguageCode;
import com.bill24.b24paymentsdk.model.conts.StatusCode;
import com.bill24.b24paymentsdk.model.requestModel.ExpiredRequestModel;
import com.bill24.b24paymentsdk.model.requestModel.GenerateDeeplinkRequestModel;
import com.bill24.b24paymentsdk.theme.CustomTheme;
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.snackbar.Snackbar;

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
            textTotalAmountTitle, textBill24,textPowerby;
    private MaterialDivider verticalDivider;
    private LinearLayoutCompat containerPaymentMethod;
    private FrameLayout bottomPaymentContainer,bottomDashLine;
    private List<BankPaymentMethodModel> bankPaymentMethodModelList;
    private TransactionInfoModel transactionInfoModel=new TransactionInfoModel();
    private CheckoutPageConfigModel checkoutPageConfigModel=new CheckoutPageConfigModel();
    private String refererKey,language,baseUrl;
    private boolean isLightMode;
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
        textBill24 =view.findViewById(R.id.bill24);
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
            textBill24.setVisibility(View.VISIBLE);
            textPowerby.setVisibility(View.VISIBLE);
            verticalDivider.setVisibility(View.VISIBLE);

        }else {
            textBill24.setVisibility(View.GONE);
            textPowerby.setVisibility(View.GONE);
            verticalDivider.setVisibility(View.GONE);
        }
    }

    private void translateLanguage(){
        TranslateLanguage tranLang=TranslateLanguage.translateLanguage(language);
        textPaymentMethod.setText(tranLang.getPaymentMethod());
        textTotalAmountTitle.setText(tranLang.getTotalAmount());
    }

    private void updateFont(){
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(getContext(),language);

        textPaymentMethod.setTypeface(typeface);
        textPaymentMethod.setTextSize(16);
        textPaymentMethod.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        //textPaymentMethod.setTextColor(ContextCompat.getColor(getContext(),R.color.header_font_color));

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
            StickyHeaderItemDecoration stickyHeaderItemDecoration=new StickyHeaderItemDecoration(adapter);
            recyclerViewPaymentMethod.addItemDecoration(stickyHeaderItemDecoration);
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayOsVersion(){

           // PackageInfo packageInfo=getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),0);
            //String version=packageInfo.versionName;
            //int versionCode=packageInfo.versionCode;
            textBill24.setText(Constant.BILL24);
            textVersion.setText(Constant.VERSION);
    }
    private void postExpiredTran(ExpiredRequestModel model){
        RequestAPI requestAPI=new RequestAPI(refererKey,baseUrl);
        Call<BaseResponse<ExpiredTransactionModel>> call=requestAPI.postExpireTran(model);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<ExpiredTransactionModel>> call, @NonNull Response<BaseResponse<ExpiredTransactionModel>> response) {

            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<ExpiredTransactionModel>> call, @NonNull Throwable t) {

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

    private void applyTheme(){
        CustomTheme customTheme=CustomTheme.getThemeFromAPI(isLightMode,checkoutPageConfigModel);

        //header
        textPaymentMethod.setTextColor(Color.parseColor(customTheme.getLabelTextColor()));

        //recyclerview
        recyclerViewPaymentMethod.setBackgroundColor(Color.parseColor(customTheme.getSecondaryBackgroundColor()));

        //bottom
        bottomPaymentContainer.setBackgroundColor(Color.parseColor(customTheme.getSecondaryBackgroundColor()));
        textTotalAmountTitle.setTextColor(Color.parseColor(customTheme.getSecondaryTextColor()));
        textPowerby.setTextColor(Color.parseColor(customTheme.getSecondaryTextColor()));
        textBill24.setTextColor(Color.parseColor(customTheme.getPrimaryTextColor()));
        verticalDivider.setDividerColor(Color.parseColor(customTheme.getPrimaryTextColor()));
        textVersion.setTextColor(Color.parseColor(customTheme.getPrimaryTextColor()));

        //total amount currency
        textTotalAmountTitle.setTextColor(Color.parseColor(customTheme.getSecondaryTextColor()));
        textTotalAmount.setTextColor(Color.parseColor(customTheme.getLabelTextColor()));
        textCurrency.setTextColor(Color.parseColor(customTheme.getLabelTextColor()));

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        int width=(int) (1 * getContext().getResources().getDisplayMetrics().density);
        int dashWidthHeight=(int)(5*getContext().getResources().getDisplayMetrics().density);
        gradientDrawable.setStroke(width,
                Color.parseColor(customTheme.getIndicatorColor()),
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

        //apply theme
        applyTheme();

        //translate
        translateLanguage();

        //set container of webview height to 90%
        int screenHeight=getResources().getDisplayMetrics().heightPixels;
        int newHeight=(int) (screenHeight*0.9);
        LinearLayoutCompat.LayoutParams layoutParams=new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,newHeight);
        containerPaymentMethod.setLayoutParams(layoutParams);

        //show hide bill24
        toggleBill24();

        textBill24.setOnClickListener(v->{
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

                String message;
                if(language.equals(LanguageCode.EN)){
                    message=Translate.ERR_SERVER_EN;
                }else {
                    message=Translate.ERR_SERVER_KM;
                }
                CustomSnackbar.showSuccessSnackbar(
                        getContext(),
                        getView().findViewById(R.id.snackbar_payment_method),
                        R.drawable.error_24px,
                        message,
                        R.color.snackbar_background_error_color,
                        Snackbar.LENGTH_LONG,
                        language
                );


                Intent intent=new Intent(requireActivity(), SuccessActivity.class);
                intent.putExtra(Constant.KEY_LANGUAGE_CODE,language);
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
                call.enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<BaseResponse<GenerateLinkDeepLinkModel>> call, @NonNull Response<BaseResponse<GenerateLinkDeepLinkModel>> response) {
                        GenerateLinkDeepLinkModel generateLinkDeepLinkModel;

                        if (response.isSuccessful()) {
                            BaseResponse<GenerateLinkDeepLinkModel> deeplink = response.body();
                            assert deeplink != null;
                            if (deeplink.getCode().equals(StatusCode.SUCCESS)) {
                                generateLinkDeepLinkModel = deeplink.getData();
                                if (!generateLinkDeepLinkModel.getMobileDeepLink().isEmpty()) {
                                    launchDeeplink(generateLinkDeepLinkModel.getMobileDeepLink());
                                    return;
                                }
                                if (!generateLinkDeepLinkModel.getWebPaymentLink().isEmpty()) {
                                    ((BottomSheet) getParentFragment()).showFragment(new WebViewCheckoutFragment(generateLinkDeepLinkModel.getWebPaymentLink()));
                                }
                            } else {
                                String message;
                                if (language.equals(LanguageCode.EN)) {
                                    message = Translate.ERR_SERVER_EN;
                                } else {
                                    message = Translate.ERR_SERVER_KM;
                                }

                                CustomSnackbar.showSuccessSnackbar(
                                        getContext(),
                                        getView().findViewById(R.id.snackbar_payment_method),
                                        R.drawable.error_24px,
                                        message,
                                        R.color.snackbar_background_error_color,
                                        Snackbar.LENGTH_LONG,
                                        language
                                );
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<BaseResponse<GenerateLinkDeepLinkModel>> call, @NonNull Throwable t) {
                        Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
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


