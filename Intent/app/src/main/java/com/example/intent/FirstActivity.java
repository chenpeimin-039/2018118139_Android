package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity", "Task id is " + getTaskId());
        setContentView(R.layout.first_layout);//给当前活动增加一个布局:res->layout->first_layout.xml
        Button button1 = (Button) findViewById(R.id.button_1);//获取在布局文件中定义的元素
        //为按钮注册一个监听器
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Intent intent = new Intent(FirstActivity.this, SecondActivity.class);//使用显式Intent
                Intent intent= new Intent("com.example.activitytest.ACTION_START");//使用隐式Intent
//              intent.addCategory("com.example.activitytest.MY_CATEGORY");//addCategory()新添一个category
                startActivity(intent);//创建/启动活动
//              SecondActivity.actionStart(FirstActivity.this, "data1", "data2");//传递数据
            }
        });
    }

    //使用菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);//创建菜单
        return true;//允许创建的菜单显示出来；false表示创建的菜单无法显示
    }

    //定义菜单响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) { //判断点击的是哪一个菜单项
            case R.id.add_item:
                //makeText()创建一个Toast对象；show()显示该对象
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}

