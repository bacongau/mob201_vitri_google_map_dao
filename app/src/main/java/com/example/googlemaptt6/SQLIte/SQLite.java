package com.example.googlemaptt6.SQLIte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {

    public SQLite(@Nullable Context context) {
        super(context, "DELIVERY", null, 1);
    }

    public String COLUMNID = "ID";
    public String COLUMNNAME = "NAME";
    public String COLUMNLONG = "LONGTITUDE";
    public String COLUMNLAT = "LATTITUDE";
    public String TABLE_NAME = "KHACHHANG";
    public String CREATE_TABLE = "CREATE TABLE "+
            TABLE_NAME+" ("+
            COLUMNID+" VARCHAR PRIMARY KEY," +
            COLUMNNAME+" VARCHAR,"+
            COLUMNLONG +" VARCHAR,"+
            COLUMNLAT+" VARCHAR)";
    public String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
    }
}
