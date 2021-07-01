package com.drawiin.forca.utils;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.drawiin.forca.R;

import java.util.Random;
import java.util.function.Consumer;

import javax.inject.Inject;

public class NotificationsUtils {
    final Application context;

    private static final String CHANNEL_ID = "CHANNEL_ID";

    @Inject
    public NotificationsUtils(Application context) {
        this.context = context;
        init();
    }

    private void init() {
        createNotificationChannel();
    }

    public Consumer<String> createNotification(String title, String content) {
        return create(title, content);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Consumer<String> create(String title, String content) {
        final int notificationId = new Random().nextInt();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.sticker_7)
                .setContentTitle(title)
                .setContentText(content)
                .setOnlyAlertOnce(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancelAll();
        notificationManager.notify(notificationId, builder.build());
        final Consumer<String> update = (value) -> {
            builder.setContentText(value);
            notificationManager.notify(notificationId, builder.build());
        };

        return update;
    }

    public void dismissAll() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancelAll();
    }
}