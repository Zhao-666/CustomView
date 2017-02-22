package com.example.next.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Next on 2016/9/21.
 */
public class CircleView extends View {

    private Paint mPaint;
    private int mHeight;
    private int mWidth;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
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
        mPaint.setStrokeWidth(8);

        canvas.translate(mWidth / 2, mHeight / 2);

        Path path = new Path();
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        RectF rectF = new RectF();

        path.addCircle(0, 0, 200, Path.Direction.CW);
        path1.addRect(0, -200, 200, 200, Path.Direction.CW);
        path2.addCircle(0, -100, 100, Path.Direction.CW);
        path3.addCircle(0, 100, 100, Path.Direction.CW);

        path.op(path1, Path.Op.DIFFERENCE);
        path.op(path2, Path.Op.UNION);
        path.op(path3, Path.Op.DIFFERENCE);

        path.computeBounds(rectF, true);

        canvas.drawPath(path, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(rectF, mPaint);
    }
}
