package com.example.journote.reminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.journote.R;
import com.example.journote.ui.MainActivity;
import com.example.journote.ui.NotificationActivity;

/**
 * Receive the broadcast and start the activity that will show the alarm
 */
public class MyReceiver extends BroadcastReceiver {
    private NotificationManager notificationManager = null;
    private static final int NOTIFICATION_FLAG = 3;
    private static int notiId2 = 6;
    private static int notiId = 7;
    /**
     * called when the BroadcastReceiver is receiving an Intent broadcast.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
      notiId ++;
      notiId2 ++;
      notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
      if(intent.getAction().equals("com.example.journote.reminder.MyReceiver")){
        /* start another activity - MyAlarm to display the alarm */
        Log.e("alarm_receiver", "定时提醒闹钟");
        //Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.jounoteicon);
        String title = intent.getStringExtra("title");
        String content = intent. getStringExtra("content");
        String username = intent.getStringExtra("username");
        //Intent intent1 = new Intent(context, MainActivity.class);
        //intent1.putExtra("username", username);
        //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
//        Intent intent1 = new Intent(context, NotificationActivity.class);
//        intent1.putExtra("title", title);
//        intent1.putExtra("content", content);
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent1);
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
          String title1 = intent.getStringExtra("title");
          String content1 = intent. getStringExtra("content");
          Log.e("alarm","notiId"+notiId+"; title:"+title+"; content:"+content);
          Log.e("alarm","notiId"+notiId+"; title1:"+title1+"; content1:"+content1);
          //String channelid = "channel";
          //String description =  "set_notification";
          String channelid = notiId + "";
          String description = notiId + "";
          int importance = NotificationManager.IMPORTANCE_DEFAULT;
          NotificationChannel channel =  new NotificationChannel(channelid, description, importance);
          NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
          notificationManager.createNotificationChannel(channel);
          Notification notification = new Notification.Builder(context, channelid)
            .setContentTitle(title1).setContentText(content1).setWhen(System.currentTimeMillis())
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.clock).build();
          notificationManager.notify(1, notification);
        } else {
          NotificationCompat.Builder buidler = new NotificationCompat.Builder(context)
            .setContentTitle(title).setContentText(content).setWhen(System.currentTimeMillis())
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.clock);
          Notification notification_lower = null;
          notification_lower = buidler.build();
          NotificationManager notificationManager1 = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
          notificationManager1.notify(2, notification_lower);
        }
      }
    }
}

