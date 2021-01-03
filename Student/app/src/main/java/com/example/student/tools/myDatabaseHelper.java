package com.example.student.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*SQLiteOpenHelper类
定义：SQLiteOpenHelper是一个辅助类
作用：管理数据库（创建、增、修、删） & 版本的控制。
使用过程：通过创建子类继承SQLiteOpenHelper类，实现它的一些方法来对数据库进行操作。
在实际开发中，为了能够更好的管理和维护数据库，我们会封装一个继承自SQLiteOpenHelper类的数据库操作类，然后以这个类为基础，再封装我们的业务逻辑方法。
*/

//自定义数据库子类（创建数据库）
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