package com.example.student.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import com.example.student.R;
import com.example.student.tools.myDatabaseHelper;

//登录主界面
public class MainActivity extends Activity {
    private long exit_time;//用于实现按两次back退出
    private Button admin;
    private Button student;
    private myDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        admin = (Button) findViewById(R.id.main_activity_admin);
        student = (Button) findViewById(R.id.main_activity_student);
        dbHelper = myDatabaseHelper.getInstance(this);  //调用getInstance方法来得到对象,保证每次调用都返回相同的对象
        dbHelper.getWritableDatabase();  //创建、打开可读写的数据库

        //跳转到管理员登录界面
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, admin_login_activity.class);
                startActivity(intent);
            }
        });

        //跳转到学生登录界面
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, student_login_activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    //按两次back键退出（计算两次按back的时间，如果大于2s不退出，否则退出）
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exit_time > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exit_time = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}