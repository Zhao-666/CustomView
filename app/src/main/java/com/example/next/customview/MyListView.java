package com.example.next.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by Next on 2016/9/29.
 */
public class MyListView extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;

    private OnDeleteListener mOnDeleteListener;

    private View mDeleteButton;

    private ViewGroup itemLayout;

    private int mSelectedItem;

    private boolean isDeleteShown;

    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(getContext(), this);
        setOnTouchListener(this);
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        mOnDeleteListener = listener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isDeleteShown) {
            itemLayout.removeView(mDeleteButton);
            mDeleteButton = null;
            isDeleteShown = false;
            return false;
        } else {
            return mGestureDetector.onTouchEvent(event);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!isDeleteShown) {
            mSelectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        }
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY)) {
            mDeleteButton = LayoutInflater.from(getContext()).inflate(R.layout.delete_button, null);
            mDeleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemLayout.removeView(mDeleteButton);
                    mDeleteButton = null;
                    isDeleteShown = false;
                    mOnDeleteListener.onDelete(mSelectedItem);
                }
            });
            itemLayout = (ViewGroup) getChildAt(mSelectedItem - getFirstVisiblePosition());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            itemLayout.addView(mDeleteButton, params);
            isDeleteShown = true;
        }
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    public interface OnDeleteListener {
        void onDelete(int index);
    }
}
