package com.bill24.onlinepaymentsdk.customShapeDrawable;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

public class CustomShape {

    public static ShapeDrawable applyShape(int color, float radiusSizeDp, Context context){
        float cornerRadiusPX = (int) (radiusSizeDp * context.getResources().getDisplayMetrics().density);
        float[] radii = new float[] { cornerRadiusPX, cornerRadiusPX, cornerRadiusPX, cornerRadiusPX, cornerRadiusPX, cornerRadiusPX, cornerRadiusPX, cornerRadiusPX }; // Adjust the radii as needed
        RoundRectShape roundedRectShape = new RoundRectShape(radii, null, null);

        ShapeDrawable shape = new ShapeDrawable(roundedRectShape);
        shape.getPaint().setColor(color); // Set the fill color
        shape.getPaint().setStyle(Paint.Style.FILL);

        return shape;
    }

}
