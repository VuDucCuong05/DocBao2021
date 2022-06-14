package com.example.android45_app_doc_bao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android45_app_doc_bao_user.android45_app_doc_bao_dang_nhap.TaiKhoan;
import com.example.android45_app_doc_bao_trang_chu.BaiBao;

import java.util.ArrayList;
import java.util.List;


public class SQLHelper extends SQLiteOpenHelper {

    private static final String TAG = "Cannot invoke method length() on null object";
    private static final String DB_NAME = "DocBao.db";
    private static final int DB_VERSION = 1;

    private static final String DB_TABLE_LuuBaibao = "LuuBaiBao";
    private static final String DB_BaiBao_Id    = "id";
    private static final String DB_BaiBao_Titel = "titel";
    private static final String DB_BaiBao_Link  = "link";
    private static final String DB_BaiBao_Image = "image";
    private static final String DB_BaiBao_Time  = "time";
    private static final String DB_BaiBao_Luy  = "luy";

    // tìm kiếm
    private static final String DB_TABLE_BaibaoDD = "DuLieu";
    private static final String DB_BaiBaoDD_Id    = "id";
    private static final String DB_BaiBaoDD_Titel = "titel";
    private static final String DB_BaiBaoDD_Link  = "link";
    private static final String DB_BaiBaoDD_Image = "image";
    private static final String DB_BaiBaoDD_Time  = "time";
    private static final String DB_BaiBaoDD_Luy  = "luy";

    // đã đọc
    private static final String DB_TABLE_BaibaoDD1 = "BaiBaoDaDoc";
    private static final String DB_BaiBaoDD_Id1    = "id";
    private static final String DB_BaiBaoDD_Titel1 = "titel";
    private static final String DB_BaiBaoDD_Link1  = "link";
    private static final String DB_BaiBaoDD_Image1 = "image";
    private static final String DB_BaiBaoDD_Time1  = "time";
    private static final String DB_BaiBaoDD_Luy1  = "luy";

    private static final String DB_TABLE_TaiKhoan = "TaiKhoan";
    private static final String DB_TaiKhoan_Id    = "id";
    private static final String DB_TaiKhoan_Gmail = "gmail";
    private static final String DB_TaiKhoan_Pass  = "pass";


    public SQLHelper(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createLuuBaiBao = "CREATE TABLE " + DB_TABLE_LuuBaibao+ "(" +
                "id INTEGER NOT NULL PRIMARY KEY," +
                "titel Text," +
                "link Text," +
                "image Text," +
                "time Text,"+
                "luy Text)";
        db.execSQL(createLuuBaiBao);

        String createBaiBaoDD = "CREATE TABLE " + DB_TABLE_BaibaoDD+ "(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "titel Text," +
                "link Text," +
                "image Text," +
                "time Text,"+
                "luy Text)";
        db.execSQL(createBaiBaoDD);

        String createBaiBaoDD1 = "CREATE TABLE " + DB_TABLE_BaibaoDD1+ "(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "titel Text," +
                "link Text," +
                "image Text," +
                "time Text,"+
                "luy Text)";
        db.execSQL(createBaiBaoDD1);



        String createTaiKhoan = "CREATE TABLE "+ DB_TABLE_TaiKhoan+ "("+
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                "gmail Text,"+
                "pass Text)";
        db.execSQL(createTaiKhoan);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion ){
            db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_LuuBaibao);
            db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_BaibaoDD);
            onCreate(db);
        }
    }

    public void addBaibao(BaiBao baiBao){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_BaiBao_Id,baiBao.getId());
        contentValues.put(DB_BaiBao_Titel,baiBao.getTitel());
        contentValues.put(DB_BaiBao_Link,baiBao.getLink());
        contentValues.put(DB_BaiBao_Image,baiBao.getImage());
        contentValues.put(DB_BaiBao_Time,baiBao.getIntroduce());
        contentValues.put(DB_BaiBao_Luy,baiBao.getLuy());
        sqLiteDatabase.insert(DB_TABLE_LuuBaibao,null,contentValues);
    }
    public void onDeleteBaiBao(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_LuuBaibao,"id=?",new String[]{String.valueOf(id)});
    }
    public List<BaiBao> getAllBaiBao(){
        List<BaiBao> baiBaoList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        BaiBao baiBao;

        Cursor cursor = sqLiteDatabase.query(false,DB_TABLE_LuuBaibao,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DB_BaiBao_Id));
            @SuppressLint("Range") String titel = cursor.getString(cursor.getColumnIndex(DB_BaiBao_Titel));
            @SuppressLint("Range") String link = cursor.getString(cursor.getColumnIndex(DB_BaiBao_Link));
            @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(DB_BaiBao_Image));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(DB_BaiBao_Time));
            @SuppressLint("Range") String luy = cursor.getString(cursor.getColumnIndex(DB_BaiBao_Luy));
            baiBao = new BaiBao(id,titel,link,image,time,luy);
            baiBaoList.add(baiBao);
        }
        return baiBaoList;
    }

    public void addTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_TaiKhoan_Gmail,taiKhoan.getGmail());
        contentValues.put(DB_TaiKhoan_Pass,taiKhoan.getPass());
        sqLiteDatabase.insert(DB_TABLE_TaiKhoan,null,contentValues);
    }
    public void onDoiMk(int id,TaiKhoan taiKhoan){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_TaiKhoan_Pass,taiKhoan.getPass());
        sqLiteDatabase.update(DB_TABLE_TaiKhoan,contentValues,"id=?",new String[]{String.valueOf(id)});
    }
    public List<TaiKhoan> getAllTaiKhoan(){
        List<TaiKhoan> taiKhoanList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        TaiKhoan taiKhoan;
        Cursor cursor = sqLiteDatabase.query(false,DB_TABLE_TaiKhoan,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DB_TaiKhoan_Id)));
            @SuppressLint("Range") String gmail = cursor.getString(cursor.getColumnIndex(DB_TaiKhoan_Gmail));
            @SuppressLint("Range") String pass = cursor.getString(cursor.getColumnIndex(DB_TaiKhoan_Pass));
            taiKhoan = new TaiKhoan(id,gmail,pass);
            taiKhoanList.add(taiKhoan);
        }
    return  taiKhoanList;
    }
    // lấy dữ liệu toàn bộ
    public void addBaibaoDaDoc(BaiBao baiBao){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_BaiBaoDD_Titel,baiBao.getTitel());
        contentValues.put(DB_BaiBaoDD_Link,baiBao.getLink());
        contentValues.put(DB_BaiBaoDD_Image,baiBao.getImage());
        contentValues.put(DB_BaiBaoDD_Time,baiBao.getIntroduce());
        contentValues.put(DB_BaiBaoDD_Luy,baiBao.getLuy());
        sqLiteDatabase.insert(DB_TABLE_BaibaoDD,null,contentValues);
    }
    public List<BaiBao> getAllBaiBaoDaDoc(){
        List<BaiBao> baiBaoList1 = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        BaiBao baiBao;

        Cursor cursor = sqLiteDatabase.query(false,DB_TABLE_BaibaoDD,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DB_BaiBaoDD_Id));
            @SuppressLint("Range") String titel = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Titel));
            @SuppressLint("Range") String link = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Link));
            @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Image));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Time));
            @SuppressLint("Range") String luy = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Luy));
            baiBao = new BaiBao(id,titel,link,image,time,luy);
            baiBaoList1.add(baiBao);
        }
        return baiBaoList1;
    }


    public void addBaibaoDaDoc1(BaiBao baiBao){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_BaiBaoDD_Titel1,baiBao.getTitel());
        contentValues.put(DB_BaiBaoDD_Link1,baiBao.getLink());
        contentValues.put(DB_BaiBaoDD_Image1,baiBao.getImage());
        contentValues.put(DB_BaiBaoDD_Time1,baiBao.getIntroduce());
        contentValues.put(DB_BaiBaoDD_Luy1,baiBao.getLuy());
        sqLiteDatabase.insert(DB_TABLE_BaibaoDD1,null,contentValues);
    }
    public List<BaiBao> getAllBaiBaoDaDoc1(){
        List<BaiBao> baiBaoList2 = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        BaiBao baiBao;

        Cursor cursor = sqLiteDatabase.query(false,DB_TABLE_BaibaoDD1,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DB_BaiBaoDD_Id1));
            @SuppressLint("Range") String titel = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Titel1));
            @SuppressLint("Range") String link = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Link1));
            @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Image1));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Time1));
            @SuppressLint("Range") String luy = cursor.getString(cursor.getColumnIndex(DB_BaiBaoDD_Luy1));
            baiBao = new BaiBao(id,titel,link,image,time,luy);
            baiBaoList2.add(baiBao);
        }
        return baiBaoList2;
    }
    public void onDeleteBaiDaDoc1(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_BaibaoDD1,"id=?",new String[]{String.valueOf(id)});
    }
}
