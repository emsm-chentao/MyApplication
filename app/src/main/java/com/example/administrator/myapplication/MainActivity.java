package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
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
//                Intent intent = new Intent(MainActivity0.this, MySevice.class);
//                startService(intent);

//                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
//                handler.removeCallbacksAndMessages(null);

                startActivity(new Intent(MainActivity.this, PostImageAndTextContentActivity.class));
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
        rectveiw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onTouchx(v,event);
                return false;
            }
        });
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
//                stopService(new Intent(MainActivity0.this, MySevice.class));
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

    float x1 = 0, y1 = 0;
    float x2 = 0, y2 = 0;

/*    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = event.getX();
                y2 = event.getY();
                rectveiw.offsetLeftAndRight((int)x2);
                break;
            case MotionEvent.ACTION_UP:
                int sa=0;
                if (x2 - x1 > 50) {
                    Toast.makeText(getApplicationContext(), "右边", Toast.LENGTH_LONG).show();
                }

                if (x1 - x2 > 50) {
                    Toast.makeText(getApplicationContext(), "左边", Toast.LENGTH_LONG).show();
                }

                if (y2 - y1 > 150) {
                    Toast.makeText(getApplicationContext(), "向下", Toast.LENGTH_LONG).show();
                }

                if (y1 - y2 > 150) {
                    Toast.makeText(getApplicationContext(), "向上", Toast.LENGTH_LONG).show();
                }
                break;
        }

        return super.onTouchEvent(event);
    }*/

    private DisplayMetrics displayMetrics;
    private float lastX=0;
    private float lastY=0;
    private int screenWidth=0;
    private int screenHeight=0;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private boolean isFirst=true;
    public boolean onTouchx(View view, MotionEvent event) {
        if (isFirst) {
            // 得到屏幕的宽
            displayMetrics = getResources().getDisplayMetrics();
            screenWidth = displayMetrics.widthPixels;
            // 得到标题栏和状态栏的高度
            Rect rect = new Rect();
            Window window = getWindow();
            rectveiw.getWindowVisibleDisplayFrame(rect);
            int statusBarHeight = rect.top;
            int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
            int titleBarHeight = contentViewTop - statusBarHeight;
            // 得到屏幕的高
            screenHeight = displayMetrics.heightPixels- (statusBarHeight + titleBarHeight);
            isFirst=false;
        }
        int action=event.getAction();
        switch (action) {
            //按下
            case MotionEvent.ACTION_DOWN:
                //按下处坐标
                lastX=event.getRawX();
                lastY=event.getRawY();
                break;
            //移动
            case MotionEvent.ACTION_MOVE:
                //移动的距离
                float distanceX=event.getRawX()-lastX;
                float distanceY=event.getRawY()-lastY;
                //移动后控件的坐标
                left=(int)(view.getLeft()+distanceX);
                top=(int)(view.getTop()+distanceY);
                right=(int)(view.getRight()+distanceX);
                bottom=(int)(view.getBottom()+distanceY);
                //处理拖出屏幕的情况
                if (left<0) {
                    left=0;
                    right=view.getWidth();
                }
                if (right>screenWidth) {
                    right=screenWidth;
                    left=screenWidth-view.getWidth();
                }
                if (top<0) {
                    top=0;
                    bottom=view.getHeight();
                }
                if (bottom>screenHeight) {
                    bottom=screenHeight;
                    top=screenHeight-view.getHeight();
                }
                //显示图片
                view.layout(left, top, right, bottom);
                lastX=event.getRawX();
                lastY=event.getRawY();
                break;
            //抬起
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return false;
    }
}
