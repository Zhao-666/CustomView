package com.example.next.customview;

import android.animation.TypeEvaluator;

/**
 * Created by Next on 2016/10/17.
 */
public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int startInt = startValue.getRadius();
        int endInt = endValue.getRadius();
        int curInt = (int) (startInt + fraction * (endInt - startInt));
        Point result = new Point(curInt);
        return result;
    }
}
