package com.example.next.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by Next on 2016/9/28.
 */
public class TitleView extends FrameLayout {

    private Button mBackButton;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.view_title, this);
        mBackButton = (Button) findViewById(R.id.button_back);
        mBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TitleView", "onClick");
                ((Activity) getContext()).finish();
            }
        });
    }

}
