package com.example.journote.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journote.R;
import com.example.journote.db.HttpConnnect;
import com.example.journote.utils.AppManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private final static int LOGIN_JUDGE = 1;
    private int RequestCode = 1;

    @Bind(R.id.login_name)
    EditText mLoginName;
    @Bind(R.id.login_password)
    EditText mLoginPassword;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.link_register)
    TextView mLinkRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mLoginName.setText(intent.getStringExtra("username"));
        mLoginPassword.setText(intent.getStringExtra("passord"));

        SharedPreferences sharedPreferences = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        Boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if (isLogin){
          Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
          intent1.putExtra("username", username);
          startActivity(intent1);
        }



    }
    public static void startActivity(Context context){
        Intent intent = new Intent(context, LoginActivity.class);

        //intent.putExtra("authorid", authorid);
        context.startActivity(intent);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1&&resultCode ==2){
            mLoginName.setText(data.getStringExtra("username"));
            mLoginPassword.setText(data.getStringExtra("password"));
        }
    }

//    private class MyHandler extends Handler {
//        //持有弱引用MainActivity,GC回收时会被回收掉.
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case LOGIN_JUDGE:{
//                    Bundle bundle = new Bundle();
//                    bundle = msg.getData();
//                    String result = bundle.getString("result");
//                    try{
//                        if (result.equals("success")){
//                            Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(LoginActivity.this,"登录失败，请重新尝试！",Toast.LENGTH_SHORT).show();
//                        }
//                    }catch (NullPointerException e){
//                        e.printStackTrace();
//                    }
//                }
//                break;
//            }
//        }
//    }
    Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg){
        super.handleMessage(msg);
        switch (msg.what){
            case LOGIN_JUDGE:{
                Bundle bundle = new Bundle();
                bundle = msg.getData();
                String result = bundle.getString("result");
                String username = bundle.getString("username");
                String password = bundle.getString("password");
                try{
                    if (result.equals("success")){
                        Toast.makeText(LoginActivity.this,username+"登录成功！",Toast.LENGTH_SHORT).show();
                        //Intent intent =new Intent();
                        //MainActivity.startActivity(LoginActivity.this, username);
                        SharedPreferences.Editor editor = LoginActivity.this.getSharedPreferences("LoginStatus", MODE_PRIVATE).edit();
                        editor.putBoolean("isLogin", true);
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //bundle.putString("username",username);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"登录失败，请重新尝试！",Toast.LENGTH_SHORT).show();
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
            break;
        }
    }
};

    @OnClick(R.id.btn_login)
    public void onClickLogin(){
        if(!validate()){
            Toast.makeText(LoginActivity.this, "重新输入", Toast.LENGTH_LONG).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String username1 = mLoginName.getText().toString();
                String password1 = mLoginPassword.getText().toString();
                String result = HttpConnnect.LoginByPost(username1, password1);
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                bundle.putString("username", username1);
                bundle.putString("password", password1);
                Message message = new Message();
                message.setData(bundle);
                message.what = LOGIN_JUDGE;
                //myHandler.sendMessage(message);
                handler.sendMessage(message);
            }
        }).start();
    }
    @OnClick(R.id.link_register)
    public void onClickLinkRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        //startActivityForResult(intent, RequestCode);
        startActivity(intent);
        //RegisterActivity.startActivity(this);
    }


    public boolean validate(){
        boolean valid = true;
        String username = mLoginName.getText().toString();
        String password = mLoginPassword.getText().toString();
        if(username.isEmpty() || username.length()<4 ||username.length()>10){
            mLoginName.setError("enter between 4 and 10 characters");
            valid = false;
        }else {
            mLoginName.setError(null);
        }
        if(password.isEmpty() || password.length()<4 ||password.length()>10){
            mLoginPassword.setError("enter between 4 and 10 characters");
            valid = false;
        }else {
            mLoginPassword.setError(null);
        }
        return valid;
    }
}
