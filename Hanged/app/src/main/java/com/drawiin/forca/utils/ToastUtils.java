package com.drawiin.forca.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void makeShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
