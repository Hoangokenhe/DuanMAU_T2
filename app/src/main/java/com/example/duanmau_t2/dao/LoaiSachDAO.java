package com.example.duanmau_t2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duanmau_t2.database.Dbhelper;
import com.example.duanmau_t2.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {

    Dbhelper dbhelper;
    public LoaiSachDAO(Context context){
        dbhelper=new Dbhelper(context);

    }

    //Lấy danh sách loại sách
    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT*FROM LOAI",null);

        if (cursor.getCount()!=0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));

            }while (cursor.moveToNext());
        }

        return list;

    }

    //Thêm loại sách
    public boolean themLoaiSach(String tenloai){

        SQLiteDatabase sqLiteDatabase=dbhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenloai",tenloai);
        long check=sqLiteDatabase.insert("LOAI",null,contentValues);
        if (check==-1)
            return false;
        return true;

    }

    //Xóa loại sách

    public int xoaLoaiSach(int id){

        SQLiteDatabase sqLiteDatabase=dbhelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE maloai=?",new String[]{String.valueOf(id)});
        if (cursor.getCount()!=0) {
            return -1;
        }

        long check=sqLiteDatabase.delete("LOAI","maloai=?",new String[]{String.valueOf(id)});
        if (check==-1)
            return 0;
        return 1;

    }

    public boolean thayDoiLoaiSach(LoaiSach loaiSach){

        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenloai", loaiSach.getTenloai());
        long check=sqLiteDatabase.update("LOAI",contentValues,"maloai=?",new String[]{String.valueOf(loaiSach.getId())});
        if (check==-1)
            return false;
        return true;


    }

}
