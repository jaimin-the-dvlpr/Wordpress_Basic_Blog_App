package com.jimmy.thetechsamacharjaimin.Favourite_DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Jay Mataji on 9/12/2015.
 */

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String image ,String date) {
        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.Name, name);
        contentValue.put(DatabaseHelper.Image, image);
        contentValue.put(DatabaseHelper.date, date);
        contentValue.put(DatabaseHelper.isChecked, "true");


        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch(String name) {
        String[] columns = new String[]{DatabaseHelper.isChecked};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns,"name=?",new String[] {name}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public void delete(String name) {
        database.delete(DatabaseHelper.TABLE_NAME, "name=?" ,new String[] {name});


    }

 public Cursor getAllCode() {

     String[] columns = new String[]{DatabaseHelper._ID,DatabaseHelper.TimeStamp, DatabaseHelper.Name, DatabaseHelper.Image,DatabaseHelper.date, DatabaseHelper.isChecked};

     Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, DatabaseHelper.TimeStamp);

     if (cursor != null) {
         cursor.moveToFirst();
    }
     return cursor;

 }
}



