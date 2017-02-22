package com.example.next.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by Next on 2016/11/1.
 */
public class MyNewListView extends ListView {

    private Scroller mScroller;
    private LinearLayout mLinearLayout;
    private int mLastX = 0;
    private final int MAX_WIDTH = 100;


    public MyNewListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context, new LinearInterpolator(context, attrs));
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mLinearLayout.scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    private int dipToPx(Context context, int dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int maxlenght = dipToPx(getContext(), MAX_WIDTH);

        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int position = pointToPosition(x, y);
                if (position != INVALID_POSITION) {
                    MyNewAdapter.DataHolder item = (MyNewAdapter.DataHolder) getItemAtPosition(position);
                    mLinearLayout = item.rootView;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int scrollX = mLinearLayout.getScrollX();
                int newScrollX = scrollX + mLastX - x;
                if (newScrollX < 0) {
                    newScrollX = 0;
                } else if (newScrollX > maxlenght) {
                    newScrollX = maxlenght;
                }
                mLinearLayout.scrollTo(newScrollX, 0);
                break;
            case MotionEvent.ACTION_UP:
                scrollX = mLinearLayout.getScrollX();
                if (scrollX > maxlenght / 2) {
                    newScrollX = maxlenght;
                } else {
                    newScrollX = 0;
                }
                mScroller.startScroll(scrollX, 0, newScrollX - scrollX, 0);
                invalidate();
                break;
        }
        mLastX = x;

        return super.onTouchEvent(ev);
    }
}
