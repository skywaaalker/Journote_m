package com.example.journote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotificationDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_NOTIFICATION = "create table Notification("
            + "id integer primary key autoincrement, "
            + "journoteTag text, "
            + "year integer, "
            + "month integer,"
            + "day integer,"
            + "hour integer,"
            + "minute integer)";

    public Context mContext;
    public NotificationDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_NOTIFICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists Notification");
        onCreate(db);
    }
}
