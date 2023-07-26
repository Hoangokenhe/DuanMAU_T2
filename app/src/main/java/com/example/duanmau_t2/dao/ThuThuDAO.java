package com.example.duanmau_t2.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_t2.database.Dbhelper;


public class ThuThuDAO {
    Dbhelper dbhelper;
    SharedPreferences sharedPreferences;

    public ThuThuDAO(Context context) {
        dbhelper = new Dbhelper(context);
        sharedPreferences=context.getSharedPreferences("THONGTIN", MODE_PRIVATE);

    }

    //ĐĂng nhập
    public boolean checkDangNhap(String matt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("matt",cursor.getString(0));
            editor.putString("loaitaikhoan",cursor.getString(3));
           editor.putString("hoten",cursor.getString(1));
            editor.commit();

            return true;
        } else {
            return false;

        }
    }
    public int capNhatMatKhau(String username, String oldPass, String newPass) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt =? AND matkhau = ?  ", new String[]{username, oldPass});
        if (cursor.getCount()>0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
           long check= sqLiteDatabase.update("THUTHU", contentValues,"matt=?",new String[]{username});
           if (check==-1)
               return -1;
           return 1;


        }
        return 0;


    }
}
