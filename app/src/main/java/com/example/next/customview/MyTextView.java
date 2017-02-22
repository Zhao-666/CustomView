package com.example.next.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Next on 2016/10/10.
 */
public class MyTextView extends TextView {

    private static final String TAG = "MyTextView";

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCharText(Character charText) {
        setText(String.valueOf(charText));
    }
}
