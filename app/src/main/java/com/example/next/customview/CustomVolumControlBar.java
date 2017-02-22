package com.example.next.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Next on 2016/11/23.
 */
public class CustomVolumControlBar extends View {

    private int mFirstColor;
    private int mSecondColor;
    private int mCircleWidth;
    private Paint mPaint;
    private Bitmap mImage;
    private int mCurrentCount = 3;
    private int mSplitSize;
    private int mCount;
    private Rect mRect;


    public CustomVolumControlBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar);
        mFirstColor = typedArray.getColor(R.styleable.CustomProgressBar_firstColor, Color.WHITE);
        mSecondColor = typedArray.getColor(R.styleable.CustomProgressBar_secondColor, Color.BLACK);
        mCircleWidth = typedArray.getDimensionPixelSize(R.styleable.CustomProgressBar_circleWidth
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 40, getResources().getDisplayMetrics()));
        mImage = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.CustomVolumControlBar_image, R.drawable.round_gmail));
        mSplitSize = typedArray.getInteger(R.styleable.CustomVolumControlBar_splitSize, 20);
        mCount = typedArray.getInteger(R.styleable.CustomVolumControlBar_dotCount, 10);
        typedArray.recycle();
        mPaint = new Paint();
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        int center = getWidth() / 2;
        int radius = center - mCircleWidth / 2;

        drawOval(canvas, center, radius);

        int relRadius = radius - mCircleWidth / 2;
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

        if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
            mRect.right = mRect.left + mImage.getWidth();
            mRect.bottom = mRect.top + mImage.getHeight();
        }

        canvas.drawBitmap(mImage, null, mRect, mPaint);

    }

    private void drawOval(Canvas canvas, int centre, int radius)
    {
        /**
         * 根据需要画的个数以及间隙计算每个块块所占的比例*360
         */
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;

        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限

        mPaint.setColor(mFirstColor); // 设置圆环的颜色
        for (int i = 0; i < mCount; i++)
        {
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }

        mPaint.setColor(mSecondColor); // 设置圆环的颜色
        for (int i = 0; i < mCurrentCount; i++)
        {
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint); // 根据进度画圆弧
        }
    }

    private void up() {
        mCurrentCount++;
        postInvalidate();
    }

    private void down() {
        mCurrentCount--;
        postInvalidate();
    }

    private int xDown, xUp;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = (int)event.getY();
                break;
            case MotionEvent.ACTION_UP:
                xUp = (int)event.getY();
                if (xUp > xDown) {
                    down();
                } else {
                    up();
                }
                break;

        }
        return true;
    }
}
