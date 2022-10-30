package com.example.weddingrides;
//desktop
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhandler extends SQLiteOpenHelper {
    private static final int  VERSION = 1;
    private static final String DB_NAME = "weddin";
    private static final String TABLE_NAME = "feedb";



    //column name
    private static final String ID = "id";
    private static final String FEEDBACK = "feedback";
    // private static final String COMPLAINT = "complaint";
   // private static final String REWVE = "rewve";

    public DBhandler(@Nullable Context context) {
        super(context,DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +FEEDBACK +" TEXT"+
                //+COMPLAINT+" TEXT,"
                //+REWVE+" TEXT," +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;

        //drop oldre tableif existed
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);

        //create tables again
        onCreate(sqLiteDatabase);


    }

    public void myfeedback(feed fee){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FEEDBACK,fee.getFeedback());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        //close database
        sqLiteDatabase.close();

    }
    public int countfeed(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //get all feedbacks
    public List<feed> getAllfeed(){

        List<feed> feeds = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        //to check first row empty or not
        if(cursor.moveToFirst()){
            do {
                feed feedb = new feed();

                feedb.setId(cursor.getInt(0));
                feedb.setFeedback(cursor.getString(1));
                feeds.add(feedb);
            }while (cursor.moveToNext());
        }
        return feeds;

    }

    //Delete Items
    public void deletefeedback(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,ID +" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    //get a single feedback
    public feed getSingleFeedback(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,FEEDBACK},
                ID + "= ?", new String[]{String.valueOf(id)},null,null,null);

        feed Feed;
        if(cursor != null){
                cursor.moveToFirst();


            Feed = new feed(
                    cursor.getInt(0),
                    cursor.getString(1)
            );
            return Feed;

        }
        return null;
    }

    public int updateSingleFeed(feed Feed){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FEEDBACK,Feed.getFeedback());

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",
                new String[]{String.valueOf(Feed.getId())});

        db.close();
        return status;
    }
}
