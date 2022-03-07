package com.example.journote.ui;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.journote.R;
import com.example.journote.bean.NotificationBean;
import com.example.journote.db.HttpConnnect;
import com.example.journote.db.JournoteDBOpenHelper;
import com.example.journote.db.JournoteDatabaseHelper;
import com.example.journote.db.NotificationDatabaseHelper;
import com.example.journote.utils.AppManager;
import com.example.journote.utils.GetDate;
import com.example.journote.utils.StatusBarCompat;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateJournoteActivity extends AppCompatActivity {
    @Bind(R.id.update_journal_tv_date)
    TextView mUpdateJournalTvDate;
    @Bind(R.id.update_journal_et_title)
    TextView mUpdateJournalEtTitle;
    @Bind(R.id.update_journal_et_content)
    TextView mUpdateJournalEtContent;
    @Bind(R.id.update_journal_fab_add)
    FloatingActionButton mUpdateJournalFabAdd;
    @Bind(R.id.update_journal_fab_back)
    FloatingActionButton mUpdateJournalFabBack;
    @Bind(R.id.update_journal_fab_delete)
    FloatingActionButton mUpdateJournalFabDelete;
    @Bind(R.id.fab_menu)
    FloatingActionsMenu mFabMenu;

    @Bind(R.id.common2_title_ll)
    LinearLayout mCommon2TitleLl;
    @Bind(R.id.common2_iv_back)
    ImageView mCommon2IvBack;
    @Bind(R.id.common2_tv_title)
    TextView mCommon2TvTitle;
    @Bind(R.id.common2_iv_noti)
    ImageView mCommon2IvNoti;
    @Bind(R.id.common2_iv_label)
    ImageView mCommon2IvLabel;

    //@Bind(R.id.update_journal_id)
    //TextView mUpdateJournalId;
    @Bind(R.id.update_journote_tv_tag)
    TextView mUpdateJournoteTvTag;
    @Bind(R.id.update_journal_noti_time)
    TextView mUpdateJournoalNotiTime;
    //@Bind(R.id.date_picker)
    //DatePicker mDatePicker;
    //@Bind(R.id.time_picker)
    //TimePicker mTimePicker;

    @Nullable
    @Bind(R.id.menu_noti_setdate)
    MenuItem mMenuNotiSetdate;

    private JournoteDatabaseHelper mHelper;
    private NotificationDatabaseHelper mNotiHelper;
    private JournoteDBOpenHelper mSqlHelper;

    private final static int DELETE_JUDGE = 1;
    private final static int UPDATE_JUDGE = 2;
    private final static int QUERY_NOTI_JUDGE = 10;
    private final static int INSERT_NOTI_JUDGE = 11;
    private final static int UPGRADE_NOTI_JUDGE = 12;
    private final static int QUERY_NOTI_TIME_JUDGE = 13;
    private final static int DELETE_NOTI_JUDGE = 3;
    private final static int SET_LABEL_0_JUDGE = 4;
    private final static int SET_LABEL_1_JUDGE = 5;
    private final static int SET_LABEL_2_JUDGE = 6;
    private final static int SET_LABEL_3_JUDGE = 7;
    private final static int SET_LABEL_4_JUDGE = 8;
    private final static int SET_LABEL_5_JUDGE = 9;
    public static void startActivity(Context context, String title, String content, String tag, String username, String date){
        Intent intent = new Intent(context, UpdateJournoteActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("tag", tag);
        intent.putExtra("username", username);
        intent.putExtra("date", date);
        context.startActivity(intent);
    }

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_journal);
        AppManager.getAppManager().addActivity(this);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        //mUpdateJournalId.setText(intent.getStringExtra("id")+ "");
        String tag = intent.getStringExtra("tag");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Thread(new Runnable() {
          @Override
          public void run() {
            NotificationBean notificationBean = HttpConnnect.getNotification(tag);
            Bundle bundle = new Bundle();
            //bundle.putString("result", result);
            bundle.putSerializable("notificationBean", notificationBean);
            Message message = new Message();
            message.setData(bundle);
            message.what = QUERY_NOTI_TIME_JUDGE;
            handler.sendMessage(message);
          }
        }).start();

        mCommon2TvTitle.setText("修改日记");
        StatusBarCompat.compat(this, Color.parseColor("#161414"));

        String username = intent.getStringExtra("username");
        mUpdateJournalTvDate.setText(intent.getStringExtra("date"));
        mUpdateJournalEtTitle.setText(intent.getStringExtra("title"));
        mUpdateJournalEtContent.setText(intent.getStringExtra("content"));
        mUpdateJournoteTvTag.setText(intent.getStringExtra("tag"));
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case DELETE_JUDGE:{
                    Bundle bundle = new Bundle();
                    bundle = msg.getData();
                    String result = bundle.getString("result");
                    try{
                        if (result.equals("success")){
                            Toast.makeText(UpdateJournoteActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                            //Intent intent =new Intent();
                            //MainActivity.startActivity(LoginActivity.this, username);
                            Intent intent1 =getIntent();
                            String username =intent1.getStringExtra("username");
                            Intent intent = new Intent(UpdateJournoteActivity.this, MainActivity.class);
                            intent.putExtra("username",username);
                            startActivity(intent);
                        }else {
                            Toast.makeText(UpdateJournoteActivity.this,"删除失败，请重新尝试！",Toast.LENGTH_SHORT).show();
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                break;
                case UPDATE_JUDGE:{
                    Bundle bundle = new Bundle();
                    bundle = msg.getData();
                    String result = bundle.getString("result");
                    Intent intent1 =getIntent();
                    String username =intent1.getStringExtra("username");
                    try{
                        if (result.equals("success")){
                            Toast.makeText(UpdateJournoteActivity.this,"更新成功！",Toast.LENGTH_SHORT).show();
                            //Intent intent =new Intent();
                            //MainActivity.startActivity(LoginActivity.this, username);

                            Intent intent = new Intent(UpdateJournoteActivity.this, MainActivity.class);
                            intent.putExtra("username",username);
                            startActivityForResult(intent, 1000);
                        }else {
                            Toast.makeText(UpdateJournoteActivity.this,"更新失败，请重新尝试！",Toast.LENGTH_SHORT).show();
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                break;
              case DELETE_NOTI_JUDGE:{
                Bundle bundle = new Bundle();
                bundle = msg.getData();
                String result = bundle.getString("result");
                try{
                  if (result.equals("success")){
                    Toast.makeText(UpdateJournoteActivity.this,"删除提醒成功！",Toast.LENGTH_SHORT).show();
                  }else {
                    Toast.makeText(UpdateJournoteActivity.this,"删除提醒失败，请重新尝试！",Toast.LENGTH_SHORT).show();
                  }
                }catch (NullPointerException e){
                  e.printStackTrace();
                }
              }
              break;
              case SET_LABEL_0_JUDGE:{
                Bundle bundle = new Bundle();
                bundle = msg.getData();
                String result = bundle.getString("result");
                try{
                  if (result.equals("success")){
                    Toast.makeText(UpdateJournoteActivity.this,"删除标签成功！",Toast.LENGTH_SHORT).show();
                  }else {
                    Toast.makeText(UpdateJournoteActivity.this,"删除标签失败，请重新尝试！",Toast.LENGTH_SHORT).show();
                  }
                }catch (NullPointerException e){
                  e.printStackTrace();
                }
              }
              break;
              case SET_LABEL_1_JUDGE:
              case SET_LABEL_2_JUDGE:
              case SET_LABEL_3_JUDGE:
              case SET_LABEL_4_JUDGE:
              case SET_LABEL_5_JUDGE:{
                Bundle bundle = new Bundle();
                bundle = msg.getData();
                String result = bundle.getString("result");
                try{
                  if (result.equals("success")){
                    Toast.makeText(UpdateJournoteActivity.this,"添加标签成功！",Toast.LENGTH_SHORT).show();
                  }else {
                    Toast.makeText(UpdateJournoteActivity.this,"添加标签失败，请重新尝试！",Toast.LENGTH_SHORT).show();
                  }
                }catch (NullPointerException e){
                  e.printStackTrace();
                }
              }
              break;
              case QUERY_NOTI_JUDGE:{
                Bundle bundle = new Bundle();
                bundle = msg.getData();
                String result = bundle.getString("result");
                try{
                  if (result.equals("hasnoti")){
                    mMenuNotiSetdate.setTitle("更改提醒日期");
                  } else {
                    mMenuNotiSetdate.setTitle("添加提醒日期");
                  }
                }catch (NullPointerException e){
                  e.printStackTrace();
                }
              }
              break;
              case QUERY_NOTI_TIME_JUDGE: {
                Bundle bundle = new Bundle();
                bundle = msg.getData();
                NotificationBean notificationBean = (NotificationBean) bundle.getSerializable("notificationBean");
                try{
                  if (notificationBean.getDateStr() == null){
                    mUpdateJournoalNotiTime.setVisibility(View.GONE);
                  }else {
                    mUpdateJournoalNotiTime.setText("提醒时间："+notificationBean.getDateStr());
                  }
                }catch (NullPointerException e){
                  e.printStackTrace();
                }
              }
              break;
            }
        }
    };
    @OnClick({R.id.common2_iv_back, R.id.update_journal_tv_date, R.id.update_journal_et_title,
    R.id.update_journal_et_content, R.id.update_journal_fab_add, R.id.update_journal_fab_back, R.id.update_journal_fab_delete})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.common2_iv_back:
                Intent intent1 = getIntent();
                //final int authorid = intent.getIntExtra("authorid", 2);
                String username = intent1.getStringExtra("username");
                Intent intent = new Intent(UpdateJournoteActivity.this, MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            case R.id.update_journal_tv_date:
                break;
            case R.id.update_journal_et_content:
                break;
            case R.id.update_journal_et_title:
                break;
            case R.id.update_journal_fab_delete:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("确定要删除该日记吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String tag = mUpdateJournoteTvTag.getText().toString();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String result = HttpConnnect.DeleteByPost(tag);
                                Bundle bundle = new Bundle();
                                bundle.putString("result", result);
                                Message message = new Message();
                                message.setData(bundle);
                                message.what = DELETE_JUDGE;
                                //myHandler.sendMessage(message);
                                handler.sendMessage(message);
                                //MainActivity.startActivity(LoginActivity.this, username);
                            }
                        }).start();

                        Intent intent = getIntent();
                        final String username = intent.getStringExtra("username");
                        Intent intent1 = new Intent(UpdateJournoteActivity.this, MainActivity.class);
                        intent1.putExtra("username",username);
                        startActivityForResult(intent1, 1000);
                        //MainActivity.startActivity(UpdateJournoteActivity.this, username);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
                break;
            case R.id.update_journal_fab_add:
                //SQLiteDatabase dbUpdate = mHelper.getWritableDatabase();
                //ContentValues valuesUpdate = new ContentValues();
                String tagAdd = mUpdateJournoteTvTag.getText().toString();
                String title = mUpdateJournalEtTitle.getText().toString();
                String content = mUpdateJournalEtContent.getText().toString();
                System.out.println("!!!!!! newtitle,tag and new content is:"+tagAdd+" "+title+" "+content);
                Log.i("test messages","new title, tag and content is "+title+" "+tagAdd+" "+content);
                Intent intent_add = getIntent();
                String username1 = intent_add.getStringExtra("username");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = HttpConnnect.journalEditByPost(title, content, tagAdd);
                        Bundle bundle = new Bundle();
                        bundle.putString("result", result);
                        Message message = new Message();
                        message.setData(bundle);
                        message.what = UPDATE_JUDGE;
                        //myHandler.sendMessage(message);
                        handler.sendMessage(message);

                        Intent intent = new Intent(UpdateJournoteActivity.this, MainActivity.class);
                        intent.putExtra("username",username1);
                        System.out.println("output after update: username="+username1);
                        startActivityForResult(intent, 1000);
                    }
                }).start();
            case R.id.update_journal_fab_back:
                Intent intent4 = getIntent();
                //final int authorid3 = intent3.getIntExtra("authorid", 2);
                String username3 = intent4.getStringExtra("username");
                Intent intent5 = new Intent(UpdateJournoteActivity.this, MainActivity.class);
                intent5.putExtra("username",username3);
                startActivityForResult(intent5, 1000);
                //MainActivity.startActivity(this, username3);
                break;
        }
    }

    @OnClick(R.id.common2_iv_noti)
    public void setJournoteNoti(View v) {
        //设置journote的提醒以及时间
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu_noti, popupMenu.getMenu());
        popupMenu.show();
        final String tagAdd = mUpdateJournoteTvTag.getText().toString();
        final String title = mUpdateJournalEtTitle.getText().toString();
        final String content = mUpdateJournalEtContent.getText().toString();
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final String journoteTag = intent.getStringExtra("tag");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_noti_setdate:
                        Intent intent = new Intent(UpdateJournoteActivity.this, DateTimePickerActivity.class);
                        intent.putExtra("tag", tagAdd);
                        intent.putExtra("title", title);
                        intent.putExtra("content", content);
                        intent.putExtra("username", username);
                        startActivityForResult(intent, 1000);
                        //DateTimePickerActivity.startActivity(UpdateJournoteActivity.this, tagAdd);
                        return true;
                    case R.id.menu_noti_finished:
                    case R.id.menu_noti_delete:
                        new Thread(new Runnable() {
                          @Override
                          public void run() {
                            System.out.println("notificationdelete"+journoteTag);
                            String result = HttpConnnect.notificationDeleteByPost(journoteTag);
                            Bundle bundle = new Bundle();
                            bundle.putString("result", result);
                            Message message = new Message();
                            message.setData(bundle);
                            message.what = DELETE_NOTI_JUDGE;
                            handler.sendMessage(message);
                          }
                        }).start();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    @OnClick(R.id.common2_iv_label)
    public void setJournoteByLabel(View v) {
        //设置Journote的标签；
        final String tagAdd = mUpdateJournoteTvTag.getText().toString();

        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu_label_set, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_label_delete:
                        new Thread(new Runnable() {
                          @Override
                          public void run() {
                            String result = HttpConnnect.labelUpdateByPost(tagAdd, 0);
                            Bundle bundle = new Bundle();
                            bundle.putString("result", result);
                            Message message = new Message();
                            message.setData(bundle);
                            message.what = SET_LABEL_0_JUDGE;
                            handler.sendMessage(message);
                          }
                        }).start();
                        return true;
                    case R.id.menu_label_set1:
                      new Thread(new Runnable() {
                        @Override
                        public void run() {
                          String result = HttpConnnect.labelUpdateByPost(tagAdd, 1);
                          Bundle bundle = new Bundle();
                          bundle.putString("result", result);
                          Message message = new Message();
                          message.setData(bundle);
                          message.what = SET_LABEL_1_JUDGE;
                          handler.sendMessage(message);
                        }
                      }).start();
                        return true;
                    case R.id.menu_label_set2:
                      new Thread(new Runnable() {
                        @Override
                        public void run() {
                          String result = HttpConnnect.labelUpdateByPost(tagAdd, 2);
                          Bundle bundle = new Bundle();
                          bundle.putString("result", result);
                          Message message = new Message();
                          message.setData(bundle);
                          message.what = SET_LABEL_2_JUDGE;
                          handler.sendMessage(message);
                        }
                      }).start();
                        return true;
                    case R.id.menu_label_set3:
                      new Thread(new Runnable() {
                        @Override
                        public void run() {
                          String result = HttpConnnect.labelUpdateByPost(tagAdd, 5);
                          Bundle bundle = new Bundle();
                          bundle.putString("result", result);
                          Message message = new Message();
                          message.setData(bundle);
                          message.what = SET_LABEL_5_JUDGE;
                          handler.sendMessage(message);
                        }
                      }).start();
                        return true;
                    case R.id.menu_label_set4:
                      new Thread(new Runnable() {
                        @Override
                        public void run() {
                          String result = HttpConnnect.labelUpdateByPost(tagAdd, 5);
                          Bundle bundle = new Bundle();
                          bundle.putString("result", result);
                          Message message = new Message();
                          message.setData(bundle);
                          message.what = SET_LABEL_5_JUDGE;
                          handler.sendMessage(message);
                        }
                      }).start();
                        return true;
                    case R.id.menu_label_set5:
                      new Thread(new Runnable() {
                        @Override
                        public void run() {
                          String result = HttpConnnect.labelUpdateByPost(tagAdd, 5);
                          Bundle bundle = new Bundle();
                          bundle.putString("result", result);
                          Message message = new Message();
                          message.setData(bundle);
                          message.what = SET_LABEL_5_JUDGE;
                          handler.sendMessage(message);
                        }
                      }).start();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        MainActivity.startActivity(this, username);
    }

}
