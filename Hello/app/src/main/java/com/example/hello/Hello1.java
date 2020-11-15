package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Hello1 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Hello1";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello1);
        Log.d(TAG, "onCreate execute");
        setTitle("Hello1");
        settupButtons();
    }

    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume");
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btToHello1) {
            Intent intent = new Intent(this, Hello1.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btToHello2) {
            Intent intent = new Intent(this, Hello2.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btToHello3) {
            Intent intent = new Intent(this, Hello3.class);
            startActivity(intent);
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
