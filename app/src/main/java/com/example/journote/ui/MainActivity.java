package com.example.journote.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journote.R;
import com.example.journote.bean.JournoteBean;
import com.example.journote.db.DBService;
import com.example.journote.db.HttpConnnect;
import com.example.journote.db.JournoteDBOpenHelper;
import com.example.journote.db.JournoteDatabaseHelper;
import com.example.journote.event.StartUpdateJournoteEvent;
import com.example.journote.utils.AppManager;
import com.example.journote.utils.GetDate;
import com.example.journote.utils.SpHelper;
import com.example.journote.utils.StatusBarCompat;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.common_iv_back)
    ImageView mCommonIvBack;
    @Bind(R.id.common_tv_title)
    TextView mCommonTvTitle;
    @Bind(R.id.common_iv_menu)
    ImageView mCommonIvMenu;
    @Bind(R.id.common_title_ll_1)
    LinearLayout mCommonTitleLl1;


    @Bind(R.id.journal_main_searchview)
    SearchView mJouranlMainSearchView;
    @Bind(R.id.journal_main_notification)
    ImageView mJouranlMainNotification;
    @Bind(R.id.journal_main_label)
    ImageView mJouranlMainLabel;

    @Bind(R.id.main_rv_show_journal)
    RecyclerView mMainRvShowJournal;
    //@Bind(R.id.main_rv_show_journal)
    //RecyclerView mMainRvShowNote;
    @Bind(R.id.journal_main_fab_add)
    FloatingActionButton mJournalMainFabAdd;
    @Bind(R.id.journal_main_rl_main)
    RelativeLayout mJournalMainRlMain;
    @Bind(R.id.journal_main_ll_main)
    LinearLayout mJournalMainLlMain;

    private List<JournoteBean> mJournoteBeanList;
    private JournoteDatabaseHelper mHelper;
    private int mEditPosition = -1;

    private final static int SELECT_ALL = 2;
    private final static int SELECT_LABEL_1 = 3;
    private final static int SELECT_LABEL_2 = 4;
    private final static int SELECT_LABEL_3 = 5;
    private final static int SELECT_LABEL_4 = 6;
    private final static int SELECT_LABEL_5 = 7;
    private final static int SELECT_NOTI = 8;
    //private DBService dbService;
    public static void startActivity(Context context, String username){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("username", username);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        StatusBarCompat.compat(this, Color.parseColor("#161414"));
        //mHelper = new JournoteDatabaseHelper(this, "Journote.db", null, 1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        EventBus.getDefault().register(this);
        //SpHelper spHelper = SpHelper.getInstance(this);
        //getJournoteBeanList();
        mJouranlMainSearchView.clearFocus();
        mJouranlMainSearchView.setIconifiedByDefault(false);
        setListeners();
        Intent intent = getIntent();
        String username_global = intent.getStringExtra("username");

        Handler handlerGetjournallist = new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                switch (msg.what){
                    case SELECT_ALL:{

                        Bundle bundle = new Bundle();
                        bundle = msg.getData();
                        ArrayList list = bundle.getParcelableArrayList("journallist");
                        mJournoteBeanList = (List<JournoteBean>)list;
                        mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));

                    }
                    break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                mJournoteBeanList = HttpConnnect.getjournal(username);
                Bundle bundle1 = new Bundle();
                bundle1.putParcelableArrayList("journallist", (ArrayList<? extends Parcelable>)mJournoteBeanList);
                //bundle.putSerializable("journotelist", mJournoteBeanList);
                Message message = new Message();
                message.setData(bundle1);
                message.what = SELECT_ALL;
                handlerGetjournallist.sendMessage(message);
            }
        }).start();

        initTitle();
//        mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(this));
//        mMainRvShowJournal.setAdapter(new JournoteAdapter(this, mJournoteBeanList));

    }
    public void initTitle(){
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String username = bundle.getString("username");
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mCommonTvTitle.setText(username+"の日记本");
        mCommonIvBack.setVisibility(View.INVISIBLE);
    }
    protected void setListeners() {
      mJouranlMainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
          return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
          final List<JournoteBean> journoteBeanListFiltered = filter(mJournoteBeanList, s);
          mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, journoteBeanListFiltered));
          return true;
        }
      });
      mJouranlMainSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
        @Override
        public boolean onClose() {
          mJouranlMainSearchView.setQuery("", false);
          mJouranlMainSearchView.clearFocus();
          return true;
        }
      });
    }
    private List<JournoteBean> filter(List<JournoteBean> models, String searchText){
      searchText = searchText.toLowerCase();
      final List<JournoteBean> filteredList = new ArrayList<>();
      for (JournoteBean model : models) {
        final String title_text = model.getTitle();
        final String content_text = model.getContent();
        final String date_text = model.getDate();
        if (title_text.contains(searchText) || content_text.contains(searchText) || date_text.contains(searchText)){
          filteredList.add(model);
        }
      }
      return  filteredList;
    }

    @Subscribe
    public void startUpdateJournoteActivity(StartUpdateJournoteEvent event){
        String title = mJournoteBeanList.get(event.getPosition()).getTitle();
        String content = mJournoteBeanList.get(event.getPosition()).getContent();
        String tag =mJournoteBeanList.get(event.getPosition()).getTag();
        //int authorid = mJournoteBeanList.get(event.getPosition()).getAuthorid();
        String username = mJournoteBeanList.get(event.getPosition()).getUsername();
        String date = mJournoteBeanList.get(event.getPosition()).getDate();
        UpdateJournoteActivity.startActivity(this, title, content, tag, username, date);

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @OnClick(R.id.journal_main_fab_add)
    public void onClickAddJournote(){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        Intent intent1 = new Intent(MainActivity.this, AddJournoteActivity.class);
        intent1.putExtra("username",username);
        startActivity(intent1);
    }
    private boolean noti_flag = false;
    //if noti_flag ==1 还没有进行选择,如果 = 0；已经通过提醒进行了选择；
    @OnClick(R.id.journal_main_notification)
    public void selectJournoteByNoti(){
        //显示有提醒的journote
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if (noti_flag) {
            noti_flag = false;
            //getJournoteBeanListByNoti();
            //getJournalHasNoti(username);
            //mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
            //mJouranlMainNotification.setImageResource(R.drawable.clock_fill);
            Handler handlerGetjournallistbynoti = new Handler(){
                @Override
                public void handleMessage(Message msg){
                    super.handleMessage(msg);
                    switch (msg.what){
                        case SELECT_NOTI:{
                            Bundle bundle = new Bundle();
                            bundle = msg.getData();
                            ArrayList list = bundle.getParcelableArrayList("journallistbynoti");
                            mJournoteBeanList = (List<JournoteBean>)list;
                            mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
                            mJouranlMainNotification.setImageResource(R.drawable.clock_fill);
                        }
                        break;
                    }
                }
            };
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mJournoteBeanList = HttpConnnect.getjournalByNoti(username);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("journallistbynoti", (ArrayList<? extends Parcelable>)mJournoteBeanList);
                    Message message = new Message();
                    message.setData(bundle);
                    message.what = SELECT_NOTI;
                    handlerGetjournallistbynoti.sendMessage(message);
                }
            }).start();
        }else{
            noti_flag = true;
            //getJournoteBeanList();
            //mJournoteBeanList = new ArrayList<>();
            //mJournoteBeanList = dbService.getJournal(authorid);
//            getJournal(username);
//            mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
//            mJouranlMainNotification.setImageResource(R.drawable.clock_green);
            Handler handlerGetjournallist = new Handler(){
                @Override
                public void handleMessage(Message msg){
                    super.handleMessage(msg);
                    switch (msg.what){
                        case SELECT_ALL:{
                            Bundle bundle = new Bundle();
                            bundle = msg.getData();
                            ArrayList list = bundle.getParcelableArrayList("journallist");
                            mJournoteBeanList = (List<JournoteBean>)list;
                            mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
                            mJouranlMainNotification.setImageResource(R.drawable.clock_green);

                        }
                        break;
                    }
                }
            };
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mJournoteBeanList = HttpConnnect.getjournal(username);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("journallist", (ArrayList<? extends Parcelable>)mJournoteBeanList);
                    Message message = new Message();
                    message.setData(bundle);
                    message.what = SELECT_ALL;
                    handlerGetjournallist.sendMessage(message);
                }
            }).start();
        }
    }

    @OnClick(R.id.common_iv_menu)
    public void switchJournalOrNote(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu_switch, popupMenu.getMenu());
        popupMenu.show();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_switch_journal:
                        return true;
                    case R.id.menu_switch_note:
                        Intent intent = new Intent(MainActivity.this, MainActivityNote.class);
                        intent.putExtra("username",username);
                        System.out.println("username after swithing to note:"+username);
                        startActivityForResult(intent, 1000);
                        return true;
                    case R.id.menu_logout:
                      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                      alertDialogBuilder.setMessage("确定要退出登录吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          SharedPreferences.Editor editor = MainActivity.this.getSharedPreferences("LoginStatus", MODE_PRIVATE).edit();
                          editor.putBoolean("isLogin", false);
                          editor.putString("username", "");
                          editor.putString("password", "");
                          editor.apply();
                          Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                          startActivity(intent1);
                        }
                      }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                      }).show();
                       return  true;

                    default:
                        return false;
                }
            }
        });
    }
    @OnClick(R.id.journal_main_label)
    public void selectJournoteByLabel(View v) {
        //根据标签显示journote
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu_label, popupMenu.getMenu());
        popupMenu.show();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_label_all:
                        //getJournal(username);
                        //mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
                        Handler handlerGetjournallist = new Handler(){
                            @Override
                            public void handleMessage(Message msg){
                                super.handleMessage(msg);
                                switch (msg.what){
                                    case SELECT_ALL:{
                                        Bundle bundle = new Bundle();
                                        bundle = msg.getData();
                                        ArrayList list = bundle.getParcelableArrayList("journallist");
                                        mJournoteBeanList = (List<JournoteBean>)list;
                                        mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));

                                    }
                                    break;
                                }
                            }
                        };
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                List<JournoteBean> JournalList = new ArrayList<JournoteBean>();
                                JournalList = HttpConnnect.getjournal(username);
                                //Toast.makeText(MainActivity.this,JournalList.get(0).getUsername()+"登录成功！",Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("journallist", (ArrayList<? extends Parcelable>) JournalList);

                                Message message = new Message();
                                message.setData(bundle);
                                message.what = SELECT_ALL;
                                handlerGetjournallist.sendMessage(message);
                            }
                        }).start();
                        return true;
                    case R.id.menu_label_1:
                        //getJournalByLabel(1, username);
                        //mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
                        Handler handlerGetjournallistlabel1 = new Handler(){
                            @Override
                            public void handleMessage(Message msg){
                                super.handleMessage(msg);
                                switch (msg.what){
                                    case SELECT_LABEL_1:{
                                        Bundle bundle = new Bundle();
                                        bundle = msg.getData();
                                        ArrayList list = bundle.getParcelableArrayList("journallistlabel1");
                                        mJournoteBeanList = (List<JournoteBean>)list;
                                        mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));

                                    }
                                    break;
                                }
                            }
                        };
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mJournoteBeanList = HttpConnnect.getjournalByLabel(username, 1);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("journallistlabel1", (ArrayList<? extends Parcelable>)mJournoteBeanList);
                                Message message = new Message();
                                message.setData(bundle);
                                message.what = SELECT_LABEL_1;
                                handlerGetjournallistlabel1.sendMessage(message);
                            }
                        }).start();
                        return true;
                    case R.id.menu_label_2:
                        //getJournalByLabel(2, username);
                        //mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
                        Handler handlerGetjournallistlabel2 = new Handler(){
                            @Override
                            public void handleMessage(Message msg){
                                super.handleMessage(msg);
                                switch (msg.what){
                                    case SELECT_LABEL_2:{
                                        Bundle bundle = new Bundle();
                                        bundle = msg.getData();
                                        ArrayList list = bundle.getParcelableArrayList("journallistlabel2");
                                        mJournoteBeanList = (List<JournoteBean>)list;
                                        mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));

                                    }
                                    break;
                                }
                            }
                        };
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mJournoteBeanList = HttpConnnect.getjournalByLabel(username, 2);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("journallistlabel2", (ArrayList<? extends Parcelable>)mJournoteBeanList);
                                Message message = new Message();
                                message.setData(bundle);
                                message.what = SELECT_LABEL_2;
                                handlerGetjournallistlabel2.sendMessage(message);
                            }
                        }).start();
                        return true;
                    case R.id.menu_label_3:
                        //getJournalByLabel(3, username);
                        //mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
                        Handler handlerGetjournallistlabel3 = new Handler(){
                            @Override
                            public void handleMessage(Message msg){
                                super.handleMessage(msg);
                                switch (msg.what){
                                    case SELECT_LABEL_3:{
                                        Bundle bundle = new Bundle();
                                        bundle = msg.getData();
                                        ArrayList list = bundle.getParcelableArrayList("journallistlabel3");
                                        mJournoteBeanList = (List<JournoteBean>)list;
                                        mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));

                                    }
                                    break;
                                }
                            }
                        };
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mJournoteBeanList = HttpConnnect.getjournalByLabel(username, 3);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("journallistlabel3", (ArrayList<? extends Parcelable>)mJournoteBeanList);
                                Message message = new Message();
                                message.setData(bundle);
                                message.what = SELECT_LABEL_3;
                                handlerGetjournallistlabel3.sendMessage(message);
                            }
                        }).start();
                        return true;
                    case R.id.menu_label_4:
                        Handler handlerGetjournallistlabel4 = new Handler(){
                            @Override
                            public void handleMessage(Message msg){
                                super.handleMessage(msg);
                                switch (msg.what){
                                    case SELECT_LABEL_4:{
                                        Bundle bundle = new Bundle();
                                        bundle = msg.getData();
                                        ArrayList list = bundle.getParcelableArrayList("journallistlabel4");
                                        mJournoteBeanList = (List<JournoteBean>)list;
                                        mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));

                                    }
                                    break;
                                }
                            }
                        };
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mJournoteBeanList = HttpConnnect.getjournalByLabel(username, 4);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("journallistlabel4", (ArrayList<? extends Parcelable>)mJournoteBeanList);
                                Message message = new Message();
                                message.setData(bundle);
                                message.what = SELECT_LABEL_4;
                                handlerGetjournallistlabel4.sendMessage(message);
                            }
                        }).start();
                        //getJournalByLabel(4, username);
                        //mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
                        return true;
                    case R.id.menu_label_5:
                        Handler handlerGetjournallistlabel5 = new Handler(){
                            @Override
                            public void handleMessage(Message msg){
                                super.handleMessage(msg);
                                switch (msg.what){
                                    case SELECT_LABEL_5:{
                                        Bundle bundle = new Bundle();
                                        bundle = msg.getData();
                                        ArrayList list = bundle.getParcelableArrayList("journallistlabel5");
                                        mJournoteBeanList = (List<JournoteBean>)list;
                                        mMainRvShowJournal.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));

                                    }
                                    break;
                                }
                            }
                        };
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mJournoteBeanList = HttpConnnect.getjournalByLabel(username, 5);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("journallistlabel5", (ArrayList<? extends Parcelable>)mJournoteBeanList);
                                Message message = new Message();
                                message.setData(bundle);
                                message.what = SELECT_LABEL_5;
                                handlerGetjournallistlabel5.sendMessage(message);
                            }
                        }).start();
                        //getJournalByLabel(5, username);
                        //mMainRvShowJournal.setAdapter(new JournoteAdapter(MainActivity.this, mJournoteBeanList));
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
        AppManager.getAppManager().AppExit(this);
    }
}
