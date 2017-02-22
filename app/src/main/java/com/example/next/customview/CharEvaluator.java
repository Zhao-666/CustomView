package com.example.next.customview;

import android.animation.TypeEvaluator;

/**
 * Created by Next on 2016/10/17.
 */
public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int curInt = (int)(startInt + fraction * (endInt - startInt));
        char result = (char) curInt;
        return result;
    }
}
