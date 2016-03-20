package com.zsl.myapplication.common.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.zsl.myapplication.R;

/**
 * Created by zsl on 2/28/16.
 */
public class NotificationUtils {
    private static NotificationUtils ourInstance = new NotificationUtils();

    private Context mContext;
    public static NotificationUtils getInstance(Context context) {
        ourInstance.mContext = context;
        return ourInstance;
    }

    private NotificationUtils() {
    }

    public void generateNotifacation(Intent intent,String topTitle,String title,String message){
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,0);

        NotificationManager nm = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification =  new Notification.Builder(mContext)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ps)
                .setWhen(System.currentTimeMillis())
                .setTicker(topTitle)
                .build();
        nm.notify(1,notification);
    }
}
