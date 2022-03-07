package com.example.journote.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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

public class RegisterActivity extends AppCompatActivity {

    private int ResultCode = 2;
    private final static int REGISTER_JUDGE = 2;

    @Bind(R.id.register_name)
    EditText mRegisterName;
    @Bind(R.id.register_password)
    EditText mRegisterPassword;
    @Bind(R.id.register_password_check)
    EditText mRegisterPasswordCheck;
    @Bind(R.id.btn_register)
    Button mBtnRegister;
    @Bind(R.id.link_login)
    TextView mLinkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);


    }
    public static void startActivity(Context context){
        Intent intent = new Intent(context, RegisterActivity.class);
        //intent.putExtra("authorid", authorid);
        context.startActivity(intent);

    }
//    private class MyHandler extends Handler {
//        //持有弱引用MainActivity,GC回收时会被回收掉.
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case REGISTER_JUDGE:{
//                    Bundle bundle = new Bundle();
//                    bundle = msg.getData();
//                    String result = bundle.getString("result");
//                    //Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
//                    try {
//                        if (result.equals("success")) {
//                            Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent();
//                            intent.putExtra("usernmae",mRegisterName.getText().toString());
//                            intent.putExtra("password",mRegisterPassword.getText().toString());
//                            setResult(ResultCode,intent);//向上一级发送数据
//                            finish();
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
            case REGISTER_JUDGE:{
                Bundle bundle = new Bundle();
                bundle = msg.getData();
                String result = bundle.getString("result");
                //Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
                try {
                    if (result.equals("success")) {
                        Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("username",mRegisterName.getText().toString());
                        intent.putExtra("password",mRegisterPassword.getText().toString());
                        setResult(ResultCode,intent);//向上一级发送数据
                        finish();
                        LoginActivity.startActivity(RegisterActivity.this);
                    } else if (result.equals("fail")){
                        Toast.makeText(RegisterActivity.this,"该用户名已存在",Toast.LENGTH_SHORT).show();
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
            break;
        }
    }
};
    @OnClick(R.id.btn_register)
    public void onClickLogin(){
        if(!validate()){
            Toast.makeText(RegisterActivity.this, "重新输入", Toast.LENGTH_LONG).show();
            return;
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                String username = mRegisterName.getText().toString();
                String password = mRegisterPassword.getText().toString();
                String passwordCheck = mRegisterPasswordCheck.getText().toString();
                String result = HttpConnnect.RegisterByPost(username, password);
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                Message message = new Message();
                message.setData(bundle);
                message.what = REGISTER_JUDGE;
//                MyHandler myHandler = new MyHandler();
//                myHandler.sendMessage(message);
                handler.sendMessage(message);
                //LoginActivity.startActivity(RegisterActivity.this);
            }
        }).start();
    }
    @OnClick(R.id.link_login)
    public void onClickLinkLogin(){
        LoginActivity.startActivity(this);
    }

    public boolean validate(){
        boolean valid = true;
        String username = mRegisterName.getText().toString();
        String password = mRegisterPassword.getText().toString();
        String passwordCheck = mRegisterPasswordCheck.getText().toString();

        if(username.isEmpty() || username.length()<4 ||username.length()>10){
            mRegisterName.setError("enter between 4 and 10 characters");
            valid = false;
        }else {
            mRegisterName.setError(null);
        }
        if(password.isEmpty() || password.length()<4 ||password.length()>10){
            mRegisterPassword.setError("enter between 4 and 10 characters");
            valid = false;
        }else {
            mRegisterPassword.setError(null);
        }
        if(passwordCheck.isEmpty() || password.length()<4 ||password.length()>10){
            mRegisterPasswordCheck.setError("enter between 4 and 10 characters");
            valid = false;
        }else {
            mRegisterPasswordCheck.setError(null);
        }
        if(!password.equals(passwordCheck)){
            Toast.makeText(RegisterActivity.this,"两次密码不一致！",Toast.LENGTH_LONG).show();
            valid = false;
        }
        return valid;
    }
}
