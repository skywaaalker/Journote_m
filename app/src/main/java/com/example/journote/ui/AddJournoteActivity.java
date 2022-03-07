package com.example.journote.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.journote.R;
import com.example.journote.db.HttpConnnect;
import com.example.journote.db.JournoteDBOpenHelper;
import com.example.journote.db.JournoteDatabaseHelper;
import com.example.journote.utils.AppManager;
import com.example.journote.utils.GetDate;
import com.example.journote.utils.StatusBarCompat;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddJournoteActivity extends AppCompatActivity {

    @Bind(R.id.add_journal_tv_date)
    TextView mAddJournalTvDate;
    @Bind(R.id.add_journal_et_title)
    EditText mAddJournalEtTitle;
    @Bind(R.id.add_journal_et_content)
    EditText mAddJournalEtContent;
    @Bind(R.id.add_journal_fab_add)
    FloatingActionButton mAddJournalFabAdd;
    @Bind(R.id.add_journal_fab_back)
    FloatingActionButton mAddJournalFabBack;

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

    private JournoteDatabaseHelper mHelper;
    private JournoteDBOpenHelper mSqlHelper;
    private final static int INSERT_JUDGE = 10;
    private final static int INSERT_JUDGE_BACK = 11;
    //private PreparedStatement ps = null;

    public static void startActivity(Context context, String username){
        Intent intent = new Intent(context, AddJournoteActivity.class);
        intent.putExtra("username", username);
        context.startActivity(intent);
    }
    public static void startActivity(Context context, String title, String content, String username){
        Intent intent = new Intent(context, AddJournoteActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("username", username);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent = getIntent();
        mAddJournalEtTitle.setText(intent.getStringExtra("title"));
        mAddJournalEtContent.setText(intent.getStringExtra("content"));
        StatusBarCompat.compat(this, Color.parseColor("#161414"));

        mCommon2TvTitle.setText("添加日记");
        mCommon2IvLabel.setVisibility(View.INVISIBLE);
        mCommon2IvNoti.setVisibility(View.INVISIBLE);
        mAddJournalTvDate.setText(GetDate.getDate());
        //mHelper = new JournoteDatabaseHelper(this, "Journote.db", null, 1);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case INSERT_JUDGE:{
                    Bundle bundle = new Bundle();
                    bundle = msg.getData();
                    String result = bundle.getString("result");
                    try{
                        if (result.equals("success")){
                            Toast.makeText(AddJournoteActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                break;
                case INSERT_JUDGE_BACK:{
                    Bundle bundle = new Bundle();
                    bundle = msg.getData();
                    String result = bundle.getString("result");
                    try{
                        if (result.equals("success")){
                            Toast.makeText(AddJournoteActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    };
    @OnClick({R.id.common2_iv_back, R.id.add_journal_et_title, R.id.add_journal_et_content, R.id.add_journal_fab_back,
    R.id.add_journal_fab_add})
    public void onClick(View view){
            switch (view.getId()){
                case R.id.common2_iv_back:
                    Intent intent_b = getIntent();
                    //int authorid1 = intent_b.getIntExtra("authorid", 2);
                    String username1 = intent_b.getStringExtra("username");
                    Intent intent = new Intent(AddJournoteActivity.this, MainActivity.class);
                    intent.putExtra("username",username1);
                    startActivity(intent);
                    //MainActivity.startActivity(this, username1);
                case R.id.add_journal_et_title:
                    break;
                case R.id.add_journal_et_content:
                    break;
                case R.id.add_journal_fab_add:

                    String date = GetDate.getDate().toString();
                    String tag = String.valueOf(System.currentTimeMillis());
                    String title = mAddJournalEtTitle.getText().toString() + "";
                    String content = mAddJournalEtContent.getText().toString() + "";

                    Intent intent_add = getIntent();
                    String username = intent_add.getStringExtra("username");

                    if(!title.equals("") || !content.equals("")){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                //Toast.makeText(AddJournoteActivity.this,title+content, Toast.LENGTH_SHORT).show();
                                //String result = HttpConnnect.journalInsertByPost(mAddJournalEtTitle.getText().toString(), mAddJournalEtContent.getText().toString(),username);
                                String result = HttpConnnect.journalInsertByPost(title,content,username);
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("result", result);
                                Message message = new Message();
                                message.setData(bundle1);
                                message.what = INSERT_JUDGE;
                                handler.sendMessage(message);
                                //MainActivity.startActivity(AddJournoteActivity.this,username);
                                Intent intent = new Intent(AddJournoteActivity.this, MainActivity.class);
                                intent.putExtra("username",username);
                                startActivity(intent);
                            }
                        }).start();
                    }
                    break;
                case R.id.add_journal_fab_back:
                    final String dateBack = GetDate.getDate().toString();
                    final String tagBack = String.valueOf(System.currentTimeMillis());
                    final String titleBack = mAddJournalEtTitle.getText().toString();
                    final String contentBack = mAddJournalEtContent.getText().toString();
                    Intent intent_back = getIntent();
                    String username_back = intent_back.getStringExtra("username");
                    if(!titleBack.isEmpty() || !contentBack.isEmpty()){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setMessage("是否保存日记内容？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        String result = HttpConnnect.journalInsertByPost(titleBack, contentBack, username_back);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("result", result);
                                        Message message = new Message();
                                        message.setData(bundle);
                                        message.what = INSERT_JUDGE_BACK;
                                        handler.sendMessage(message);
                                        MainActivity.startActivity(AddJournoteActivity.this,username_back);
                                    }
                                }).start();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.startActivity(AddJournoteActivity.this, username_back);
                            }
                        }).show();
                    }else {
                        MainActivity.startActivity(this, username_back);
                    }
                    break;
            }
        }
        @Override
        public void onBackPressed(){
            Intent intent = getIntent();
            String username = intent.getStringExtra("username");
            super.onBackPressed();
            MainActivity.startActivity(this, username);
        }

}


