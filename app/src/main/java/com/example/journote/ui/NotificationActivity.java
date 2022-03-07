package com.example.journote.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.example.journote.R;

import org.greenrobot.eventbus.EventBus;


public class NotificationActivity extends AppCompatActivity {
  private int notiId = 2;
  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

    Intent intent = getIntent();
    String title = intent.getStringExtra("title");
    String content = intent.getStringExtra("content");

    String channelid = "channel_1";
    String description =  "set_notification";
    int importance = NotificationManager.IMPORTANCE_LOW;
    NotificationChannel channel =  new NotificationChannel(channelid, description, importance);
    NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    notificationManager.createNotificationChannel(channel);
    Notification notification = new Notification.Builder(NotificationActivity.this, channelid)
      .setContentTitle(title).setContentText(content).setWhen(System.currentTimeMillis())
      .setAutoCancel(true)
      .setSmallIcon(R.drawable.clock).build();
    notificationManager.notify(notiId, notification);

  }


}
