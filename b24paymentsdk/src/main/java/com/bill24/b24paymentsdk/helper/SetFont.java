package com.bill24.b24paymentsdk.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import com.bill24.b24paymentsdk.R;
import com.bill24.b24paymentsdk.model.conts.LanguageCode;


public class SetFont {
    public Typeface setFont(Context context, String languageCode){
        Typeface typeface;
        if (languageCode.equals(LanguageCode.EN)){
            typeface= ResourcesCompat.getFont(context, R.font.roboto_regular);
        }
        else {
            typeface=ResourcesCompat.getFont(context,R.font.battambang_regular);
        }
        return typeface;
    }


}
