package com.bill24.b24paymentsdk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bill24.b24paymentsdk.customShapeDrawable.CustomShape;
import com.bill24.b24paymentsdk.customShapeDrawable.SelectedState;
import com.bill24.b24paymentsdk.helper.ChangLanguage;
import com.bill24.b24paymentsdk.helper.ConvertColorHexa;
import com.bill24.b24paymentsdk.helper.SetFont;
import com.bill24.b24paymentsdk.helper.Translate;
import com.bill24.b24paymentsdk.helper.translateLanguage.TranslateLanguage;
import com.bill24.b24paymentsdk.model.BillerModel;
import com.bill24.b24paymentsdk.model.CheckoutPageConfigModel;
import com.bill24.b24paymentsdk.model.TransactionInfoModel;
import com.bill24.b24paymentsdk.model.conts.Constant;
import com.bill24.b24paymentsdk.model.conts.LanguageCode;
import com.bill24.b24paymentsdk.theme.CustomTheme;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SuccessActivity extends AppCompatActivity {

    private AppCompatTextView
            textInvoiceAlreadyPaid, textTranNoTitle,
            textBankRefTitle, textToMerchantTitle, textTranDateTitle,
            textTotalTitle,
            textTranNo, textBankRef, textToMerchant,
    textTranDate, textTotalAmount,textDownload,textShare,textDone,textCurrency;

    private View dashLineFirst,dashLineSecond,dashLineThird;
    private LinearLayoutCompat successContainer;
    private FrameLayout downloadContainer,shareContainer,buttonDoneContainer;
    private TransactionInfoModel transactionInfoModel;
    private BillerModel billerModel=new BillerModel();
    private CheckoutPageConfigModel checkoutPageConfigModel;
    private String language;
    private boolean isLightMode;
    private AppCompatImageView imageShare,imageDownload;
    private LinearLayoutCompat successActivityContainer;

    private void initView(){
        textInvoiceAlreadyPaid=findViewById(R.id.text_invoice_already_paid_suceess);
        textTranNoTitle=findViewById(R.id.text_tran_no_title_success);
        textBankRefTitle=findViewById(R.id.text_bank_ref_title_success);
        textToMerchantTitle=findViewById(R.id.text_to_merchant_title_success);
        textTranDateTitle=findViewById(R.id.text_tran_date_title_success);
        textTotalTitle=findViewById(R.id.text_total_title_success);
        textTranNo=findViewById(R.id.text_tran_no_success);
        textBankRef=findViewById(R.id.text_bank_ref_success);
        textToMerchant=findViewById(R.id.text_to_merchant_success);
        textTranDate=findViewById(R.id.text_tran_date_success);
        textTotalAmount=findViewById(R.id.text_total_amount_success);
        textDownload=findViewById(R.id.text_download_success);
        textShare=findViewById(R.id.text_share_success);
        dashLineFirst=findViewById(R.id.dash_line_first_success);
        dashLineSecond=findViewById(R.id.dash_lin_second_success);
        dashLineThird=findViewById(R.id.dash_line_third_success);
        successContainer=findViewById(R.id.success_container);
        downloadContainer=findViewById(R.id.download_container_suceess);
        shareContainer=findViewById(R.id.share_container_success);
        buttonDoneContainer=findViewById(R.id.button_done_success);
        imageDownload=findViewById(R.id.image_download_success);
        imageShare=findViewById(R.id.image_share_success);
        textDone=findViewById(R.id.text_button_done_success);
        textCurrency=findViewById(R.id.text_currency_success);
        successActivityContainer=findViewById(R.id.success_activity_container);
    }

    private void getDataIntent(){
        language=getIntent().getStringExtra(Constant.KEY_LANGUAGE_CODE);
        isLightMode=getIntent().getBooleanExtra(Constant.IS_LIGHT_MODE,false);
        checkoutPageConfigModel=getIntent().getParcelableExtra(Constant.KEY_CHECKOUT_PAGE_CONFIG);
        transactionInfoModel=getIntent().getParcelableExtra(Constant.KEY_TRANSACTION_INFO);
        billerModel=getIntent().getParcelableExtra(Constant.KEY_BILLER);

        ChangLanguage.setLanguage(language,this);

    }

    private void updateFont(){
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(this,language);

        textInvoiceAlreadyPaid.setTypeface(typeface);
        textInvoiceAlreadyPaid.setTextSize(16);
        textInvoiceAlreadyPaid.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);

        textTranNoTitle.setTypeface(typeface);
        textTranNoTitle.setTextSize(14);

        textBankRefTitle.setTypeface(typeface);
        textBankRefTitle.setTextSize(14);

        textToMerchantTitle.setTypeface(typeface);
        textToMerchantTitle.setTextSize(14);

        textTranDateTitle.setTypeface(typeface);
        textTranDateTitle.setTextSize(14);

        textTotalTitle.setTypeface(typeface);
        textTotalTitle.setTextSize(14);
        textTotalTitle.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);


        textTranNo.setTextSize(14);


        textBankRef.setTextSize(14);

        textToMerchant.setTypeface(typeface);
        textToMerchant.setTextSize(14);


        textTranDate.setTextSize(14);


        textTotalAmount.setTextSize(16);
        textTotalAmount.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);


        textCurrency.setTextSize(14);

        textDownload.setTypeface(typeface);
        textDownload.setTextSize(11);

        textShare.setTypeface(typeface);
        textShare.setTextSize(11);

        textDone.setTypeface(typeface);
        textDone.setTextSize(14);


    }

    private void bindData(){
        textTranNo.setText(transactionInfoModel.getTranNo());
        textBankRef.setText(transactionInfoModel.getBankRefId());
        textToMerchant.setText(billerModel.getBillerName());
        textTranDate.setText(transactionInfoModel.getTranDate());
        textTotalAmount.setText(transactionInfoModel.getTranAmountDisplay());
        textCurrency.setText(transactionInfoModel.getCurrency());

    }

    private void translateLanguage(){
        TranslateLanguage tranLang=TranslateLanguage.translateLanguage(language);
        textInvoiceAlreadyPaid.setText(tranLang.getInvoicePaidDone());
        textTranNoTitle.setText(tranLang.getTransactionNo());
        textBankRefTitle.setText(tranLang.getBankRef());
        textToMerchantTitle.setText(tranLang.getToMerchant());
        textTranDateTitle.setText(tranLang.getTransactionDate());
        textTotalTitle.setText(tranLang.getTotal());
        textDownload.setText(tranLang.getDownload());
        textShare.setText(tranLang.getShare());
        textDone.setText(tranLang.getDone());
    }
    private void launchDeeplink(String url){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);

    }

    private void applyTheme(){

        // dash line
        CustomTheme custom=CustomTheme.getThemeFromAPI(isLightMode,checkoutPageConfigModel);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.LINE);

        gradientDrawable.setStroke(1,
                Color.parseColor(custom.getIndicatorColor()),
                15,
                15);

        dashLineFirst.setBackground(gradientDrawable);
        dashLineSecond.setBackground(gradientDrawable);
        dashLineThird.setBackground(gradientDrawable);

        //success container
        successActivityContainer.setBackgroundColor(Color.parseColor(
                custom.getSecondaryBackgroundColor()));

        ShapeDrawable shape= CustomShape.applyShape(
                Color.parseColor(custom.getPrimaryBackgroundColor()),20,this);
        successContainer.setBackground(shape);

        //download share
        ShapeDrawable normalShape= CustomShape.applyShape(
                Color.parseColor(custom.getActionButtonBackgroundColor()),
                10,this);

        String selectorColor=ConvertColorHexa.getFiftyPercentColor(
                custom.getActionButtonBackgroundColor());

        ShapeDrawable selectorShape=CustomShape.applyShape(Color.parseColor(
                selectorColor),10,this);

        StateListDrawable selectorDownload= SelectedState.selectedSate(normalShape,selectorShape);
        downloadContainer.setBackground(selectorDownload);

        StateListDrawable selectorShare=SelectedState.selectedSate(normalShape,selectorShape);
        shareContainer.setBackground(selectorShare);

        //apply icon color
        ColorFilter colorFilter=new PorterDuffColorFilter(
                Color.parseColor(custom.getActionButtonTextColor()), PorterDuff.Mode.SRC_ATOP);
        imageShare.setColorFilter(colorFilter);
        imageDownload.setColorFilter(colorFilter);

        //text download share
        textDownload.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textShare.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));

        //button done
        ShapeDrawable btnDoneNormalShape= CustomShape.applyShape(
                Color.parseColor(custom.getButtonBackgroundColor()),10,this);
        String buttonSelectColor=ConvertColorHexa.getFiftyPercentColor(
                custom.getButtonBackgroundColor());
        ShapeDrawable btnDoneSelectorShape=CustomShape.applyShape(Color.parseColor(buttonSelectColor),
                10,this);

        StateListDrawable selector= SelectedState.selectedSate(btnDoneNormalShape,btnDoneSelectorShape);
        buttonDoneContainer.setBackground(selector);
        textDone.setTextColor(Color.parseColor(custom.getButtonTextColor()));

        //apply text theme
        textInvoiceAlreadyPaid.setTextColor(Color.parseColor(custom.getPrimaryTextColor()));
        textTranNoTitle.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textBankRefTitle.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textToMerchantTitle.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textTranDateTitle.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textTotalTitle.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textTranNo.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textBankRef.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textToMerchant.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textTranDate.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textTotalAmount.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
        textTotalAmount.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        textCurrency.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));
    }

    private Bitmap convertLayoutToImage(@NonNull View view){
        AppCompatTextView invoiceAlreayPaid=view.findViewById(R.id.text_invoice_already_paid_share);
        AppCompatTextView tranNoTitle=view.findViewById(R.id.text_tran_no_title_share);
        AppCompatTextView bankRefTitle=view.findViewById(R.id.text_bank_ref_title_share);
        AppCompatTextView toMerchantTitle=view.findViewById(R.id.text_to_merchant_title_share);
        AppCompatTextView tranDateTitle=view.findViewById(R.id.text_tran_date_title_share);
        AppCompatTextView totalTitle=view.findViewById(R.id.text_total_title_share);
        AppCompatTextView transactionNo=view.findViewById(R.id.text_tran_no_share);
        AppCompatTextView bankRef=view.findViewById(R.id.text_bank_ref_share);
        AppCompatTextView merchantName=view.findViewById(R.id.text_to_merchant_share);
        AppCompatTextView transactionDate=view.findViewById(R.id.text_tran_date_share);
        AppCompatTextView totalAmount=view.findViewById(R.id.text_total_amount_share);
        AppCompatTextView currency=view.findViewById(R.id.text_currency_share);


            //set value
            transactionNo.setText(transactionInfoModel.getTranNo());
            bankRef.setText(transactionInfoModel.getBankRefId());
            merchantName.setText(billerModel.getBillerName());
            transactionDate.setText(transactionInfoModel.getTranDate());
            totalAmount.setText(transactionInfoModel.getTranAmountDisplay());
            currency.setText(transactionInfoModel.getCurrency());

            //use to update font
            SetFont font=new SetFont();
            Typeface typeface=font.setFont(this,language);

            //translate
            TranslateLanguage tranLang=TranslateLanguage.translateLanguage(language);
            invoiceAlreayPaid.setText(tranLang.getInvoicePaidDone());
            tranNoTitle.setText(tranLang.getTransactionNo());
            bankRefTitle.setText(tranLang.getBankRef());
            toMerchantTitle.setText(tranLang.getToMerchant());
            tranDateTitle.setText(tranLang.getTransactionDate());
            totalTitle.setText(tranLang.getTotal());


            invoiceAlreayPaid.setTypeface(typeface);
            invoiceAlreayPaid.setTextSize(16);
            invoiceAlreayPaid.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);

            tranNoTitle.setTypeface(typeface);
            tranNoTitle.setTextSize(14);

            bankRefTitle.setTypeface(typeface);
            bankRefTitle.setTextSize(14);

            toMerchantTitle.setTypeface(typeface);
            toMerchantTitle.setTextSize(14);

            tranDateTitle.setTypeface(typeface);
            tranDateTitle.setTextSize(14);

            totalTitle.setTypeface(typeface);
            totalTitle.setTextSize(14);
            totalTitle.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);


            transactionNo.setTextSize(14);


            bankRef.setTextSize(14);

            merchantName.setTypeface(typeface);
            merchantName.setTextSize(14);


            transactionDate.setTextSize(14);


            totalAmount.setTextSize(16);
            totalAmount.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);


            currency.setTextSize(14);

            view.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());
            return  Bitmap.createBitmap(view.getMeasuredWidth(),view.getMeasuredHeight(),Bitmap.Config.ARGB_8888);

    }

    private void  customSnackBar(int image,String desc,int color){
        Snackbar customSnackbar = Snackbar.make(findViewById(R.id.snackbar_success_container), "",Snackbar.LENGTH_SHORT);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) customSnackbar.getView();
        View customView = getLayoutInflater().inflate(R.layout.snackbar_success_custom_layout, null);

        snackbarLayout.setBackgroundColor(ContextCompat.getColor(this,color));//remove snackbar background
        customView.setBackgroundColor(ContextCompat.getColor(this,color));
        snackbarLayout.addView(customView);

        //update font family
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(this,language);

        // Customize the content and appearance of the custom layout
        AppCompatTextView textView = customView.findViewById(R.id.custom_snackbar_desc);
        textView.setTypeface(typeface);
        textView.setText(desc);

        AppCompatImageView imageView=customView.findViewById(R.id.custom_snackbar_icon);
        imageView.setImageResource(image);

        customSnackbar.show();
    }
    private void downloadInvoice(Bitmap bitmap){

        String imageTitle=transactionInfoModel.getTranNo()+"-"+transactionInfoModel.getTranAmountDisplay()+" "+ transactionInfoModel.getCurrency();
        String imageUrl= MediaStore.Images.Media.insertImage(this.getContentResolver(),bitmap,imageTitle,"");

        String saveSuccess;
        if(language.equals(LanguageCode.EN)){
            saveSuccess=Translate.INVOICE_SAVE_MESSAGE_EN;

        }
        else {
            saveSuccess=Translate.INVOICE_SAVE_MESSAGE_KM;
        }

        if(imageUrl!=null){
           customSnackBar(R.drawable.check_circle_24px,saveSuccess,R.color.snackbar_background_success_color);
        }
//        else {
//            customSnackBar(R.drawable.error_24px,saveUnSuccess,R.color.snackbar_background_error_color);
//        }
    }

    private void shareInvoice(Bitmap bitmap){
        File tempFile;
        String fileName=transactionInfoModel.getTranNo()+"-"+transactionInfoModel.getTranAmountDisplay()+" "+transactionInfoModel.getCurrency();

        try {
            //tempFile = File.createTempFile(fileName, ".png", this.getCacheDir());
            tempFile=new File(getCacheDir(),fileName+".png");
            FileOutputStream fos = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            Uri uri = FileProvider.getUriForFile(this, Constant.AUTHORITY, tempFile);

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(Intent.createChooser(shareIntent, "Share Invoice"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        initView();
        getDataIntent();

        applyTheme();//apply theme

        translateLanguage();
        bindData();

        updateFont();

        View layoutImage=getLayoutInflater().inflate(R.layout.download_share_success_image_layout,null);
        downloadContainer.setOnClickListener(v->{
            Bitmap bitmap=convertLayoutToImage(layoutImage);
            Canvas canvas=new Canvas(bitmap);
            layoutImage.draw(canvas);
            //Save Image into Gallerry
            downloadInvoice(bitmap);

        });
        shareContainer.setOnClickListener(v->{
            Bitmap bitmap=convertLayoutToImage(layoutImage);
            Canvas canvas=new Canvas(bitmap);
            layoutImage.draw(canvas);

            shareInvoice(bitmap);

        });
        buttonDoneContainer.setOnClickListener(v->{
            //todo lauchdeeplink
            Toast.makeText(this, "Button done click", Toast.LENGTH_SHORT).show();
        });

    }




}