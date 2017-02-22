package com.example.next.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Next on 2016/11/22.
 */
public class CustomeProgressBar extends View {

    private int mFirstColor;
    private int mSecondColor;
    private int mCircleWidth;
    private int mSpeed;
    private Paint mPaint;
    private int mProgress = 0;
    private Boolean isNext = false;

    public CustomeProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar);
        mFirstColor = typedArray.getColor(R.styleable.CustomProgressBar_firstColor, Color.BLACK);
        mSecondColor = typedArray.getColor(R.styleable.CustomProgressBar_secondColor, Color.WHITE);
        mCircleWidth = typedArray.getDimensionPixelSize(R.styleable.CustomProgressBar_circleWidth
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 40, getResources().getDisplayMetrics()));
        mSpeed = typedArray.getInteger(R.styleable.CustomProgressBar_speed, 20);

        typedArray.recycle();
        mPaint = new Paint();

        new Thread() {
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (isNext) {
                            isNext = false;
                        } else {
                            isNext = true;
                        }
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2;
        int radius = center - mCircleWidth / 2;
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rect = new RectF(center - radius, center - radius, center + radius, center + radius);

        if (isNext) {
            mPaint.setColor(mFirstColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mSecondColor);
            canvas.drawArc(rect, -90, mProgress, false, mPaint);
        } else {
            mPaint.setColor(mSecondColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mFirstColor);
            canvas.drawArc(rect, -90, mProgress, false, mPaint);
        }
    }
}
