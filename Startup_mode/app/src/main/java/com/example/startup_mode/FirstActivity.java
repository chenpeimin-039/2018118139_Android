package com.example.startup_mode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity",this.toString());
        setContentView(R.layout.first_layout);//给当前活动增加一个布局:res->layout->first_layout.xml
        Button button1 = (Button) findViewById(R.id.button_1);//获取在布局文件中定义的元素
        //为按钮注册一个监听器
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(FirstActivity.this, SecondActivity.class);//使用显式Intent
              startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("FirstActivity", "onRestart");
    }

}


