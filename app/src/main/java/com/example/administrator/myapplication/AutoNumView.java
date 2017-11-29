package com.example.administrator.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * https://www.cnblogs.com/itgungnir/p/6217447.html
 * Created by Administrator on 2017/11/28.
 */

/**
 * 自定义Ｖｉｅｗ　心德：
 * 　首先继承Ｖｉｅｗ，然后复写带参构造方法
 * 　接着实现Ｖｉｅｗ的两个重要方法：ＯｎＭｅａｓｕｒｅ（），ＯｎＤｒａｗ（）；
 * <p>
 * ＯｎＭｅａｓｕｒｅ（）：
 * 说明：ＥＸＡＣＴＬＹ　：一般设置有固定值或者ＭＡＴＣＨ＿ＰＡＲＥＮＴ
 * 　　　ＡＴ＿ＭＯＳＴ：一般设置为ＷＡＲＰ＿ＣＯＮＴＥＮＴ
 * 　　　ＵＮＳＰＥＣＩＦＩＥＤ子布局要多大就多大，基本很少使用
 * <p>
 * <p>
 * ｓｅｔＭｅａｓｕｒｅＤｉｍｅｎｓｉｏｎ（ｗｉｄｔｈ，ｈｅｉｇｔｈ）；这个方法是重新设置Ｖｉｅｗ的宽高
 * <p>
 * //requestLayout();//经过测试：ｒｅｑｕｅｓｔＬａｙｏｕｔ只会调用ｏｎＭｅａｓｕｒｅ方法
 * //invalidate();//postInvalidate();
 */
public class AutoNumView extends View {
    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    private Paint mPaint;
    private Rect mRect;

    public AutoNumView(Context context) {
        super(context);
    }

    public AutoNumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        mTitleText = typedArray.getString(R.styleable.RectView_RectView_text);
        mTitleTextSize = (int) typedArray.getDimension(R.styleable.RectView_RectView_textSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
        mTitleTextColor = typedArray.getColor(R.styleable.RectView_RectView_textColor, Color.RED);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);

        mRect = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wmode = MeasureSpec.getMode(widthMeasureSpec);
        int hmode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heigthSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0, heigth = 0;
        if (wmode == MeasureSpec.UNSPECIFIED || hmode == MeasureSpec.UNSPECIFIED) {
            throw new IllegalArgumentException("子布局不能想要多大就多大");
        }

        if (wmode == MeasureSpec.EXACTLY) {//一般设置了固定的值或者设置为ＭＡＴＣＨ＿ＰＡＲＥＮＴ
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
            float textWidth = mRect.width();//获取文本的宽度
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (hmode == MeasureSpec.EXACTLY) {//一般设置了固定的值或者设置为ＭＡＴＣＨ＿ＰＡＲＥＮＴ
            heigth = heigthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
            float textHeight = mRect.height();//获取文本的高度
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            heigth = desired;
        }

        setMeasuredDimension(width, heigth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);

        int w = getMeasuredWidth();

        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        int wx = getWidth();
        int wxmRect = mRect.width();
        Log.e("CT", w + "=w====wx=" + wx + "＝＝wxmRect＝" + wxmRect);
        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, (getWidth() - mRect.width()) / 2, (getHeight() + mRect.height()) / 2, mPaint);
    }

    int ss = 0;

    public void setTextRandom() {
//        Random random = new Random();
//        Set<Integer> integerSet = new HashSet<>();
//        while (integerSet.size() < 4) {
//            Integer in = random.nextInt(10);
//            integerSet.add(in);
//        }
//
//        StringBuffer stringBuffer = new StringBuffer();
//        for (Integer x : integerSet) {
//            stringBuffer.append(x + "");
//        }

        ss++;
        mTitleText = "" + ss;
        invalidate();

    }
}
