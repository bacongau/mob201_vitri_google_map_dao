package com.example.googlemaptt6.SQLIte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.googlemaptt6.Object.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class DAO {
    private SQLiteDatabase db;
    private SQLite sqLite;
    public DAO(Context context) {
        sqLite = new SQLite(context);
        db = sqLite.getWritableDatabase();
    }

    public long insert(KhachHang khachHang){
        ContentValues values = new ContentValues();
        values.put(sqLite.COLUMNID,khachHang.getId());
        values.put(sqLite.COLUMNNAME,khachHang.getName());
        values.put(sqLite.COLUMNLONG,khachHang.getLongitude());
        values.put(sqLite.COLUMNLAT,khachHang.getLatitude());
        return db.insert(sqLite.TABLE_NAME,null,values);
    }

    public int update(KhachHang khachHang){
        ContentValues values = new ContentValues();
        values.put(sqLite.COLUMNID,khachHang.getId());
        values.put(sqLite.COLUMNNAME,khachHang.getName());
        values.put(sqLite.COLUMNLONG,khachHang.getLongitude());
        values.put(sqLite.COLUMNLAT,khachHang.getLatitude());

        return db.update(sqLite.TABLE_NAME,values,sqLite.COLUMNID+"=?",new String[]{String.valueOf(khachHang.getId())});
    }

    public int delete(String id){
        return db.delete(sqLite.TABLE_NAME,sqLite.COLUMNID+"=?",new String[]{id});
    }

    public List<KhachHang> getAllData(){
        String sql = "SELECT * FROM "+sqLite.TABLE_NAME;
        return getData(sql);
    }

    public KhachHang getItemById(String id){
        String sql = "SELECT * FROM "+sqLite.TABLE_NAME+" WHERE "+sqLite.COLUMNID+"=?";
        List<KhachHang> list = getData(sql,id);
        return list.get(0);
    }

    private List<KhachHang> getData(String sql,String...selectionArgs){
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            KhachHang khachHang = new KhachHang();
            khachHang.setId(cursor.getString(cursor.getColumnIndex(sqLite.COLUMNID)));
            khachHang.setName(cursor.getString(cursor.getColumnIndex(sqLite.COLUMNNAME)));
            khachHang.setLongitude(cursor.getString(cursor.getColumnIndex(sqLite.COLUMNLONG)));
            khachHang.setLatitude(cursor.getString(cursor.getColumnIndex(sqLite.COLUMNLAT)));
            list.add(khachHang);
        }

        return list;
    }
}
