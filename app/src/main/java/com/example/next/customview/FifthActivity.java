package com.example.next.customview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Next on 2016/10/20.
 */
public class FifthActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mMenuButton;
    private Button mItemButton1;
    private Button mItemButton2;
    private Button mItemButton3;
    private Button mItemButton4;
    private Button mItemButton5;

    private Boolean mIsMenuOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initView();
    }

    /*private void initView() {
        mMenuButton = (Button) findViewById(R.id.id_button_gmail);
        mMenuButton.setOnClickListener(this);

        mItemButton1 = (Button) findViewById(R.id.id_button_sound);
        mItemButton1.setOnClickListener(this);

        mItemButton2 = (Button) findViewById(R.id.id_button_superuser);
        mItemButton2.setOnClickListener(this);

        mItemButton3 = (Button) findViewById(R.id.id_button_twitter);
        mItemButton3.setOnClickListener(this);

        mItemButton4 = (Button) findViewById(R.id.id_button_gplus);
        mItemButton4.setOnClickListener(this);

        mItemButton5 = (Button) findViewById(R.id.id_button_firefox);
        mItemButton5.setOnClickListener(this);
    }
*/
    @Override
    public void onClick(View v) {
        if (v == mMenuButton) {
            if (!mIsMenuOpen) {
                mIsMenuOpen = true;
                doAnimateOpen(mItemButton1, 0, 400);
                doAnimateOpen(mItemButton2, 1, 400);
                doAnimateOpen(mItemButton3, 2, 400);
                doAnimateOpen(mItemButton4, 3, 400);
                doAnimateOpen(mItemButton5, 4, 400);
            } else {
                mIsMenuOpen = false;
                doAnimateClose(mItemButton1, 0, 400);
                doAnimateClose(mItemButton2, 1, 400);
                doAnimateClose(mItemButton3, 2, 400);
                doAnimateClose(mItemButton4, 3, 400);
                doAnimateClose(mItemButton5, 4, 400);
            }
        } else {
            Toast.makeText(this, v.toString(), Toast.LENGTH_SHORT);
        }
    }

    private void doAnimateOpen(Button itemButton, int index, int radius) {
        if (itemButton.getVisibility() != View.VISIBLE) {
            itemButton.setVisibility(View.VISIBLE);
        }

        double degree = Math.toRadians(90) / 4 * index;

        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet animator = new AnimatorSet();
        animator.setDuration(500).playTogether(
                ObjectAnimator.ofFloat(itemButton, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(itemButton, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(itemButton, "scaleX", 0, 1),
                ObjectAnimator.ofFloat(itemButton, "scaleY", 0, 1),
                ObjectAnimator.ofFloat(itemButton, "alpha", 0, 1)
        );
        animator.start();
    }

    private void doAnimateClose(Button itemButton, int index, int radius) {
        if (itemButton.getVisibility() != View.VISIBLE) {
            itemButton.setVisibility(View.VISIBLE);
        }

        double degree = Math.toRadians(90) / 4 * index;

        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet animator = new AnimatorSet();
        animator.setDuration(500).playTogether(
                ObjectAnimator.ofFloat(itemButton, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(itemButton, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(itemButton, "scaleX", 1, 0),
                ObjectAnimator.ofFloat(itemButton, "scaleY", 1, 0),
                ObjectAnimator.ofFloat(itemButton, "alpha", 1, 0)
        );
        animator.start();
    }
}
