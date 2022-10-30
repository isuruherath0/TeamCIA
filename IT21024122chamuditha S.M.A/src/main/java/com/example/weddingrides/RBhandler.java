package com.example.weddingrides;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RBhandler extends SQLiteOpenHelper {

    private static final int  VERSION = 1;
    private static final String DB_NAME = "weddin";
    private static final String LE_NAME = "review";

    //column name
    private static final String ID = "id";
    private static final String REVIEW = "revie";

    public RBhandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+LE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +REVIEW +" TEXT"+
                //+COMPLAINT+" TEXT,"
                //+REWVE+" TEXT," +
                ");";

        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ LE_NAME;

        //drop oldre tableif existed
        db.execSQL(DROP_TABLE_QUERY);

        //create tables again
        onCreate(db);

    }

    public void addReview(review ree){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REVIEW,ree.getReview());

        //save to table
        sqLiteDatabase.insert(LE_NAME,null,contentValues);

        //close database
        sqLiteDatabase.close();

    }
}
