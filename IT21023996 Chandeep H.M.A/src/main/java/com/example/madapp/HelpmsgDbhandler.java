package com.example.madapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class HelpmsgDbhandler<c1> extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME ="dbcard";
    private static final String TABLE_NAME= "helpmsg";

    private static final String ID ="id";
    private static final String MSG = "msg";



    public HelpmsgDbhandler(@Nullable Context context) {
        super(context,DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_CREATE_QUERY;
        TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +MSG +" TEXT "+
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;

        //drop older tableif existed
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);

        //create tables again
        onCreate(sqLiteDatabase);

    }

    public void addMsg(MsgModel msgModel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(MSG,msgModel.getMsg());


        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
    }

}
