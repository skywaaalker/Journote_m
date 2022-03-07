package com.example.journote.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class JournoteBean implements Parcelable {
    //private int id;
    private String date;
    private String title;
    private String content;
    private int isjournal; //isjournal = 1, isjournal; isjournal= 0, is note;
    private int hasnoti; //hasnoti=1, has notification;
    private int label; //use 1 to 5 to add laebel
    private String tag; //identify the journote with time instead of id
    //private int authorid;
    private String username;

    public JournoteBean(String date, String title, String content, int isjournal, int hasnoti, int label, String tag, String username){
        //this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.isjournal = isjournal;
        this.hasnoti = hasnoti;
        this.label = label;
        this.tag = tag;
        this.username = username;
    }
    public JournoteBean(){
    }

    //public int getId(){return id;}
    //public void setId(int id){this.id = id;}

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getIsjournal(){return isjournal;}
    public void setIsjournal(int isjournal){this.isjournal = isjournal;}

    public int getHasnoti(){return hasnoti;}
    public void setHasnoti(int hasnoti){this.hasnoti = hasnoti;}

    public int getLabel(){return label;}
    public void setLabel(int label){this.label = label;}

    public String getTag(){return tag;}
    public void setTag(String tag) { this.tag = tag; }

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg0, int arg1) {
        // TODO Auto-generated method stub
        // 1.必须按成员变量声明的顺序封装数据，不然会出现获取数据出错
        // 2.序列化对象
        arg0.writeString(date);
        arg0.writeString(title);
        arg0.writeString(content);
        arg0.writeInt(isjournal);
        arg0.writeInt(hasnoti);
        arg0.writeInt(label);
        arg0.writeString(tag);
        arg0.writeString(username);
    }

    public static final Parcelable.Creator<JournoteBean> CREATOR = new Creator(){

        @Override
        public JournoteBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            JournoteBean j = new JournoteBean();
            j.setDate(source.readString());
            j.setTitle(source.readString());
            j.setContent(source.readString());
            j.setIsjournal(source.readInt());
            j.setHasnoti(source.readInt());
            j.setLabel(source.readInt());
            j.setTag(source.readString());
            j.setUsername(source.readString());
            return j;
        }

        @Override
        public JournoteBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new JournoteBean[size];
        }
    };

}
