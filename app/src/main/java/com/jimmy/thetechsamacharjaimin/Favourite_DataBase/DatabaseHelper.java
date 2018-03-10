package com.jimmy.thetechsamacharjaimin.Favourite_DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jaimin on 13/9/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    static final String DB_NAME = "FAVOURITE.DB";

    // Table Name
    public static final String TABLE_NAME = "FAV";

    // Table columns
    public static final String _ID = "_id";
    public static final String Name = "name";
    public static final String Image = "image";
    public static final String TimeStamp = "timestamp";
    public static final String date = "date";
    public static final String isChecked = "ischecked";
    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = ("create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Name + " TEXT NOT NULL, " + Image + " BLOB, "+ TimeStamp + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + date + " TEXT, " + isChecked + " TEXT);");

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
