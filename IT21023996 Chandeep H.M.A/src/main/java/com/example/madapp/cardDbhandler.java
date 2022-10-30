package com.example.madapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class cardDbhandler<c1> extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME ="dbcard";
    private static final String TABLE_NAME= "dcard";

    private static final String ID ="id";
    private static final String NUMBER = "cNumber";
    private static final String CNAME = "cName";
    private static final String EXP ="cExp";
    private static final String CVC ="cCVC";


    public cardDbhandler(@Nullable Context context) {
        super(context,DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NUMBER +" TEXT, "
                +CNAME +" TEXT, "
                +EXP +" TEXT, "
                +CVC+ " TEXT "+
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

    public void addCard(cardModel cardmodel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NUMBER,cardmodel.getcNumber());
        contentValues.put(CNAME,cardmodel.getcName());
        contentValues.put(EXP,cardmodel.getcExp());
        contentValues.put(CVC,cardmodel.getcCVC());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
    }
    public int countCards(){
        SQLiteDatabase db= getReadableDatabase();
        String query ="SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }
    public List<cardModel> getAllcards(){
        List<cardModel> cards =new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                cardModel cardmodel = new cardModel();
                cardmodel.setId(cursor.getInt(0));
                cardmodel.setcNumber(cursor.getString(1));
                cardmodel.setcName(cursor.getString(2));
                cardmodel.setcExp(cursor.getString(3));
                cardmodel.setcCVC(cursor.getString(4));

                cards.add(cardmodel);
            }while(cursor.moveToNext());
        }
        return cards;
    }

    public void deletecard(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,ID+" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    public cardModel getSingleCard(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,NUMBER,CNAME,EXP,CVC},ID + "= ?",new String[]{String.valueOf(id)},null,null,null);
        cardModel cm;
        if(cursor != null){
            cursor.moveToFirst();
            cm = new cardModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            return cm;
        }
        return  null;
    }

    public int updatesinglecard(cardModel cardmodel){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NUMBER,cardmodel.getcNumber());
        contentValues.put(CNAME,cardmodel.getcName());
        contentValues.put(EXP,cardmodel.getcExp());
        contentValues.put(CVC,cardmodel.getcCVC());

        int status = db.update(TABLE_NAME,contentValues,ID+" =?",new  String[]{String.valueOf(cardmodel.getId())});
        db.close();
        return status;
    }



}
