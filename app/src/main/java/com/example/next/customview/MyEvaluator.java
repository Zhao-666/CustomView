package com.example.next.customview;

import android.animation.TypeEvaluator;

/**
 * Created by Next on 2016/10/14.
 */
public class MyEvaluator implements TypeEvaluator<Integer> {

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int curValue = endValue;
        return (int) (curValue - fraction * (curValue - startValue));
    }
}
