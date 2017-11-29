package utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/11/29.
 */

public class ViewTextUtils extends AppCompatTextView {
    public ViewTextUtils(Context context) {
        super(context);
    }

    public ViewTextUtils(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewTextUtils(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public static final String TAG = "ViewTextUtils=== ";

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, TAG + "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, TAG + "onTouchEvent");
        return super.onTouchEvent(event);
    }

}
