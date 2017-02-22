package com.example.next.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Next on 2016/9/20.
 */
public class PathView extends View {

    private static final float C = 0.551915024494f;

    private Paint mPaint;

    private float[] mData = new float[8];
    private float[] mCtrl = new float[16];

    private int mHeight;
    private int mWidth;

    private int mRadio = 200;
    private float mDistance = mRadio * C;
    private float mDuration = 1000;
    private float mCurrent = 0;
    private float mCount = 100;
    private float mPiece = mDuration / mCount;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        initData();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        Path path = new Path();
        path.moveTo(mData[0], mData[1]);

        for (int i = 0, j = 0; i < 16; i += 4, j += 2) {
            if (j == 6) j = -2;
            path.cubicTo(mCtrl[i], mCtrl[i + 1], mCtrl[i + 2], mCtrl[i + 3], mData[j + 2], mData[j + 3]);
        }
        canvas.drawPath(path, mPaint);

        mCurrent += mPiece;
        if (mCurrent != mDuration/10) {
            mData[5] += mCount/8;

            mCtrl[1] -= mCount/10;
            mCtrl[15] -= mCount/10;
            postInvalidateDelayed(10);
        }
    }

    private void initData() {
        mData[0] = 0;
        mData[1] = mRadio;

        mData[2] = mRadio;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mRadio;

        mData[6] = -mRadio;
        mData[7] = 0;
//---------------------------------------------------------------------------------
        mCtrl[0] = mDistance;
        mCtrl[1] = mData[1];//1

        mCtrl[2] = mData[2];
        mCtrl[3] = mDistance;//2

        mCtrl[4] = mData[2];
        mCtrl[5] = -mDistance;//3

        mCtrl[6] = mDistance;
        mCtrl[7] = mData[5];//4

        mCtrl[8] = -mDistance;
        mCtrl[9] = mData[5];//5

        mCtrl[10] = mData[6];
        mCtrl[11] = -mDistance;//6

        mCtrl[12] = mData[6];
        mCtrl[13] = mDistance;//7

        mCtrl[14] = -mDistance;
        mCtrl[15] = mData[1];//8
    }
}
