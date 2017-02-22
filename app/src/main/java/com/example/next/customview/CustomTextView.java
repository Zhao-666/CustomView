package com.example.next.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Next on 2016/11/8.
 */
public class CustomTextView extends View {

    private final int IMAGE_SCALE_FITXY = 0;

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    private int mImageScaleType;
    private Bitmap mImage;

    private Rect mBound;
    private Paint mPaint;
    private Rect mRect;
    private int mWidth;
    private int mHeight;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);

        mTitleText = array.getString(R.styleable.CustomTitleView_titleText);
        mTitleTextSize = array.getDimensionPixelSize(
                R.styleable.CustomTitleView_titleTextSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 40, getResources().getDisplayMetrics()));
        mTitleTextColor = array.getColor(R.styleable.CustomTitleView_titleTextcolor, Color.BLACK);
        mImage = BitmapFactory.decodeResource(getResources(), array.getResourceId(R.styleable.CustomTitleView_image, 0));
        mImageScaleType = array.getInt(R.styleable.CustomTitleView_imageScaleType, 0);

        array.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mRect = new Rect();

        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText = RandomNumber();
                postInvalidate();
            }
        });
    }

    private String RandomNumber() {

        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 4) {
            int num = random.nextInt(10);
            set.add(num);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer i : set) {
            sb.append("" + i);
        }
        return sb.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {

            int desireByImg = getPaddingLeft() + mImage.getWidth() + getPaddingRight();
            int desireByText = getPaddingLeft() + mBound.width() + getPaddingRight();

            mWidth = Math.max(desireByImg, desireByText);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {


            mHeight = getPaddingTop() + mImage.getHeight() + mBound.height() + getPaddingBottom();
        }

        setMeasuredDimension(mWidth, mHeight);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(mTitleTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        if (mBound.width() > mWidth) {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleText, paint,
                    (float) mWidth - getPaddingLeft() - getPaddingRight(), TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        } else {
            canvas.drawText(mTitleText, mWidth / 2 - mBound.width() / 2, mHeight - getPaddingBottom(), mPaint);
        }

        mRect.bottom -= mBound.height();

        if (mImageScaleType == IMAGE_SCALE_FITXY) {
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        } else {
            mRect.left = mWidth / 2 - mImage.getWidth() / 2;
            mRect.right = mWidth / 2 + mImage.getWidth()/2;
            mRect.top = (mHeight - mBound.height()) / 2 - mImage.getHeight() / 2;
            mRect.bottom = (mHeight - mBound.height()) / 2 + mImage.getHeight()/2;

            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }
    }

}
