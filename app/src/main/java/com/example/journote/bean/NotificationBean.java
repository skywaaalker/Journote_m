package com.example.journote.bean;

import java.io.Serializable;

public class NotificationBean implements Serializable {
    String journoteTag;
    String date_str;

    public NotificationBean(String journoteTag, String date_str){
        this.journoteTag = journoteTag;
        this.date_str = date_str;
    }
    public NotificationBean(){

    }

    public String getJournoteTag(){return journoteTag;}
    public void setJournoteTag(String journoteTag){this.journoteTag = journoteTag;}

    public String getDateStr(){return date_str;}
    public void setDateStr(String date_str){this.date_str = date_str;}
}
