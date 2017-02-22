package com.example.next.customview;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Next on 2016/10/10.
 */
public class ThirdActivity extends AppCompatActivity {

    private Button mStart;
    private Button mCancel;

    private MyPointView mMyPointView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mStart = (Button) findViewById(R.id.id_button_start);
       // mCancel = (Button) findViewById(R.id.id_button_cancel);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPointViewAnimation();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    public void doPointViewAnimation() {
        ObjectAnimator objectAnimator = new ObjectAnimator().ofInt(mMyPointView, "pointRadius", 0, 800, 500);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

}
