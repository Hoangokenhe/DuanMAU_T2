package com.example.duanmau_t2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper  {
public Dbhelper(Context context){
    super(context,"DANGKYMONHOC",null,1);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
            String dbThuThu = "CREATE TABLE THUTHU(matt text primary key, hoten text, matkhau text, loaitaikhoan text)";
            db.execSQL(dbThuThu);

        String dbThanhVien="CREATE TABLE THANHVIEN(matv integer primary key autoincrement,hoten text, namsinh text)";
        db.execSQL(dbThanhVien);

        String dbLoai ="CREATE TABLE LOAI(maloai integer primary key autoincrement, tenloai text)";
        db.execSQL(dbLoai);

        String dbSach = "CREATE TABLE SACH( masach integer primary key autoincrement, tensach text, giathue integer, maloai integer references LOAI(maloai) )";
        db.execSQL(dbSach);

        String dbPhieuMuon= " CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement, matv integer references THANHVIEN(matv),matt text references THUTHU(matt), masach integer references SACH(masach),ngay text, trasach integer, tienthue integer)";
        db.execSQL(dbPhieuMuon);


        db.execSQL("INSERT INTO LOAI VALUES(1, 'Thieu nhi'),(2,'Tinh Cam'),(3,'Giao Khoa')");
        db.execSQL("INSERT INTO SACH VALUES (1,'Hay doi day', 2500, 1),(2, 'Thang cuoi',1000,1),(3,'Lap trinh android',2000,3)");
        db.execSQL("INSERT INTO THUTHU VALUES ('thuthu01','Vũ Huy Hoàng','1234','Admin'),('thuthu02','Khuất Khánh Linh','1234','thủ thư')");
        db.execSQL("INSERT INTO THANHVIEN VALUES(1,'Cao Thu Trang','2000'),(2,'Vu Huy Hoang','2000')");
        // tra sach: 1: da tra  -  0: chua tra

        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu01',1,'19/03/2022',1,2500),(3,2,'thuthu02',1,'19/03/2022',0,2000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i !=1) {
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(db);
        }
    }
}
