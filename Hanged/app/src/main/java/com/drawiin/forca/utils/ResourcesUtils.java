package com.drawiin.forca.utils;

import android.content.Context;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

public class ResourcesUtils {

    @ColorInt
    public static int getColor(Context context, @ColorRes int id) {
        return ContextCompat.getColor(context, id);
    }
}
