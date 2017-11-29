package utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/11/29.
 */

public class LineaViewA extends LinearLayout {
    public static final String TAG = "LineaViewA=== ";

    public LineaViewA(Context context) {
        super(context);
    }

    public LineaViewA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineaViewA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, TAG + "dispatchTouchEvent");
         return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, TAG + "onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, TAG + "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
