package com.example.asynctasktest;

import android.os.AsyncTask;
import android.util.Log;
//String:网络图片位置，Integer:进度的刻度，String:任务执行的返回结果
public class DownloadTask extends AsyncTask<String,Integer,String> {

    //运行前（主线程）
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        Log.d("DownloadTask","onPreExecute进程--"+Thread.currentThread().getName());
    }
    //子线程运行（主要完成耗时工作）
    @Override
    protected String doInBackground(String... params) {
        Log.d("DownloadTask","doInBackground进程--"+Thread.currentThread().getName());
        //模拟下载
        int i=0;
        for(i=20;i<=100;i+=20){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Log.d("DownloadTask","doInBackground下载进度----"+i+"%");
            publishProgress(i);//更新UI元素，传参给onProgressUpdate()实现从子线程切换到主线程
        }
        String result="download end";
        return result;
    }
    //运行中（主线程，更新UI操作）
    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        int progress=values[0];
        Log.d("DownloadTask","onProgressUpdate下载结果----"+progress+"%");
    }
    //运行后（主线程）
    @Override
    protected void onPostExecute(String s){
        Log.d("DownloadTask","onPostExecute进程--"+Thread.currentThread().getName());
        Log.d("DownloadTask","onPostExecute最终结果----"+s);
    }
}
