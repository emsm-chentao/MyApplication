package com.example.administrator.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/23.
 */

public class MyBroadRevice extends BroadcastReceiver {
    private static final String tagMsg = "CTMyBroadRevice===";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"onReceive",Toast.LENGTH_LONG).show();
        Log.e("", "onReceive");
    }
}
