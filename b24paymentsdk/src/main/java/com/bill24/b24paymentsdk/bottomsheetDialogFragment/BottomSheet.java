package com.bill24.b24paymentsdk.bottomsheetDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.bill24.b24paymentsdk.R;
import com.bill24.b24paymentsdk.SuccessActivity;
import com.bill24.b24paymentsdk.core.RequestAPI;
import com.bill24.b24paymentsdk.fragment.PaymentMethodFragment;
import com.bill24.b24paymentsdk.helper.ConvertColorHexa;
import com.bill24.b24paymentsdk.helper.CustomSnackbar;
import com.bill24.b24paymentsdk.helper.SharePreferenceCustom;
import com.bill24.b24paymentsdk.helper.Translate;
import com.bill24.b24paymentsdk.model.BankPaymentMethodModel;
import com.bill24.b24paymentsdk.model.BillerModel;
import com.bill24.b24paymentsdk.model.CheckoutDetailModel;
import com.bill24.b24paymentsdk.model.CheckoutPageConfigModel;
import com.bill24.b24paymentsdk.model.TransactionInfoModel;
import com.bill24.b24paymentsdk.model.appearance.darkMode.DarkModeModel;
import com.bill24.b24paymentsdk.model.appearance.lightMode.LightModeModel;
import com.bill24.b24paymentsdk.model.baseResponseModel.BaseResponse;
import com.bill24.b24paymentsdk.model.baseUrl.BaseURL;
import com.bill24.b24paymentsdk.model.conts.Constant;
import com.bill24.b24paymentsdk.model.conts.LanguageCode;
import com.bill24.b24paymentsdk.model.conts.StatusCode;
import com.bill24.b24paymentsdk.model.requestModel.CheckoutDetailRequestModel;
import com.bill24.b24paymentsdk.socketIO.EVentName;
import com.bill24.b24paymentsdk.socketIO.SocketManager;
import com.bill24.b24paymentsdk.socketIO.model.SocketRespModel;
import com.bill24.b24paymentsdk.theme.CustomTheme;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheet extends BottomSheetDialogFragment {

    private CheckoutDetailRequestModel requestModel;
    private FrameLayout progressBarContainer;
    private ProgressBar progressBar;
    private LinearLayoutCompat bottomSheet;
    private View dragHandle;
    private boolean isLightMode;
    private BottomSheetDialog dialog;
    private String tranId,refererKey,language,env, baseUrl="";
    private CheckoutPageConfigModel checkoutPageConfigModel=new CheckoutPageConfigModel();
    public BottomSheet(
            String tranId,
            String refererKey,
            boolean isLightMode){
        this.tranId=tranId;
        this.refererKey=refererKey;
        this.isLightMode=isLightMode;

        if(env==null || env.isEmpty()){
            baseUrl =BaseURL.BASE_URL_DEMO;
        }
        requestModel=new CheckoutDetailRequestModel(this.tranId);
    }

    public BottomSheet(
            String tranId,
            String refererKey,
            String language,
            boolean isLightMode,
            String env){
        this.tranId=tranId;
        this.refererKey=refererKey;
        this.language=language;
        this.isLightMode=isLightMode;
        this.env=env;

        if(env.equals(Constant.DEMO_ENV)){
            baseUrl=BaseURL.BASE_URL_DEMO;
        }else if(env.equals(Constant.STAGING_ENV)){
            baseUrl=BaseURL.BASE_URL_STAGGING;
        }else {
            baseUrl=BaseURL.BASE_URL_DEMO;
        }

        requestModel=new CheckoutDetailRequestModel(this.tranId);
    }
    public BottomSheet(
            String tranId,
            String refererKey,
            boolean isLightMode,
            String env){
        this.tranId=tranId;
        this.refererKey=refererKey;
        this.isLightMode=isLightMode;
        this.env=env;

        if(env.equals(Constant.DEMO_ENV)){
            baseUrl=BaseURL.BASE_URL_DEMO;
        }else if(env.equals(Constant.STAGING_ENV)){
            baseUrl=BaseURL.BASE_URL_STAGGING;
        }else {
            baseUrl=BaseURL.BASE_URL_DEMO;
        }

        requestModel=new CheckoutDetailRequestModel(this.tranId);
    }
    public BottomSheet(
            String tranId,
            String refererKey,
            String language,
            boolean isLightMode
           ){
        this.tranId=tranId;
        this.refererKey=refererKey;
        this.language=language;
        this.isLightMode=isLightMode;


        if(env==null || env.isEmpty()){
            baseUrl =BaseURL.BASE_URL_DEMO;
        }

        requestModel=new CheckoutDetailRequestModel(this.tranId);
    }


    private void initView(View view){
        bottomSheet=view.findViewById(R.id.bottom_sheet);
        progressBarContainer=view.findViewById(R.id.progress_circular);
        dragHandle=view.findViewById(R.id.drag_handle);
        progressBar=view.findViewById(R.id.progress_indicator);
    }


    public void showFragment(Fragment fragment){
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.content_layout,fragment)
                .commit();
    }

    private void showProgressIndicator(){
        int screenHeight=getResources().getDisplayMetrics().heightPixels;
        int newHeight=(int) (screenHeight*0.9);
        FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,newHeight);
        progressBarContainer.setLayoutParams(layoutParams);

        progressBarContainer.setVisibility(View.VISIBLE);
    }
    private void hideProgressIndicator(){
        progressBarContainer.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();

        //connect to socket
        connectSocketIO(tranId);

        postCheckoutDetail();

        if(language==null || language.isEmpty()){
            language= LanguageCode.KH;
        }

        if(isLightMode){
            bottomSheet.setBackground(getContext().getDrawable(R.drawable.bottom_sheet_loding_light_shape));
        }else {
            bottomSheet.setBackground(getContext().getDrawable(R.drawable.bottom_sheet_loading_dark_shape));
        }

    }


    private void connectSocketIO(String roomName){
        SocketManager.connectJoinRoom(roomName);
    }

    private void setSharePreference(
            List<BankPaymentMethodModel> bankPaymentMethodModelList,
            TransactionInfoModel transactionInfoModel,
            CheckoutPageConfigModel checkoutPageConfigModel,
            BillerModel billerModel,
            String language){
        SharedPreferences preferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(Constant.KEY_PAYMENT_METHOD, SharePreferenceCustom.convertListObjectToJson(bankPaymentMethodModelList));
        editor.putString(Constant.KEY_LANGUAGE_CODE,language);
        editor.putString(Constant.KEY_REFERER_KEY,refererKey);
        editor.putString(Constant.KEY_TRANSACTION_INFO,SharePreferenceCustom.convertObjectToJson(transactionInfoModel));
        editor.putString(Constant.KEY_CHECKOUT_PAGE_CONFIG,SharePreferenceCustom.convertObjectToJson(checkoutPageConfigModel));
        editor.putString(Constant.KEY_BILLER,SharePreferenceCustom.convertObjectToJson(billerModel));
        editor.putBoolean(Constant.IS_LIGHT_MODE,isLightMode);
        editor.putString(Constant.BASE_URL_ENV, baseUrl);

        editor.apply();
    }

    private void launchDeeplink(String url){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

         dialog= (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(dialogInterface -> {
            BottomSheetDialog d= (BottomSheetDialog) dialogInterface;

            int alpha = (int) (0.25 * 255);
            int blackColorWith65PercentAlpha = Color.argb(alpha, 0, 0, 0);

            //Set Barrier Color
            d.getWindow().setBackgroundDrawable(new ColorDrawable(blackColorWith65PercentAlpha));

//
            // Change BottomSheet Background Transparent
           FrameLayout bottomSheet=d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
           int transparentColor = Color.argb(0, 0, 0, 0);
           bottomSheet.setBackgroundColor(transparentColor);

            //Set Height to BottomSheet
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = d.getBehavior();
            int screenHeight = getResources().getDisplayMetrics().heightPixels;
            //Set Max Height
            bottomSheetBehavior.setMaxHeight((int)(screenHeight*0.9));
            //Set Height When Dialog Load
            bottomSheetBehavior.setPeekHeight((int) (screenHeight*1)); // Set the initial peek height
            bottomSheetBehavior.setHideable(true);

        });
        return dialog;
    }

    private void broadcastFromSocketServer(){
        Socket socket=SocketManager.getSocket();
        socket.on(EVentName.BROADCAST, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("cccccccc", "call: BroadCast From SerVer"+args);
               String object=args[0].toString();
               SocketRespModel socketRespModel=SharePreferenceCustom.converJsonToObject(object,SocketRespModel.class);
                try {
                    RequestAPI requestAPI=new RequestAPI(refererKey,baseUrl);
                    CheckoutDetailRequestModel request=new CheckoutDetailRequestModel(socketRespModel.getTranNo());
                    Call<BaseResponse<CheckoutDetailModel>> call=requestAPI.postCheckoutDetail(request);
                    call.enqueue(new Callback<BaseResponse<CheckoutDetailModel>>() {
                        @Override
                        public void onResponse(Call<BaseResponse<CheckoutDetailModel>> call, Response<BaseResponse<CheckoutDetailModel>> response) {

                            if(response.isSuccessful()){
                                assert response.body() != null;
                                if(response.body().getData().getTransInfo().getStatus().equals("success")){

                                    if(response.body().getData().getCheckoutPageConfig().isDisplaySuccessPage()){
                                        TransactionInfoModel transactionInfoModel=response.body().getData().getTransInfo();
                                        CheckoutPageConfigModel checkoutPageConfigModel=response.body().getData().getCheckoutPageConfig();
                                        BillerModel billerModel=response.body().getData().getBiller();

                                        //check fragment attach with activity
                                        if(isAdded()){
                                            Intent intent=new Intent(requireActivity(), SuccessActivity.class);
                                            intent.putExtra(Constant.KEY_LANGUAGE_CODE,transactionInfoModel.getLanguage());
                                            intent.putExtra(Constant.IS_LIGHT_MODE,isLightMode);
                                            intent.putExtra(Constant.KEY_TRANSACTION_INFO,transactionInfoModel);
                                            intent.putExtra(Constant.KEY_CHECKOUT_PAGE_CONFIG,checkoutPageConfigModel);
                                            intent.putExtra(Constant.KEY_BILLER,billerModel);

                                            startActivity(intent);

                                            dialog.dismiss();
                                        }
                                    }

                                    else {
//                                        launchDeeplink();
                                        //todo open redirect screen
                                    }

                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse<CheckoutDetailModel>> call, Throwable t) {

                        }
                    });

                }catch (Exception e){
                    Log.d("broadcasterror", "call: "+e.getMessage());
                }
            }
        });
    }

    public void setRadiusCorner(Context context, ViewGroup viewGroup, float cornerRadiusDP, CheckoutPageConfigModel checkoutPageConfigModel) {
        int cornerRadiusPX = (int) (cornerRadiusDP * context.getResources().getDisplayMetrics().density);

        float[] outerRadii = new float[] {
                cornerRadiusPX, cornerRadiusPX, cornerRadiusPX, cornerRadiusPX, 0, 0, 0, 0
        };


        if(isLightMode){
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(outerRadii, null, null));
            String bottomSheetColor=checkoutPageConfigModel.getAppearance().getLightMode().getSecondaryColor().getBackgroundColor();
            String bottomSheetHexa= ConvertColorHexa.convertHex(bottomSheetColor);


            shapeDrawable.getPaint().setColor(Color.parseColor(bottomSheetHexa)); // Set your background color here
            viewGroup.setBackground(shapeDrawable);
        }else {
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(outerRadii, null, null));
            String bottomSheetColor=checkoutPageConfigModel.getAppearance().getDarkMode().getSecondaryColor().getBackgroundColor();
            String bottomSheetHexa= ConvertColorHexa.convertHex(bottomSheetColor);
            shapeDrawable.getPaint().setColor(Color.parseColor(bottomSheetHexa)); // Set your background color here
            viewGroup.setBackground(shapeDrawable);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_sheet_layout,container,false);

        initView(view);

        showProgressIndicator();

//        ShapeDrawable shapeDrawable=CustomShape.applyShape(Color.WHITE,25);
//        view.setBackground(shapeDrawable);

        return view;
    }


    private void createDraghanle(int colorCode){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setTint(colorCode);
        gradientDrawable.setSize(82,5);// Set the stroke color and width
        gradientDrawable.setCornerRadius(20); // Set the corner radius
        gradientDrawable.setDither(true);

        dragHandle.setBackground(gradientDrawable);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //wait broadcast from server
        broadcastFromSocketServer();

    }

    private void postCheckoutDetail(){
        RequestAPI requestAPI=new RequestAPI(refererKey,baseUrl);
        Call<BaseResponse<CheckoutDetailModel>> call=requestAPI.postCheckoutDetail(requestModel);
        call.enqueue(new Callback<BaseResponse<CheckoutDetailModel>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<CheckoutDetailModel>> call, @NonNull Response<BaseResponse<CheckoutDetailModel>> response) {

                if(response.isSuccessful()){
                    assert response.body() != null;
                    if(response.body().getCode().equals(StatusCode.SUCCESS)){
                        List<BankPaymentMethodModel> bankPaymentMethodModelList=
                                (   response.body() !=null &&
                                        response.body().getData() !=null &&
                                        response.body().getData().getTransInfo() !=null &&
                                        response.body().getData().getTransInfo().getBankPaymentMethod() !=null
                                ) ?

                                        response.body().getData().getTransInfo().getBankPaymentMethod() : new ArrayList<>();

                        BillerModel billerModel=
                                (
                                        response.body()!=null &&
                                                response.body().getData() !=null &&
                                                response.body().getData().getBiller() !=null
                                ) ?
                                        response.body().getData().getBiller():new BillerModel();

                        //TransactionInfo
                        TransactionInfoModel transactionInfoModel=
                                (
                                        response.body() !=null &&
                                                response.body().getData() !=null &&
                                                response.body().getData().getTransInfo()!=null
                                ) ?
                                        response.body().getData().getTransInfo() : new TransactionInfoModel();

                        //CheckoutPageConfig
                        checkoutPageConfigModel=
                                (
                                        response.body()!=null &&
                                                response.body().getData() !=null &&
                                                response.body().getData().getCheckoutPageConfig() !=null
                                ) ?
                                        response.body().getData().getCheckoutPageConfig() : new CheckoutPageConfigModel();




                        //set bottomsheet radius
                        setRadiusCorner(getContext(),bottomSheet,12,checkoutPageConfigModel);


                        //set getlanuage
                        if(language == null || language.isEmpty()){
                            language=transactionInfoModel.getLanguage();
                        }

                        //set drag handle
                        if(isLightMode){
                            LightModeModel lightModeModel=checkoutPageConfigModel.getAppearance().getLightMode();
                            String dragHandle=lightModeModel.getIndicatorColor();
                            String dragHanleHexa=ConvertColorHexa.convertHex(dragHandle);

                            createDraghanle(Color.parseColor(dragHanleHexa));
                        }else {
                            DarkModeModel darkModeModel=checkoutPageConfigModel.getAppearance().getDarkMode();
                            String dragHandle=darkModeModel.getIndicatorColor();
                            String dragHanleHexa=ConvertColorHexa.convertHex(dragHandle);
                            createDraghanle(Color.parseColor(dragHanleHexa));
                        }

                        if(!bankPaymentMethodModelList.isEmpty()){
                            setSharePreference(bankPaymentMethodModelList,transactionInfoModel,checkoutPageConfigModel,billerModel,language);//Store value in sharePreference
                            showFragment(new PaymentMethodFragment());//Go to Fragment PaymentMethod
                        }

                        new Handler().postDelayed(() -> {
                            hideProgressIndicator();//Hide Progress Indicator
                        },100);

                        Log.d("checkoutDetail", "onResponse: "+transactionInfoModel.getKhqrString());

                    }else {

                        String message;
                        if(language.equals(LanguageCode.EN)){
                            message= Translate.ERR_SERVER_EN;
                        }else {
                            message=Translate.ERR_SERVER_KM;
                        }

                        CustomSnackbar.showSuccessSnackbar(
                                getContext(),
                                getView().findViewById(R.id.snackar_bottomsheet_container),
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
            public void onFailure(@NonNull Call<BaseResponse<CheckoutDetailModel>> call, @NonNull Throwable t) {

                String message;
                if(language.equals(LanguageCode.EN)){
                    message= Translate.ERR_SERVER_EN;
                }else {
                    message=Translate.ERR_SERVER_KM;
                }

                CustomSnackbar.showSuccessSnackbar(
                        getContext(),
                        getView().findViewById(R.id.snackar_bottomsheet_container),
                                R.drawable.error_24px,
                                message,
                        R.color.snackbar_background_error_color,
                        Snackbar.LENGTH_LONG,
                        language
                                );


                //Toast.makeText(getContext(),"Error internal server",Toast.LENGTH_LONG).show();
                Log.d("bottomSheet", "onFailure: "+t.getMessage());

                Handler handler=new Handler();
                handler.postDelayed(() -> progressBar.setVisibility(View.GONE),4000);

            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        SocketManager.disConnect();//Disconnect from Socket server

    }
}
