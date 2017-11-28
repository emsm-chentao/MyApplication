package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/11/23.
 */

public class ViewText extends View {

    public ViewText(Context context) {
        super(context);
    }

    public ViewText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=MeasureSpec.getMode(widthMeasureSpec);
        int height=MeasureSpec.getMode(heightMeasureSpec);

        int w=MeasureSpec.getSize(widthMeasureSpec);
        int h=MeasureSpec.getSize(heightMeasureSpec);

        int mWidthx = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int mHeightx = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        Log.e("CT","width="+width+"\n"+"height="+height+"\n"+"w="+w+"\n"+"h="+h+"\n"+"mWidthx="+mWidthx+"\n"+"mHeightx="+mHeightx);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
