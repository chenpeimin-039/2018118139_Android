package com.example.student.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//自定义数据库子类（创建数据库）
//通过创建子类继承SQLiteOpenHelper类，实现它的一些方法来对数据库进行操作。
public class myDatabaseHelper extends SQLiteOpenHelper {
    private static myDatabaseHelper instance;
    public static final String CREATE_ADMIN = "create table admin(id integer primary key autoincrement, " +
            "name text,password text)";//创建管理员表
    public static final String CREATE_STUDENT = "create table student(name text,sex text,id text primary key," +
            "number text,password text,mathScore integer,chineseScore integer,englishScore integer)";//创建学生表
    private myDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建数据库表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ADMIN);
        db.execSQL(CREATE_STUDENT);
        db.execSQL("alter table student add  column ranking integer");
    }

    //更新数据库表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion==1){
            db.execSQL("alter table student add  column ranking integer");
        }
    }

    //数据库名：StudentManagement.db
    public static myDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new myDatabaseHelper(context, "StudentManagement.db", null, 2);
        }
        return instance;
    }
}