package com.drawiin.forca.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

public class ResourcesUtils {

    @ColorInt
    public static int getColor(Context context, @ColorRes int id) {
        return ContextCompat.getColor(context, id);
    }

    public static Drawable getDrawable(Context context, @DrawableRes int id){
        return  ContextCompat.getDrawable(context, id);
    }
}
