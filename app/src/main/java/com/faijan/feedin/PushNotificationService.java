package com.faijan.feedin;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService extends FirebaseMessagingService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NewApi")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String subj = remoteMessage.getNotification().getBody();
        String CHANNEL_ID = "com.faijan.feedin";
        ;
        CharSequence name;
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "FeedIn Donation",
                NotificationManager.IMPORTANCE_HIGH
        );
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Context context;
        Notification.Builder notificationBuilder = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(subj)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true);


        NotificationManagerCompat.from(this).notify(1, notificationBuilder.build());
        super.onMessageReceived(remoteMessage);
    }
}
