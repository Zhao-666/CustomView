package com.example.next.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Next on 2016/9/19.
 */
public class CanvasView extends View {

    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private int mAngle;
    private int mAngle2;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mAngle = 0;
        mAngle2 = 0;
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
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(mWidth / 2, mHeight / 2);
        for (int i = 50; i <= 400; i += 50) {
            canvas.drawCircle(0, 0, i, mPaint);
        }
        for (int i = 50; i < 400; i += 100) {
            canvas.rotate(mAngle);
            canvas.drawLine(0, i, 0, i + 50, mPaint);
            canvas.drawLine(0, -i, 0, -(i + 50), mPaint);
        }
        canvas.restore();
        canvas.translate(mWidth / 2, mHeight / 2);
        for (int i = 0; i < 400; i += 100) {
            canvas.rotate(mAngle2);
            canvas.drawLine(0, i, 0, i+50, mPaint);
            canvas.drawLine(0, -i, 0, -(i + 50), mPaint);
        }
        mAngle += 2;
        mAngle2 -= 2;
        postInvalidateDelayed((long) 10);
    }


}
