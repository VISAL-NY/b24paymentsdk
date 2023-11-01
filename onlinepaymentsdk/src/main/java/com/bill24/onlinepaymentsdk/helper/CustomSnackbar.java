package com.bill24.onlinepaymentsdk.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bill24.onlinepaymentsdk.R;
import com.google.android.material.snackbar.Snackbar;

public class CustomSnackbar {

    public static void showSuccessSnackbar(Context context,@Nullable View view,
                                    int image, String  desc, int color,
                                    String language){
        Snackbar customSnackbar = Snackbar.make(view.findViewById(R.id.container_khqrfragment), "",Snackbar.LENGTH_SHORT);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) customSnackbar.getView();
        View customView= LayoutInflater.from(context).inflate(R.layout.snackbar_success_custom_layout,null);
        customView.setBackgroundColor(context.getColor(color));
        snackbarLayout.setBackgroundColor(context.getColor(color));//remove snackbar background
        snackbarLayout.addView(customView);

        //update font family
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(context,language);



        // Customize the content and appearance of the custom layout
        AppCompatTextView textView = customView.findViewById(R.id.custom_snackbar_desc);
        textView.setTypeface(typeface);
        textView.setText(desc);

        AppCompatImageView imageView=customView.findViewById(R.id.custom_snackbar_icon);
        imageView.setImageResource(image);

        customSnackbar.show();
    }
}
