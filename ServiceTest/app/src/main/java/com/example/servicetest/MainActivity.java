package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int UPDATE_TEXT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService=(Button)findViewById(R.id.start_service);
        Button download=(Button)findViewById(R.id.download);
        Button stopService=(Button)findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        download.setOnClickListener(this);
        stopService.setOnClickListener(this);
    }

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.arg1){
                case UPDATE_TEXT:
                    int i=0;
                    for(i=20;i<=100;i+=20){
                        try{
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        Log.d("Download","下载进度----"+i+"%");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent start=new Intent(this,MyService.class);
                startService(start);//启动服务
                break;
            case R.id.download:
                new Thread(new Runnable() {     //匿名创建子线程
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.arg1=UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.stop_service:
                Intent stop=new Intent(this,MyService.class);
                stopService(stop);//停止服务
                break;
            default:
                break;
        }
    }
}