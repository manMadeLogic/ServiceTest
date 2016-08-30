package com.example.xichen.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Xi Chen on 2016/8/30.
 */
public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Notification.Builder builder = new Notification.Builder(this);
//        Notification notification = new Notification(R.mipmap.ic_launcher, "Notification comes",
//                System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("Notification comes");
        builder.setWhen(System.currentTimeMillis());

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notification.setLatestEventInfo(this, "This is title", "This is content", pendingIntent);
        builder.setContentTitle("This is title");
        builder.setContentText("This is content");
        builder.setContentIntent(pendingIntent);
        startForeground(1, builder.build());
        Log.d("MyService", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy");

    }

    protected class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d("MyService", "startDownload");
        }

        public int getProgress(){
            Log.d("MyService", "getProgress");
            return 0;
        }
    }
}
