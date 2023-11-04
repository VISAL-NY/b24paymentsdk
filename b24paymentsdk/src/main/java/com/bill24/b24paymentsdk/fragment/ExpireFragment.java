package com.bill24.b24paymentsdk.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.bill24.b24paymentsdk.R;
import com.bill24.b24paymentsdk.bottomsheetDialogFragment.BottomSheet;
import com.bill24.b24paymentsdk.customShapeDrawable.CustomShape;
import com.bill24.b24paymentsdk.customShapeDrawable.SelectedState;
import com.bill24.b24paymentsdk.helper.ChangLanguage;
import com.bill24.b24paymentsdk.helper.ConvertColorHexa;
import com.bill24.b24paymentsdk.helper.SetFont;
import com.bill24.b24paymentsdk.helper.SharePreferenceCustom;
import com.bill24.b24paymentsdk.helper.translateLanguage.TranslateLanguage;
import com.bill24.b24paymentsdk.model.CheckoutPageConfigModel;
import com.bill24.b24paymentsdk.model.conts.Constant;
import com.bill24.b24paymentsdk.theme.CustomTheme;

public class ExpireFragment extends Fragment {
    private FrameLayout buttonTryAgain;
    private AppCompatTextView textTryagainButton;
    private AppCompatTextView textTranHasExpired,textTryAgain,expireDashLine;
    private String transactionId,refererKey,language;
    private CheckoutPageConfigModel checkoutPageConfigModel;
    private LinearLayoutCompat expireContainer;
    private boolean isLightMode;
    public ExpireFragment(String transactionId){
      this.transactionId=transactionId;
    }
    private void initView(View view){
        buttonTryAgain=view.findViewById(R.id.button_try_again);
        textTranHasExpired =view.findViewById(R.id.text_expire_title);
        textTryAgain=view.findViewById(R.id.text_try_again_title);
        expireContainer=view.findViewById(R.id.expire_container);
        textTryagainButton=view.findViewById(R.id.text_button_try_again);
        expireDashLine=view.findViewById(R.id.expire_dash_line);
    }
    private void updateFont(){
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(getContext(),language);
        textTranHasExpired.setTypeface(typeface);
        textTranHasExpired.setTextSize(16);
        textTranHasExpired.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);

        textTryAgain.setTypeface(typeface);
        textTryAgain.setTextSize(14);

        textTryagainButton.setTypeface(typeface);
        textTryagainButton.setTextSize(13);
    }

    private  void applyTheme(){
        CustomTheme custom=CustomTheme.getThemeFromAPI(isLightMode,checkoutPageConfigModel);
        expireContainer.setBackgroundColor(Color.parseColor(custom.getSecondaryBackgroundColor()));
        textTranHasExpired.setTextColor(Color.parseColor(custom.getPrimaryTextColor()));
        textTryAgain.setTextColor(Color.parseColor(custom.getSecondaryTextColor()));

        //shape button
        ShapeDrawable normalShape= CustomShape.applyShape(
                Color.parseColor(custom.getButtonBackgroundColor()),
                10,getContext());

        String selectorColor=ConvertColorHexa.getFiftyPercentColor(custom.getButtonBackgroundColor());

        ShapeDrawable selectorShape=CustomShape.applyShape(
                Color.parseColor(selectorColor),10,getContext()
        );

        StateListDrawable selector= SelectedState.selectedSate(normalShape,selectorShape);
        buttonTryAgain.setBackground(selector);

        //text button
        textTryagainButton.setTextColor(Color.parseColor(custom.getButtonTextColor()));

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.LINE);
        int dashWidthHeight = (int) (5 * getContext().getResources().getDisplayMetrics().density);
        int width = (int) (1 * getContext().getResources().getDisplayMetrics().density);
        gradientDrawable.setStroke(width,
                Color.parseColor(custom.getIndicatorColor()),
                dashWidthHeight,
                dashWidthHeight); // Set the stroke color and width// Set the corner radius
        gradientDrawable.setDither(true);
        expireDashLine.setBackground(gradientDrawable);
    }

    private  void  getSharePreference(){
        SharedPreferences preferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        //get language
        language=preferences.getString(Constant.KEY_LANGUAGE_CODE,"");
        //get refererKey
        refererKey=preferences.getString(Constant.KEY_REFERER_KEY,"");
        ChangLanguage.setLanguage(language,getContext());

        String checkoutPageConfigJson=preferences.getString(Constant.KEY_CHECKOUT_PAGE_CONFIG,"");
        checkoutPageConfigModel= SharePreferenceCustom.converJsonToObject(checkoutPageConfigJson, CheckoutPageConfigModel.class);

        isLightMode=preferences.getBoolean(Constant.IS_LIGHT_MODE,true);
    }

    private void translateLanguage(){
        TranslateLanguage tranLang=TranslateLanguage.translateLanguage(language);
        textTranHasExpired.setText(tranLang.getTranHasExpired());
        textTryAgain.setText(tranLang.getTryAgainToContinue());
        textTryagainButton.setText(tranLang.getTryAgain());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSharePreference();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.expire_fragment_layout,container,false);
        initView(view);

        translateLanguage();

        //Update Font
        updateFont();

        //apply theme shape
        applyTheme();


        buttonTryAgain.setOnClickListener(v->{
            Fragment fragment=getParentFragment();
            if(fragment !=null && fragment instanceof BottomSheet){
                ((BottomSheet)getParentFragment()).showFragment(new KhqrFragment(transactionId));
            }
        });

        return view;
    }
}
