package com.example.next.customview;

import android.animation.TimeInterpolator;

/**
 * Created by Next on 2016/10/14.
 */
public class MyInterPolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return 1-input;
    }
}
