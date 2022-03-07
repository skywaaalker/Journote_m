package com.example.journote.db;

import android.app.Notification;
import android.util.Log;
import android.widget.Toast;

import com.example.journote.bean.JournoteBean;
import com.example.journote.bean.NotificationBean;
import com.example.journote.ui.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpConnnect {
    public static String LoginByPost(String username, String password){
        String address = "http://47.102.211.21:8080/JournoteServlet/journotelogin.do";
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "username=" + URLEncoder.encode(username, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public static String RegisterByPost(String username, String password){
        String address = "http://47.102.211.21:8080/JournoteServlet/journoteregister.do";
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "username=" + URLEncoder.encode(username, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    public static List<JournoteBean> getjournal(String username){
        String address = "http://47.102.211.21:8080/JournoteServlet/journalqueryservlet.do";
        String result = "";
        List <JournoteBean> mJournoteBeanList = new ArrayList<JournoteBean>();
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "username=" + URLEncoder.encode(username, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
                //在logcat中输出返回的json数据
                //System.out.print(result);
                //Toast.makeText(this,username+"登录成功！",Toast.LENGTH_SHORT).show();
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    //Log.i("cctao-json-object",jsonObject.toString());
                    JournoteBean journoteBean = new JournoteBean();
                    journoteBean.setDate(jsonObject.getString("date"));
                    journoteBean.setTitle(jsonObject.getString("title"));
                    journoteBean.setContent(jsonObject.getString("content"));
                    journoteBean.setIsjournal(jsonObject.getInt("isjournal"));
                    journoteBean.setHasnoti(jsonObject.getInt("hasnoti"));
                    journoteBean.setLabel(jsonObject.getInt("label"));
                    journoteBean.setTag(jsonObject.getString("tag"));
                    journoteBean.setUsername(jsonObject.getString("username"));
                    mJournoteBeanList.add(journoteBean);
                }
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return mJournoteBeanList;
    }
    public static List<JournoteBean> getjournalByLabel(String username, int label){
        String address = "http://47.102.211.21:8080/JournoteServlet/journalquerybylabelservlet.do";
        String result = "";
        List <JournoteBean> mJournoteBeanList = new ArrayList<JournoteBean>();
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

//            String data = "username=" + URLEncoder.encode(username, "UTF-8") +
//                    "&password=" + URLEncoder.encode(password, "UTF-8");
            //String data = "username=" + URLEncoder.encode(username, "UTF-8")+ "&label="+URLEncoder.encode(String.valueOf(label), "UTF-8");
            String data = "username=" + URLEncoder.encode(username, "UTF-8")+ "&label="+label;
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());

                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    Log.i("cctao-json-object",jsonObject.toString());
                    JournoteBean journoteBean = new JournoteBean();
                    journoteBean.setDate(jsonObject.getString("date"));
                    journoteBean.setTitle(jsonObject.getString("title"));
                    journoteBean.setContent(jsonObject.getString("content"));
                    journoteBean.setIsjournal(jsonObject.getInt("isjournal"));
                    journoteBean.setHasnoti(jsonObject.getInt("hasnoti"));
                    journoteBean.setLabel(jsonObject.getInt("label"));
                    journoteBean.setTag(jsonObject.getString("tag"));
                    journoteBean.setUsername(jsonObject.getString("username"));
                    mJournoteBeanList.add(journoteBean);
                }
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return mJournoteBeanList;
    }
    public static List<JournoteBean> getjournalByNoti(String username){
        String address = "http://47.102.211.21:8080/JournoteServlet/journalquerybynotiservlet.do";
        String result = "";
        List <JournoteBean> mJournoteBeanList = new ArrayList<JournoteBean>();
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

//            String data = "username=" + URLEncoder.encode(username, "UTF-8") +
//                    "&password=" + URLEncoder.encode(password, "UTF-8");
            String data = "username=" + URLEncoder.encode(username, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());

                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    Log.i("cctao-json-object",jsonObject.toString());
                    JournoteBean journoteBean = new JournoteBean();
                    journoteBean.setDate(jsonObject.getString("date"));
                    journoteBean.setTitle(jsonObject.getString("title"));
                    journoteBean.setContent(jsonObject.getString("content"));
                    journoteBean.setIsjournal(jsonObject.getInt("isjournal"));
                    journoteBean.setHasnoti(jsonObject.getInt("hasnoti"));
                    journoteBean.setLabel(jsonObject.getInt("label"));
                    journoteBean.setTag(jsonObject.getString("tag"));
                    journoteBean.setUsername(jsonObject.getString("username"));
                    mJournoteBeanList.add(journoteBean);
                }
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return mJournoteBeanList;
    }
    public static List<JournoteBean> getnote(String username){
        String address = "http://47.102.211.21:8080/JournoteServlet/notequeryservlet.do";
        String result = "";
        List <JournoteBean> mJournoteBeanList = new ArrayList<JournoteBean>();
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

//            String data = "username=" + URLEncoder.encode(username, "UTF-8") +
//                    "&password=" + URLEncoder.encode(password, "UTF-8");
            String data = "username=" + URLEncoder.encode(username, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());

                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    //Log.i("cctao-json-object",jsonObject.toString());
                    JournoteBean journoteBean = new JournoteBean();
                    journoteBean.setDate(jsonObject.getString("date"));
                    journoteBean.setTitle(jsonObject.getString("title"));
                    journoteBean.setContent(jsonObject.getString("content"));
                    journoteBean.setIsjournal(jsonObject.getInt("isjournal"));
                    journoteBean.setHasnoti(jsonObject.getInt("hasnoti"));
                    journoteBean.setLabel(jsonObject.getInt("label"));
                    journoteBean.setTag(jsonObject.getString("tag"));
                    journoteBean.setUsername(jsonObject.getString("username"));
                    mJournoteBeanList.add(journoteBean);
                }
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return mJournoteBeanList;
    }
    public static List<JournoteBean> getnoteByLabel(String username, int label){
        String address = "http://47.102.211.21:8080/JournoteServlet/notequerybylabelservlet.do";
        String result = "";
        List <JournoteBean> mJournoteBeanList = new ArrayList<JournoteBean>();
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

//            String data = "username=" + URLEncoder.encode(username, "UTF-8") +
//                    "&password=" + URLEncoder.encode(password, "UTF-8");
            String data = "username=" + URLEncoder.encode(username, "UTF-8")+"&label="+label;
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());

                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    Log.i("cctao-json-object",jsonObject.toString());
                    JournoteBean journoteBean = new JournoteBean();
                    journoteBean.setDate(jsonObject.getString("date"));
                    journoteBean.setTitle(jsonObject.getString("title"));
                    journoteBean.setContent(jsonObject.getString("content"));
                    journoteBean.setIsjournal(jsonObject.getInt("isjournal"));
                    journoteBean.setHasnoti(jsonObject.getInt("hasnoti"));
                    journoteBean.setLabel(jsonObject.getInt("label"));
                    journoteBean.setTag(jsonObject.getString("tag"));
                    journoteBean.setUsername(jsonObject.getString("username"));
                    mJournoteBeanList.add(journoteBean);
                }
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return mJournoteBeanList;
    }
    public static List<JournoteBean> getnoteByNoti(String username){
        String address = "http://47.102.211.21:8080/JournoteServlet/notequerybynotiservlet.do";
        String result = "";
        List <JournoteBean> mJournoteBeanList = new ArrayList<JournoteBean>();
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

//            String data = "username=" + URLEncoder.encode(username, "UTF-8") +
//                    "&password=" + URLEncoder.encode(password, "UTF-8");
            String data = "username=" + URLEncoder.encode(username, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());

                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    Log.i("cctao-json-object",jsonObject.toString());
                    JournoteBean journoteBean = new JournoteBean();
                    journoteBean.setDate(jsonObject.getString("date"));
                    journoteBean.setTitle(jsonObject.getString("title"));
                    journoteBean.setContent(jsonObject.getString("content"));
                    journoteBean.setIsjournal(jsonObject.getInt("isjournal"));
                    journoteBean.setHasnoti(jsonObject.getInt("hasnoti"));
                    journoteBean.setLabel(jsonObject.getInt("label"));
                    journoteBean.setTag(jsonObject.getString("tag"));
                    journoteBean.setUsername(jsonObject.getString("username"));
                    mJournoteBeanList.add(journoteBean);
                }
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return mJournoteBeanList;
    }
    public static String journalEditByPost(String title, String content, String tag){
        String address = "http://47.102.211.21:8080/JournoteServlet/journaleditservlet.do";
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "title=" + URLEncoder.encode(title, "UTF-8") +
                    "&content=" + URLEncoder.encode(content, "UTF-8")+
                    "&tag=" + URLEncoder.encode(tag, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    public static String noteEditByPost(String title, String content, String tag){
        String address = "http://47.102.211.21:8080/JournoteServlet/noteeditservlet.do";
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "title=" + URLEncoder.encode(title, "UTF-8") +
                    "&content=" + URLEncoder.encode(content, "UTF-8")+
                    "&tag=" + URLEncoder.encode(tag, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    public static String journalInsertByPost(String title, String content, String username){
        String address = "http://47.102.211.21:8080/JournoteServlet/journalinsertservlet.do";
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "title=" + URLEncoder.encode(title, "UTF-8") +
                    "&content=" + URLEncoder.encode(content, "UTF-8")+
                    "&username=" + URLEncoder.encode(username, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    public static String noteInsertByPost(String title, String content, String username){
        String address = "http://47.102.211.21:8080/JournoteServlet/noteinsertservlet.do";
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "title=" + URLEncoder.encode(title, "UTF-8") +
                    "&content=" + URLEncoder.encode(content, "UTF-8")+
                    "&username=" + URLEncoder.encode(username, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    public static String DeleteByPost(String tag){
        String address = "http://47.102.211.21:8080/JournoteServlet/journotedeleteservlet.do";
        String result = "";
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "tag=" + URLEncoder.encode(tag, "UTF-8") ;
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
  public static String notificationInsertByPost(String journoteTag, String date_str){
    String address = "http://47.102.211.21:8080/JournoteServlet/notificationinsertservlet.do";
    String result = "";
    try {
      URL url = new URL(address);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setReadTimeout(5000);
      conn.setConnectTimeout(5000);
      conn.setUseCaches(false);

      String data = "journoteTag=" + URLEncoder.encode(journoteTag, "UTF-8") +
        "&date_str=" + URLEncoder.encode(date_str, "UTF-8");
      OutputStream out = conn.getOutputStream();
      out.write(data.getBytes());
      out.flush();
      out.close();
      conn.connect();

      if(conn.getResponseCode() == 200){
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        int len = 0;
        byte buffer[] = new byte[1024];
        while ((len = is.read(buffer)) != -1){
          message.write(buffer, 0, len);
        }
        is.close();
        message.close();
        result = new String(message.toByteArray());
      }
    }catch (MalformedURLException e){
      e.printStackTrace();
    }catch (IOException e){
      e.printStackTrace();
    }
    return result;
  }
  public static String notificationDeleteByPost(String journoteTag){
    String address = "http://47.102.211.21:8080/JournoteServlet/notificationdeleteservlet.do";
    String result = "";
    try {
      URL url = new URL(address);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setReadTimeout(5000);
      conn.setConnectTimeout(5000);
      conn.setUseCaches(false);

      String data = "journoteTag=" + URLEncoder.encode(journoteTag, "UTF-8");
      OutputStream out = conn.getOutputStream();
      out.write(data.getBytes());
      out.flush();
      out.close();
      conn.connect();

      if(conn.getResponseCode() == 200){
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        int len = 0;
        byte buffer[] = new byte[1024];
        while ((len = is.read(buffer)) != -1){
          message.write(buffer, 0, len);
        }
        is.close();
        message.close();
        result = new String(message.toByteArray());
      }
    }catch (MalformedURLException e){
      e.printStackTrace();
    }catch (IOException e){
      e.printStackTrace();
    }
    return result;
  }
  public static String notificationUpdateByPost(String journoteTag, String date_str){
    String address = "http://47.102.211.21:8080/JournoteServlet/notificationupdateservlet.do";
    String result = "";
    try {
      URL url = new URL(address);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setReadTimeout(5000);
      conn.setConnectTimeout(5000);
      conn.setUseCaches(false);

      String data = "journoteTag=" + URLEncoder.encode(journoteTag, "UTF-8") +
        "&date_str=" + URLEncoder.encode(date_str, "UTF-8");
      OutputStream out = conn.getOutputStream();
      out.write(data.getBytes());
      out.flush();
      out.close();
      conn.connect();

      if(conn.getResponseCode() == 200){
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        int len = 0;
        byte buffer[] = new byte[1024];
        while ((len = is.read(buffer)) != -1){
          message.write(buffer, 0, len);
        }
        is.close();
        message.close();
        result = new String(message.toByteArray());
      }
    }catch (MalformedURLException e){
      e.printStackTrace();
    }catch (IOException e){
      e.printStackTrace();
    }
    return result;
  }

  public static NotificationBean getNotification(String journoteTag){
    String address = "http://47.102.211.21:8080/JournoteServlet/notificationqueryservlet.do";
    String result = "";
    List <NotificationBean> mNotificationBeanList = new ArrayList<NotificationBean>();
    NotificationBean mNotificationBean = new NotificationBean();
    try {
      URL url = new URL(address);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setReadTimeout(5000);
      conn.setConnectTimeout(5000);
      conn.setUseCaches(false);

      String data = "journoteTag=" + URLEncoder.encode(journoteTag, "UTF-8");
      OutputStream out = conn.getOutputStream();
      out.write(data.getBytes());
      out.flush();
      out.close();
      conn.connect();

      if(conn.getResponseCode() == 200){
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        int len = 0;
        byte buffer[] = new byte[1024];
        while ((len = is.read(buffer)) != -1){
          message.write(buffer, 0, len);
        }
        is.close();
        message.close();
        result = new String(message.toByteArray());
        //在logcat中输出返回的json数据
        //System.out.print(result);
        //Toast.makeText(this,username+"登录成功！",Toast.LENGTH_SHORT).show();
        JSONArray jsonArray = new JSONArray(result);
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonArray.length(); i++){
          jsonObject = jsonArray.getJSONObject(i);
          //Log.i("cctao-json-object",jsonObject.toString());
          mNotificationBean.setJournoteTag(jsonObject.getString("journoteTag"));
          mNotificationBean.setDateStr(jsonObject.getString("date_str"));
        }
      }
    }catch (MalformedURLException e){
      e.printStackTrace();
    }catch (IOException e){
      e.printStackTrace();
    }catch (JSONException e){
      e.printStackTrace();
    }
    //return mJournoteBeanList;
    return mNotificationBean;
  }

  public static String labelUpdateByPost(String journoteTag, int label){
    String address = "http://47.102.211.21:8080/JournoteServlet/labelupdateservlet.do";
    String result = "";
    try {
      URL url = new URL(address);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setReadTimeout(5000);
      conn.setConnectTimeout(5000);
      conn.setUseCaches(false);

      String data = "journoteTag=" + URLEncoder.encode(journoteTag, "UTF-8") +
        "&label=" + label;
      OutputStream out = conn.getOutputStream();
      out.write(data.getBytes());
      out.flush();
      out.close();
      conn.connect();

      if(conn.getResponseCode() == 200){
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        int len = 0;
        byte buffer[] = new byte[1024];
        while ((len = is.read(buffer)) != -1){
          message.write(buffer, 0, len);
        }
        is.close();
        message.close();
        result = new String(message.toByteArray());
      }
    }catch (MalformedURLException e){
      e.printStackTrace();
    }catch (IOException e){
      e.printStackTrace();
    }
    return result;
  }

  public static String isJournoteHasnotiByPost(String journoteTag){
    String address = "http://47.102.211.21:8080/JournoteServlet/isjournotehasnotiservlet.do";
    String result = "";
    try {
      URL url = new URL(address);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setReadTimeout(5000);
      conn.setConnectTimeout(5000);
      conn.setUseCaches(false);

      String data = "journoteTag=" + URLEncoder.encode(journoteTag, "UTF-8");
      OutputStream out = conn.getOutputStream();
      out.write(data.getBytes());
      out.flush();
      out.close();
      conn.connect();

      if(conn.getResponseCode() == 200){
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        int len = 0;
        byte buffer[] = new byte[1024];
        while ((len = is.read(buffer)) != -1){
          message.write(buffer, 0, len);
        }
        is.close();
        message.close();
        result = new String(message.toByteArray());
      }
    }catch (MalformedURLException e){
      e.printStackTrace();
    }catch (IOException e){
      e.printStackTrace();
    }
    return result;
  }

}
