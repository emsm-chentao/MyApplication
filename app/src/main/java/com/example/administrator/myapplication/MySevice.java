package com.example.administrator.myapplication;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/23.
 */

public class MySevice extends Service {
    private static final String tagMsg = "CTMySevice===";

    @Override
    public void onCreate() {
        Log.e("CT==", tagMsg + "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("CT==", tagMsg + "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("CT==", tagMsg + "onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
    MyBinder myBinder=new MyBinder();

    class MyBinder extends Binder {
        public void loadImage() {
            Log.e("CT==", tagMsg + "loadImage=============");
        }
    }

}
