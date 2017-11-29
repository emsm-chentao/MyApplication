package utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2017/11/29.
 */

/**
 * 事件分发 正常循序
 * 首先：Activity－－＞ＶｉｅｗＧｒｏｕｐ－－＞Ｖｉｅｗ
 * Activity(dispatchOntouchEvent) ---> ＶｉｅｗＧｒｏｕｐ(dispatchOntouchEvent,onInterceptTouchEvent)  ---> Ｖｉｅｗ（dispatchOntouchEvent）
 * <p>
 * Ｖｉｅｗ(onTouchEvent) ---> ＶｉｅｗＧｒｏｕｐ(onTouchEvent) --> Activity(onTouchEvent)
 */
public class OntouchTestActivity extends AppCompatActivity {
    public static final String TAG = "OntouchTestActivity=== ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
//        findViewById(R.id.rectveiw).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, TAG + "onClick");
//            }
//        });
//
//        findViewById(R.id.rectveiw).setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Log.e(TAG, TAG + "onLongClick");
//                return false;
//            }
//        });
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.e(TAG, TAG + "onTouchEvent");
//        return super.onTouchEvent(event);
//    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e(TAG, TAG + "dispatchTouchEvents");
//        return super.dispatchTouchEvent(ev);
//    }


}