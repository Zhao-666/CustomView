package com.example.next.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Next on 2016/9/18.
 */
public class PieView extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private ArrayList<PieData> mPieDatas;
    private int mHeight, mWidth;
    private Paint mPaint = new Paint();


    private float mStartAngle = 0;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
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
        if (mPieDatas == null) return;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mHeight, mWidth) / 2 * 0.8);
        RectF rectF = new RectF(-r, -r, r, r);

        for (int i = 0; i < mPieDatas.size(); i++) {
            PieData pieData = mPieDatas.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rectF, mStartAngle, pieData.getAngle(), true, mPaint);
            mStartAngle += pieData.getAngle();
        }
    }

    public void setStartAngle(float startAngle) {
        this.mStartAngle = startAngle;
        invalidate();
    }

    public void setPieDatas(ArrayList<PieData> pieDatas) {
        this.mPieDatas = pieDatas;
        initData(pieDatas);
        invalidate();
    }

    private void initData(ArrayList<PieData> pieDatas) {
        if (pieDatas == null || pieDatas.size() == 0) return;

        float sumValues = 0;
        for (int i = 0; i < pieDatas.size(); i++) {
            PieData pieData = pieDatas.get(i);
            int j = i % mColors.length;
            pieData.setColor(mColors[j]);
            sumValues += pieData.getValue();
        }
        for (int i = 0; i < pieDatas.size(); i++) {
            PieData pieData = pieDatas.get(i);
            float angle = (pieData.getValue() / sumValues) * 360;
            pieData.setAngle(angle);
        }
    }
}
