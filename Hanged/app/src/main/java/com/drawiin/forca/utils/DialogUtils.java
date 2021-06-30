package com.drawiin.forca.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogUtils {
    public static void showDialog(
            Context context,
            String title,
            String message,
            String primaryLabel,
            String secondaryLabel,
            Runnable onPrimaryClicked,
            Runnable onSecondaryClicked
    ) {
        final AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(primaryLabel, (dialogInterface, i) -> onPrimaryClicked.run())
                .setNegativeButton(secondaryLabel, (dialogInterface, i) -> onSecondaryClicked.run())
                .setCancelable(false)
                .create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
