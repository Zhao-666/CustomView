package com.example.next.customview;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Next on 2016/10/18.
 */
public class FourthAcivity extends AppCompatActivity {

    private Button mStart;
    private Button mCancel;

    private MyTextView mMyTextview;

    private AnimatorSet mAnimatorSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mStart = (Button) findViewById(R.id.id_button_start);
       // mCancel = (Button) findViewById(R.id.id_button_cancel);
       // mMyTextview = (MyTextView) findViewById(R.id.id_text_view);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimatorSet = doTextViewAnimation();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimatorSet.cancel();
            }
        });

    }

    private AnimatorSet doTextViewAnimation() {
        Keyframe keyframe = Keyframe.ofFloat(0, 0);
        Keyframe keyframe1 = Keyframe.ofFloat(0.5f, 100);
        Keyframe keyframe2 = Keyframe.ofFloat(1, 0);

        PropertyValuesHolder textHolder = PropertyValuesHolder.ofKeyframe("rotation", keyframe, keyframe1, keyframe2);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mMyTextview, textHolder);
        ObjectAnimator animator1 = ObjectAnimator.ofInt(mMyTextview, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mMyTextview, "TranslationY", 0, 400, 0);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator).with(animator1).after(animator2);
        animatorSet.setDuration(1000);
        animatorSet.start();
        return animatorSet;
    }
}
