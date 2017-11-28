package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/19.
 * <p/>
 * setmCurrentProgressColor（）； 控制进度条颜色
 * <p/>
 * setStart  《必须调用 否则设置不了数据》
 */
public class CTShapeRectangle extends View {
    private float mMaxIntProgress = 10000;
    private Paint mCurrentProgress;
    private Paint mMaxProgress;
    private Paint mPaintText;
    private int mWidth;
    private int mHeight;
    private String monthAndYer;
    private float money;
    private int mCurrentProgressColor = Color.parseColor("#949494");
    private int mMaxProgressColor = Color.parseColor("#FFFFFF");
    private int mPaintTextColor = Color.parseColor("#FFFFFF");
    private float marginLeftAndRigth = dip2px(0);//整体左右边距
    private float marginPaintTextLeftAndRigth = dip2px(8);//文字 左右边距
    private float XStartLeft, YStartLeft;//左边文字坐标
    private float XStartRight, YStartRight;//右边文字坐标
    private float left, top, right, bottom;//矩形的

    public CTShapeRectangle(Context context) {
        super(context);
    }

    public CTShapeRectangle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCurrentProgress = new Paint();
        mCurrentProgress.setColor(Color.YELLOW);
        mCurrentProgress.setAntiAlias(true);

        this.mMaxProgress = new Paint();
        this.mMaxProgress.setAntiAlias(true);
        this.mMaxProgress.setDither(true);
        this.mMaxProgress.setStyle(Paint.Style.FILL);
        this.mMaxProgress.setColor(mMaxProgressColor);

        mPaintText = new Paint();
        mPaintText.setColor(mPaintTextColor);
        mPaintText.setStrokeWidth(5);
        mPaintText.setAntiAlias(true);
        mPaintText.setTextSize(dip2px(15));
        mPaintText.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaintText.setColor(mPaintTextColor);
        mMaxProgress.setColor(mMaxProgressColor);
        mCurrentProgress.setColor(mCurrentProgressColor);//0.5*1000      500/500 * 1000  1000   250 /500 * 1000
        left = marginLeftAndRigth;
        top = marginLeftAndRigth;
        right = mWidth - marginLeftAndRigth;
        bottom = mHeight;
        canvas.drawRect(left, top, right, bottom, mMaxProgress);//float left, float top, float right, float bottom

        float mCurrentProgressRigth = (money / mMaxIntProgress * mWidth);

        float tempWidth = mWidth * 2 / 5f;//
        if (mCurrentProgressRigth < tempWidth) {//如果大于屏蔽宽度 那么用 屏蔽宽度的四分之一
            mCurrentProgressRigth = tempWidth;
            mCurrentProgressRigth = mCurrentProgressRigth + money / mMaxIntProgress * (mWidth - tempWidth);
        } else {
            mCurrentProgressRigth = tempWidth;
            mCurrentProgressRigth = mCurrentProgressRigth + money / mMaxIntProgress * (mWidth - tempWidth);
        }


        float cun = mPaintText.measureText(monthAndYer + "") + mPaintText.measureText(format(money) + "") + marginPaintTextLeftAndRigth * 2.6f;
        if (mCurrentProgressRigth < cun) {//主要计算 当文字很长的时  显示比例 在放大一些
            mCurrentProgressRigth = cun;
        }


        if (mCurrentProgressRigth > mWidth) {//如果大于屏蔽宽度 那么用 屏蔽宽度
            right = mWidth;
            XStartRight = mWidth - marginPaintTextLeftAndRigth;
        } else {//其他正常显示
            right = mCurrentProgressRigth;
            XStartRight = mCurrentProgressRigth - marginPaintTextLeftAndRigth;
        }

        XStartLeft = marginLeftAndRigth + marginPaintTextLeftAndRigth;
        YStartLeft = mHeight / mheightMag;
        YStartRight = mHeight / mheightMag;

        canvas.drawRect(left, top, right, bottom, mCurrentProgress);
        mPaintText.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(monthAndYer + "", XStartLeft, YStartLeft, mPaintText);

        mPaintText.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("" + format(money), XStartRight, YStartRight, mPaintText);

    }

    private String format(Object obj) {
        try {
            return String.format("%.2f", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    private float mheightMag = 1.6f;

    /**
     * 字体颜色
     *
     * @param mPaintTextColor
     */
    public void setmPaintTextColor(int mPaintTextColor) {
        this.mPaintTextColor = mPaintTextColor;
    }

    /**
     * 多少个进度的颜色
     *
     * @param mCurrentProgressColor
     */

    public void setmCurrentProgressColor(int mCurrentProgressColor) {
        this.mCurrentProgressColor = mCurrentProgressColor;
    }

    /**
     * 最长的进度 背景颜色
     *
     * @param mMaxProgressColor
     */
    public void setmMaxProgressColor(int mMaxProgressColor) {
        this.mMaxProgressColor = mMaxProgressColor;
    }

    /**
     * 设置数据
     *
     * @param mMaxIntProgress 设置最大的值
     * @param monthAndYer     月份
     * @param money           多少钱
     */
    public void setStart(float mMaxIntProgress, String monthAndYer, float money) {
        this.mMaxIntProgress = mMaxIntProgress;
        this.monthAndYer = monthAndYer;
        this.money = money;
        invalidate();
    }

    //根据手机的分辨率从 dp 的单位 转成为 px(像素)
    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}