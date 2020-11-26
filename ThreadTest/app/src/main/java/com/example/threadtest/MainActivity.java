package com.example.threadtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView text;
    public static final int UPDATE_TEXT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.text);
        Button change=(Button)findViewById(R.id.change);
        change.setOnClickListener(this);
    }

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){     //在主线程中运行
            switch (msg.what){
                case UPDATE_TEXT:
                    text.setText("Change Text: Hello!");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change:
                new Thread(new Runnable() {     //匿名创建子线程
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=UPDATE_TEXT;
                        handler.sendMessage(message);//发送Message对象，在handleMessage()进行处理
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}