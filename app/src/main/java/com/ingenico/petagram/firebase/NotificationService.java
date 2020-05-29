package com.ingenico.petagram.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ingenico.petagram.MainActivity;
import com.ingenico.petagram.R;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseService";

    private void notification(String title, String body){
        int notificationId = 0;
        String CHANNEL_ID = "Channel_id";
        String CHANNEL_NAME = "Channel_name";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT, null);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.like_64)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(sound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.notify(0, notification.build());
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " +  remoteMessage.getNotification().getBody());

        notification(remoteMessage.getFrom(), remoteMessage.getNotification().getBody());
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(@NonNull String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onSendError(@NonNull String s, @NonNull Exception e) {
        super.onSendError(s, e);
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d(TAG, "Token: " + s);
    }
}
