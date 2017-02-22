package com.example.next.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Next on 2016/9/27.
 */
public class CounterView extends View implements View.OnClickListener {

    private Paint mPaint;
    private int mCount;

    public CounterView(Context context) {
        this(context, null);
    }

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCount = 0;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(Color.GREEN);
        Rect backRect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawRect(backRect, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(100);
        canvas.drawPoint(getWidth()/2,getHeight()/2,mPaint);
        mPaint.setColor(Color.BLACK);

        mPaint.setTextSize(100);
        Rect bounds = new Rect();
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text, 0, 1, bounds);
        float width = bounds.width();
        float height = bounds.height();
        canvas.drawText(text, getWidth() / 2 - width/2, getHeight() / 2 + height/2, mPaint);

    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
