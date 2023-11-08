package com.bill24.b24paymentsdk.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bill24.b24paymentsdk.R;
import com.bill24.b24paymentsdk.bottomsheetDialogFragment.BottomSheet;
import com.bill24.b24paymentsdk.core.RequestAPI;
import com.bill24.b24paymentsdk.customShapeDrawable.CustomShape;
import com.bill24.b24paymentsdk.customShapeDrawable.SelectedState;
import com.bill24.b24paymentsdk.helper.ChangLanguage;
import com.bill24.b24paymentsdk.helper.ConvertColorHexa;
import com.bill24.b24paymentsdk.helper.CustomSnackbar;
import com.bill24.b24paymentsdk.helper.SetFont;
import com.bill24.b24paymentsdk.helper.SharePreferenceCustom;
import com.bill24.b24paymentsdk.helper.Translate;
import com.bill24.b24paymentsdk.helper.translateLanguage.TranslateLanguage;
import com.bill24.b24paymentsdk.model.BillerModel;
import com.bill24.b24paymentsdk.model.CheckoutPageConfigModel;
import com.bill24.b24paymentsdk.model.ExpiredTransactionModel;
import com.bill24.b24paymentsdk.model.TransactionInfoModel;
import com.bill24.b24paymentsdk.model.appearance.darkMode.DarkModeModel;
import com.bill24.b24paymentsdk.model.appearance.lightMode.LightModeModel;
import com.bill24.b24paymentsdk.model.baseResponseModel.BaseResponse;
import com.bill24.b24paymentsdk.model.conts.Constant;
import com.bill24.b24paymentsdk.model.conts.CurrencyCode;
import com.bill24.b24paymentsdk.model.conts.LanguageCode;
import com.bill24.b24paymentsdk.model.conts.StatusCode;
import com.bill24.b24paymentsdk.model.requestModel.ExpiredRequestModel;
import com.bill24.b24paymentsdk.theme.CustomTheme;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhqrFragment extends Fragment {
    private AppCompatTextView textMerchantName,
            textAmount,textCurrency,textCountDownTime,
            textScanToPay,textDownload,textShare,textOr;
    private AppCompatImageView khqrImage,khqrCurrencyIcon,imageDownload,imageShare;
    private FrameLayout downloadContainer,shareContainer,khqrLoading,
            khqrCardContainer,containerQrCode;
    private RelativeLayout containerLoadingTime;
    private BillerModel billerModel;
    private TransactionInfoModel transactionInfoModel;
    private CheckoutPageConfigModel checkoutPageConfigModel;
    private AppCompatImageView dashLineLeft,dashLineRight;
    private String transactionId,refererKey,language,baseUrl;
    private CoordinatorLayout khqrContainer;
    private View khqrBackground;
    private  boolean isLightMode;
    private ProgressBar khqrProgressBar;
    public KhqrFragment(String transactionId){
        this.transactionId=transactionId;
    }

    private void initView(View view){
        textMerchantName=view.findViewById(R.id.text_khqr_merchant_name);
        textAmount=view.findViewById(R.id.text_khqr_amount);
        textCurrency=view.findViewById(R.id.text_khqr_currency);
        textCountDownTime=view.findViewById(R.id.text_timer);
        khqrImage=view.findViewById(R.id.khqr_image);
        khqrCurrencyIcon=view.findViewById(R.id.khqr_currency_icon);
        textScanToPay=view.findViewById(R.id.text_scan_to_pay);
        textDownload=view.findViewById(R.id.text_download);
        textShare=view.findViewById(R.id.text_share);
        textOr=view.findViewById(R.id.text_or);
        downloadContainer=view.findViewById(R.id.download_container);
        shareContainer=view.findViewById(R.id.share_container);
        khqrLoading=view.findViewById(R.id.khqr_loading);
        containerLoadingTime=view.findViewById(R.id.container_loading_time);
        khqrContainer=view.findViewById(R.id.container_khqrfragment);
        imageDownload=view.findViewById(R.id.image_download);
        imageShare=view.findViewById(R.id.image_share);
        dashLineLeft=view.findViewById(R.id.or_dash_line_left);
        dashLineRight=view.findViewById(R.id.or_dash_line_right);
        khqrCardContainer=view.findViewById(R.id.khqr_container);
        khqrBackground=view.findViewById(R.id.khqr_background_color);
        containerQrCode=view.findViewById(R.id.container_qr_code);
        khqrProgressBar=view.findViewById(R.id.khrq_progress_bar);

    }

    private void updateFont(){
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(getContext(),language);
        textScanToPay.setTypeface(typeface);
        textScanToPay.setTextSize(13);
        textShare.setTypeface(typeface);
        textShare.setTextSize(12);
        textDownload.setTypeface(typeface);
        textDownload.setTextSize(12);
        textOr.setTypeface(typeface);
        textOr.setTextSize(11);
    }

    private void bindData(){
        textMerchantName.setText(billerModel.getBillerDisplayName());
        textAmount.setText(transactionInfoModel.getTotalAmountDisplay());
        textCurrency.setText(transactionInfoModel.getCurrency());
        //load image
        Picasso.get().load(transactionInfoModel.getKhqrImage()).into(khqrImage);
        if(transactionInfoModel.getCurrency().equals(CurrencyCode.USD)){
            khqrCurrencyIcon.setImageResource(R.drawable.usd_khqr_logo);
        }else if(transactionInfoModel.getCurrency().equals(CurrencyCode.KHR)){
            khqrCurrencyIcon.setImageResource(R.drawable.khr_khqr_logo);
        }
    }
    private void countTime(long timeMillisecond){
        CountDownTimer countDownTimer=new CountDownTimer(timeMillisecond,1000){
            @Override
            public void onTick(long l) {
                long minute=l/1000/60;
                long second=(l/1000)%60;
                String timeLeftFormat=String.format(Locale.getDefault(),"%02d:%02d",minute,second);
                textCountDownTime.setText(timeLeftFormat);
            }

            @Override
            public void onFinish() {
                Fragment fragment=getParentFragment();
                if(fragment !=null && fragment instanceof BottomSheet){
                    ((BottomSheet)getParentFragment()).showFragment(new ExpireFragment(transactionId));
                }
            }
        };
        countDownTimer.start();
    }

    private void getSharePreference(){
        SharedPreferences preferences= getActivity().getPreferences(Context.MODE_PRIVATE);
        if(preferences!=null){
            String transactionInfoJson=preferences.getString(Constant.KEY_TRANSACTION_INFO,"");
            transactionInfoModel= SharePreferenceCustom.converJsonToObject(transactionInfoJson, TransactionInfoModel.class);
            //get language
            language=preferences.getString(Constant.KEY_LANGUAGE_CODE,"");
            //get refererKey
            refererKey=preferences.getString(Constant.KEY_REFERER_KEY,"");

            //checkout page config
            String checkoutPageConfigJson=preferences.getString(Constant.KEY_CHECKOUT_PAGE_CONFIG,"");
            checkoutPageConfigModel=SharePreferenceCustom.converJsonToObject(checkoutPageConfigJson, CheckoutPageConfigModel.class);

            //biller
            String billerJson=preferences.getString(Constant.KEY_BILLER,"");
            billerModel=SharePreferenceCustom.converJsonToObject(billerJson, BillerModel.class);


            //get isLight mode
            isLightMode=preferences.getBoolean(Constant.IS_LIGHT_MODE,true);

            //get base url
            baseUrl=preferences.getString(Constant.BASE_URL_ENV,"");

        }

    }

    private void postExtendExpiredTime(View view){
        if(isLightMode){
            LightModeModel lightModeModel=checkoutPageConfigModel.getAppearance().getLightMode();
            String khqrLoadingColor=lightModeModel.getSecondaryColor().getBackgroundColor();
            String khqrLoadingHexa=ConvertColorHexa.convertHex(khqrLoadingColor);
            khqrLoading.setBackgroundColor(Color.parseColor(khqrLoadingHexa));
            khqrLoading.setVisibility(View.VISIBLE);
        }else {

            DarkModeModel darkModeModel=checkoutPageConfigModel.getAppearance().getDarkMode();
            String khqrLoadingColor=darkModeModel.getSecondaryColor().getBackgroundColor();
            String khqrLoadingHexa=ConvertColorHexa.convertHex(khqrLoadingColor);
            khqrLoading.setBackgroundColor(Color.parseColor(khqrLoadingHexa));
            khqrLoading.setVisibility(View.VISIBLE);

        }

        RequestAPI requestAPI=new RequestAPI(refererKey,baseUrl);
        ExpiredRequestModel requestModel=new ExpiredRequestModel(transactionId);
        Call<BaseResponse<ExpiredTransactionModel>> call =requestAPI.postExpireTran(requestModel);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<ExpiredTransactionModel>> call, @NonNull Response<BaseResponse<ExpiredTransactionModel>> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getCode().equals(StatusCode.SUCCESS)) {
                        khqrLoading.setVisibility(View.GONE);
                        BaseResponse<ExpiredTransactionModel> expiredTran = response.body();
                        String expiredTime = expiredTran.getData().getExpiredDate();
                        //String expiredTime="2023-10-26 15:54:20";
                        try {
                            @SuppressLint("SimpleDateFormat")
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date expiredDate = dateFormat.parse(expiredTime);
                            Date currentDate = new Date();
                            assert expiredDate != null;
                            long timeDifferenceMillisecond = expiredDate.getTime() - currentDate.getTime();

                            if (timeDifferenceMillisecond > 1800000) {//30 minutes
                                containerLoadingTime.setVisibility(View.INVISIBLE);
                            } else {
                                containerLoadingTime.setVisibility(View.VISIBLE);
                            }

                            countTime(timeDifferenceMillisecond);

                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        String saveSuccess;
                        if (language.equals(LanguageCode.EN)) {
                            saveSuccess = Translate.ERR_SERVER_EN;

                        } else {
                            saveSuccess = Translate.ERR_SERVER_KM;
                        }

                        //hide progress bar
                        khqrProgressBar.setVisibility(View.GONE);

                        CustomSnackbar.showSuccessSnackbar(getContext(),
                                view.findViewById(R.id.container_khqrfragment),
                                R.drawable.error_24px,
                                saveSuccess, R.color.snackbar_background_error_color,
                                Snackbar.LENGTH_LONG
                                , language);
                    }
                } else {
                    Log.d("extend time", "onResponse: " + response.code());
                    //Toast.makeText(getContext(),"Code : "+response.code(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<ExpiredTransactionModel>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Bitmap convertLayoutToImage(View view){
        AppCompatTextView merchantName=view.findViewById(R.id.text_khqr_merchant_name);
        AppCompatTextView amount=view.findViewById(R.id.text_khqr_amount);
        AppCompatImageView qrcodeImage=view.findViewById(R.id.khqr_image_download_share);
        AppCompatImageView khqrCurrencyLogo=view.findViewById(R.id.khqr_currency_icon_download_share);
        AppCompatTextView currency=view.findViewById(R.id.text_khqr_currency);

        merchantName.setText(billerModel.getBillerDisplayName());
        amount.setText(transactionInfoModel.getTotalAmountDisplay());
        if(transactionInfoModel.getCurrency().equals(CurrencyCode.KHR)){
            currency.setText(transactionInfoModel.getCurrency());
            khqrCurrencyLogo.setImageResource(R.drawable.khr_khqr_logo);
        }
        else if(transactionInfoModel.getCurrency().equals(CurrencyCode.USD)){
            currency.setText(transactionInfoModel.getCurrency());
            khqrCurrencyLogo.setImageResource(R.drawable.usd_khqr_logo);
        }
        //load image from internet
       Picasso.get().load(transactionInfoModel.getKhqrImage()).into(qrcodeImage);

        view.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());
        return  Bitmap.createBitmap(view.getMeasuredWidth(),view.getMeasuredHeight(),Bitmap.Config.ARGB_8888);
    }


//    private void  customSnackBar(View view,int image,String desc){
//        Snackbar customSnackbar = Snackbar.make(view.findViewById(R.id.container_khqrfragment), "",Snackbar.LENGTH_SHORT);
//
//        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) customSnackbar.getView();
//        View customView = getLayoutInflater().inflate(R.layout.snackbar_success_custom_layout, null);
//        customView.setBackgroundColor(Color.BLUE);
//        snackbarLayout.setBackgroundColor(getContext().getColor(R.color.snackbar_background_color));//remove snackbar background
//        snackbarLayout.addView(customView);
//
//        //update font family
//            SetFont font=new SetFont();
//            Typeface typeface=font.setFont(getContext(),language);
//
//
//
//        // Customize the content and appearance of the custom layout
//            AppCompatTextView textView = customView.findViewById(R.id.custom_snackbar_desc);
//            textView.setTypeface(typeface);
//            textView.setText(desc);
//
//            AppCompatImageView imageView=customView.findViewById(R.id.custom_snackbar_icon);
//            imageView.setImageResource(image);
//
//        customSnackbar.show();
//    }

    private void downloadKHQR(Bitmap bitmap,View view){

        String imageTitle=billerModel.getBillerName()+"-"+transactionInfoModel.getTranAmountDisplay()+" "+transactionInfoModel.getCurrency();
        String imageUrl= MediaStore.Images.Media.insertImage(requireActivity().getContentResolver(),bitmap,imageTitle,"");

        String saveSuccess;
        if(language.equals(LanguageCode.EN)){
            saveSuccess=Translate.KHQR_SAVE_MESSAGE_EN;

        }else {
            saveSuccess=Translate.KHQR_SAVE_MESSAGE_KM;
        }


        if(imageUrl!=null){
            CustomSnackbar.showSuccessSnackbar(getContext(),
                    view.findViewById(R.id.container_khqrfragment),
                    R.drawable.check_circle_24px,
                    saveSuccess,R.color.snackbar_background_success_color,
                    Snackbar.LENGTH_SHORT
                    ,language);
        }
//        else {
//            CustomSnackbar.showSuccessSnackbar(
//                    getContext(),
//                    view.findViewById(R.id.container_khqrfragment),
//                    R.drawable.error_24px,
//                    saveUnSuceess,
//                    R.color.snackbar_background_error_color,
//                    Snackbar.LENGTH_SHORT,
//                    language
//            );
//        }
    }
    private void shareKHQR(Bitmap bitmap){
        File tempFile;

        String fileName=billerModel.getBillerName()+"-"+transactionInfoModel.getTranAmountDisplay()+" "+transactionInfoModel.getCurrency();
        try {
            //tempFile = File.createTempFile(fileName, ".png");
            tempFile=new File(getContext().getCacheDir(),fileName +".png");
            FileOutputStream fos = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            Uri uri = FileProvider.getUriForFile(getContext(), Constant.AUTHORITY, tempFile);

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(Intent.createChooser(shareIntent, "Share KHQR"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void  applyThemeShape(){
        CustomTheme customTheme=CustomTheme.getThemeFromAPI(isLightMode,checkoutPageConfigModel);

        khqrContainer.setBackgroundColor(Color.parseColor(customTheme.getSecondaryBackgroundColor()));
        textScanToPay.setTextColor(Color.parseColor(customTheme.getPrimaryTextColor()));
        textOr.setTextColor(Color.parseColor(customTheme.getPrimaryTextColor()));

        //dash line
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.LINE);
        int width=(int)(1*getContext().getResources().getDisplayMetrics().density);
        int dashWidthHeight=(int)(5*getContext().getResources().getDisplayMetrics().density);

        gradientDrawable.setStroke(width,
                Color.parseColor(customTheme.getIndicatorColor()),
                dashWidthHeight,
                dashWidthHeight); // Set the stroke color and width// Set the corner radius

        dashLineLeft.setBackground(gradientDrawable);
        dashLineRight.setBackground(gradientDrawable);

        //count time
        textCountDownTime.setTextColor(Color.parseColor(customTheme.getSecondaryTextColor()));

        //download share
        ShapeDrawable normalShape=CustomShape.applyShape(
                Color.parseColor(customTheme.getActionButtonBackgroundColor()),
                        10,getContext());

        String selectorColor=ConvertColorHexa.getFiftyPercentColor(
                customTheme.getActionButtonBackgroundColor());

//        ShapeDrawable selectorShape=CustomShape.applyShape(
//                Color.parseColor(selectorColor),
//                10,getContext()
//        );

        StateListDrawable selectorDownload=SelectedState.selectedSate(normalShape,normalShape);
        downloadContainer.setBackground(selectorDownload);
        StateListDrawable selectorShare=SelectedState.selectedSate(normalShape,normalShape);
        shareContainer.setBackground(selectorShare);

        ColorFilter colorFilter=new PorterDuffColorFilter(
                Color.parseColor(customTheme.getActionButtonTextColor()),
                PorterDuff.Mode.SRC_ATOP);
        imageShare.setColorFilter(colorFilter);
        imageDownload.setColorFilter(colorFilter);
        textDownload.setTextColor(Color.parseColor(customTheme.getPrimaryTextColor()));
        textShare.setTextColor(Color.parseColor(customTheme.getPrimaryTextColor()));

    }

    private void translateLanguage(){
        TranslateLanguage translateLanguage=TranslateLanguage.translateLanguage(language);
            textDownload.setText(translateLanguage.getDownload());
            textScanToPay.setText(translateLanguage.getScanToPay());
            textShare.setText(translateLanguage.getShare());
            textOr.setText(translateLanguage.getOr());
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSharePreference();

        ChangLanguage.setLanguage(language,getContext());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.khqr_fragment_layout,container,false);
        initView(view);
        bindData();

        translateLanguage();
        //Update Font
        updateFont();



        //apply theme shape
        applyThemeShape();


        //apply khqr card corner
        ShapeDrawable khqrCard=CustomShape.applyShape(Color.WHITE,30,getContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            int shadowColor=Color.argb(10,0,0,0);
            int shadowPX=(int) (shadowColor*getContext().getResources().getDisplayMetrics().density);
            khqrCardContainer.setOutlineSpotShadowColor(shadowPX);
        }
        int elevation=(int)(40 * getContext().getResources().getDisplayMetrics().density);
        khqrCardContainer.setElevation(elevation);
        khqrCardContainer.setBackground(khqrCard);

        ShapeDrawable containerQrcode=CustomShape.applyShape(Color.WHITE,30,getContext());
        containerQrCode.setBackground(containerQrcode);

        ShapeDrawable khqrBg=CustomShape.applyShape(
                ContextCompat.getColor(getContext(),R.color.khqr_backgound_color),30,getContext());
        khqrBackground.setBackground(khqrBg);


        postExtendExpiredTime(view);//get expired date from api

        // get layout to conver to image
        View layoutImage=getLayoutInflater().inflate(R.layout.download_share_card_khqr_image_layout,null);
        downloadContainer.setOnClickListener(v->{
             Bitmap bitmap=convertLayoutToImage(layoutImage);
             Canvas canvas=new Canvas(bitmap);
             layoutImage.draw(canvas);

             //Save Image into Gallerry
             downloadKHQR(bitmap,view);

        });

        shareContainer.setOnClickListener(v->{
            Bitmap bitmap=convertLayoutToImage(layoutImage);
            Canvas canvas=new Canvas(bitmap);
            layoutImage.draw(canvas);

            shareKHQR(bitmap);

        });

        return view;
    }


}
