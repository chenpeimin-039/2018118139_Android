package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Hello1 extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Hello1";
    private static int objCount = 0;
    private int mObjCount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_layout);
        objCount++;
        mObjCount = objCount;
        Log.d(TAG, mObjCount + "-onCreate execute");
        setTitle("Hello1");
        settupButtons();
    }


    protected void onStart() {
        super.onStart();
        Log.d(TAG, mObjCount + "-onStart");
    }


    protected void onResume() {
        super.onResume();
        Log.d(TAG, mObjCount + "-onResume");
    }


    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, mObjCount + "-onPostResume");
    }


    protected void onPause() {
        super.onPause();
        Log.d(TAG, mObjCount + "-onPause");
    }


    protected void onStop() {
        super.onStop();
        Log.d(TAG, mObjCount + "-onStop");
    }


    protected void onDestroy() {
        super.onDestroy();
        objCount--;
        Log.d(TAG, mObjCount + "-onDestroy");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btToHello1) {
            Intent intent;
            intent = new Intent(this, Hello1.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btToHello2) {
            Intent intent;
            intent = new Intent(this, Hello2.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btToHello3) {
            Intent intent;
            String string1 = "com.example.hello.intent.action.H3";
            // intent = new Intent(this, Hello3.class);
            intent = new Intent(string1);
            startActivity(intent);
//            Intent intent1 = new Intent(Intent.ACTION_VIEW);
//            intent1.setData(Uri.parse("http://www.hstc.edu.cn"));
//            startActivity(intent1);
        }
    }

    private void settupButtons() {
        Button b;

        b = (Button) findViewById(R.id.btToHello1);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.btToHello2);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.btToHello3);
        b.setOnClickListener(this);

    }
}
