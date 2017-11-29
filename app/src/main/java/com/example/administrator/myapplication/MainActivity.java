package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import utils.OntouchTestActivity;

public class MainActivity extends AppCompatActivity {
    private static final String tagMsg = "CTMainActivity===";
    private HandlerThread handlerThread;
    //    private Handler handler;
    private TextView textView;

    //    MySevice.MyBinder myBinder;
//    ServiceConnection serviceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            myBinder = (MySevice.MyBinder) iBinder;
//            myBinder.loadImage();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//
//        }
//    };
    AutoNumView rectveiw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tcv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MySevice.class);
//                startService(intent);

//                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
//                handler.removeCallbacksAndMessages(null);

                startActivity(new Intent(MainActivity.this, Second.class));
                finish();
            }
        });
        findViewById(R.id.tcv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OntouchTestActivity.class));
            }
        });
        rectveiw = findViewById(R.id.rectveiw);
        rectveiw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rectveiw.setTextRandom();
            }
        });
        rectveiw.callOnClick();

        final RoundProgressBar roundProgressBar2 = findViewById(R.id.roundProgressBar2);
        roundProgressBar2.setProgress(70);
        roundProgressBar2.setMax(100);
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                textView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        rectveiw.performClick();
//                    }
//                });
//
//                Log.e("CT==", tagMsg + "timer============");
//            }
//        },0,100);


       // handler.postDelayed(runnable, 1000);


//        findViewById(R.id.tcv1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stopService(new Intent(MainActivity.this, MySevice.class));
//            }
//        });


//        handlerThread= new HandlerThread("32212313");
//        handlerThread.start();
//        handler=new Handler(handlerThread.getLooper(), new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message message) {
//                Log.e("CT==", tagMsg + "handleMessage"+ message.what);
//                textView.invalidate();
//                return false;
//            }
//        });
//        handler.sendEmptyMessage(1);
//        myHandler = new MyHandler(this);
//        myHandler.sendEmptyMessage(1);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rectveiw.performClick();
            handler.postDelayed(runnable, 100);
        }
    };
    MyHandler myHandler;

    static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;

        MyHandler(Activity activity) {
            mActivityReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final Activity activity = mActivityReference.get();
            MainActivity mainActivity = (MainActivity) activity;
            if (mainActivity != null) {
                mainActivity.textView.invalidate();
                Log.e("CT==", tagMsg + "handleMessage" + msg.what);
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("CT==", tagMsg + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("CT==", tagMsg + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("CT==", tagMsg + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("CT==", tagMsg + "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("CT==", tagMsg + "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   finish();
//        System.exit(0);
        Log.e("CT==", tagMsg + "onDestroy");
    }

}
