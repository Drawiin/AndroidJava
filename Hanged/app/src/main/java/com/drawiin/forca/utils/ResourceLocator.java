package com.drawiin.forca.utils;

import android.app.Application;
import android.content.Context;

import androidx.annotation.StringRes;

import javax.inject.Inject;

public class ResourceLocator {
    private final Application context;

    @Inject
    ResourceLocator(Application context) {
        this.context = context;
    }

    public String getString(@StringRes Integer stringId) {
        return context.getString(stringId);
    }

    public String getString(Context context, @StringRes int resId, Object... formatArgs) {
        return context.getString(resId, formatArgs);
    }
}
