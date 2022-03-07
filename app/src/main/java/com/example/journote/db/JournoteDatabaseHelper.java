package com.example.journote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JournoteDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_JOURNOTE = "create table Journote("
            + "id integer primary key autoincrement, "
            + "date text, "
            + "title text, "
            + "content text,"
            + "tag text,"
            + "isjournal integer,"
            + "hasnoti integer,"
            + "label integer)";

    public Context mContext;
    public JournoteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_JOURNOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists Journote");
        onCreate(db);
    }
}
