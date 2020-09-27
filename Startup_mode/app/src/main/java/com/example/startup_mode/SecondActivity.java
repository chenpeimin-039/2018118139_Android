package com.example.startup_mode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity", this.toString());
        setContentView(R.layout.second_layout);//给当前活动增加一个布局:res->layout->first_layout.xml
        Button button1 = (Button) findViewById(R.id.button_2);//获取在布局文件中定义的元素
        //为按钮注册一个监听器
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "onDestroy");
    }
}
