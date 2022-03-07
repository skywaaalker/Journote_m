package com.example.journote.ui;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.journote.R;
import com.example.journote.db.HttpConnnect;
import com.example.journote.db.JournoteDBOpenHelper;
import com.example.journote.db.JournoteDatabaseHelper;
import com.example.journote.db.NotificationDatabaseHelper;
import com.example.journote.reminder.MyReceiver;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


public class DateTimePickerActivity extends AppCompatActivity {
    private Calendar currentDate;
    private Calendar setNotiDate;

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private int notiId =2;
    private final static int QUERY_NOTI_JUDGE = 1;
    private final static int INSERT_NOTI_JUDGE = 2;
    private final static int UPDATE_NOTI_JUDGE = 3;
    private final static int TIME_EARLY_JUDGE = 4;

    public static void startActivity(Context context, String tag) {
        Intent intent = new Intent(context, DateTimePickerActivity.class);
        intent.putExtra("tag",tag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.date_time_picker);
        Intent intent = getIntent();
        final String tag = intent.getStringExtra("tag");
        final String title = intent.getStringExtra("title");
        final String content = intent.getStringExtra("content");
        //final int authorid = intent.getIntExtra("authorid", 2);
        final String username = intent.getStringExtra("username");

        DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
        TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
        Button btn = (Button) findViewById(R.id.datetime_picker_add);
        //TextView textView = (TextView) findViewById(R.id.datetime_picker_show);
        timePicker.setIs24HourView(true);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        currentDate = Calendar.getInstance();
        showDate(currentDate);
        setNotiDate = Calendar.getInstance();

        datePicker.init(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                setNotiDate.set(i, i1, i2);
                showDate(setNotiDate);
            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                setNotiDate.set(Calendar.HOUR_OF_DAY, i);
                setNotiDate.set(Calendar.MINUTE, i1);
                showDate(setNotiDate);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
              if (setNotiDate.getTimeInMillis() < System.currentTimeMillis()){
                try {
                  Toast.makeText(DateTimePickerActivity.this, "请选择现在之后的时间", Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                  e.printStackTrace();
                }
                return;
              }
                new Thread(new Runnable() {
                  @Override
                  public void run() {
                    String date_str = "";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    date_str = sdf.format(setNotiDate.getTime());
                    String result = HttpConnnect.isJournoteHasnotiByPost(tag);
                    if (result.equals("hasnoti")){
                      String result1 = HttpConnnect.notificationUpdateByPost(tag, date_str);
                      Bundle bundle1 = new Bundle();
                      bundle1.putString("updatenotiresult", result1);
                      Message message1 = new Message();
                      message1.setData(bundle1);
                      message1.what = UPDATE_NOTI_JUDGE;
                      handler.sendMessage(message1);
                    } else if (result.equals("nothasnoti")) {
                      String result2 = HttpConnnect.notificationInsertByPost(tag, date_str);
                      Bundle bundle2 = new Bundle();
                      bundle2.putString("insertnotiresult", result2);
                      Message message2 = new Message();
                      message2.setData(bundle2);
                      message2.what = INSERT_NOTI_JUDGE;
                      handler.sendMessage(message2);
                    }
                  }
                }).start();


//                String channelid="channel_1";
//                String description = setNotiDate.get(1)+""+setNotiDate.get(2)+""+setNotiDate.get(3)+""+setNotiDate.get(10)+""+setNotiDate.get(12);
//                int importance = NotificationManager.IMPORTANCE_LOW;
//                NotificationChannel channel = new NotificationChannel(channelid, description, importance);
//                NotificationManager notificationManager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//                notificationManager.createNotificationChannel(channel);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String date_str = sdf.format(setNotiDate.getTime());
//                System.out.println("!!!!!!!DatetimePickerActivity:set date to"+date_str+"timemills="+setNotiDate.getTimeInMillis());
//                System.out.println("!!!!!this.notiid="+DateTimePickerActivity.this.notiId+"current Timemills="+System.currentTimeMillis());
//                Notification notification = new Notification.Builder(DateTimePickerActivity.this, channelid)
//                        .setContentTitle(title).setContentText(content).setWhen(setNotiDate.getTimeInMillis()/1000)
//                        .setAutoCancel(true)
//                        .setSmallIcon(R.drawable.clock).build();
//                notificationManager.notify(notiId, notification);
//                DateTimePickerActivity.this.notiId += 1;
                setNotiDate.set(Calendar.SECOND, 0);
                Intent intentPend = new Intent(DateTimePickerActivity.this, MyReceiver.class);
                intentPend.putExtra("username", username);
                intentPend.putExtra("title", title);
                intentPend.putExtra("content", content);
                intentPend.setAction("com.example.journote.reminder.MyReceiver");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(DateTimePickerActivity.this, 0, intentPend, PendingIntent.FLAG_ONE_SHOT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, setNotiDate.getTimeInMillis(), pendingIntent);

                Intent intent1 = getIntent();
                String username = intent1.getStringExtra("username");
                Intent intent = new Intent(DateTimePickerActivity.this, MainActivity.class);
                intent.putExtra("username", username);
                startActivityForResult(intent, 1000);
            }
        });


    }


    Handler handler = new Handler(){
      @Override
      public void handleMessage(Message msg){
        super.handleMessage(msg);
        switch (msg.what){
          case QUERY_NOTI_JUDGE:{
            Bundle bundle = new Bundle();
            bundle = msg.getData();
            String result = bundle.getString("result");
            try{
              if(result.equals("hasnoti")){
                //this journote has notification;
              } else {

              }
            }catch (NullPointerException e ){
              e.printStackTrace();
            }
          }
          break;
          case INSERT_NOTI_JUDGE:{
            Bundle bundle =  new Bundle();
            bundle = msg.getData();
            String result = bundle.getString("insertnotiresult");
            try{
              if (result.equals("success")){
                Toast.makeText(DateTimePickerActivity.this, "添加提醒成功", Toast.LENGTH_SHORT).show();
              } else {
                Toast.makeText(DateTimePickerActivity.this, "添加提醒失败", Toast.LENGTH_SHORT).show();
              }
            }catch (NullPointerException e){
              e.printStackTrace();
            }
          }
          break;
          case UPDATE_NOTI_JUDGE:{
            Bundle bundle = new Bundle();
            bundle = msg.getData();
            String result = bundle.getString("updatenotiresult");
            try{
              if (result.equals("success")){
                Toast.makeText(DateTimePickerActivity.this, "更改提醒成功", Toast.LENGTH_SHORT).show();
              }else {
                Toast.makeText(DateTimePickerActivity.this, "更改提醒失败", Toast.LENGTH_SHORT).show();
              }
            } catch (NullPointerException e ){
              e.printStackTrace();
            }
          }
          break;
          case TIME_EARLY_JUDGE:{
            try {
              Toast.makeText(DateTimePickerActivity.this, "请选择现在之后的时间", Toast.LENGTH_SHORT);
            } catch (NullPointerException e){
              e.printStackTrace();
            }
          }
          break;
        }
      }
    };


    private void showDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TextView textView = (TextView) findViewById(R.id.datetime_picker_show);
        textView.setText("设置提醒时间:" + year + "年" + (month + 1) + "月" + day + "日" + hour + "时" + minute + "分");
    }

}

