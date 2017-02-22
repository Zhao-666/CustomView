package com.example.next.customview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Next on 2016/10/21.
 */
public class CircleItemAcivity extends AppCompatActivity {

    private ListAdapter mListAdapter;
    private List<Drawable> mDrawables = new ArrayList<>();
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_item_activity);
        mListView = (ListView) findViewById(R.id.id_list_view);
        initData();
        mListAdapter = new ListAdapter(this, mDrawables, mListView, 100);
        mListView.setAdapter(mListAdapter);
    }

    private void initData() {
        mDrawables.add(getResources().getDrawable(R.drawable.round_firefox));
        mDrawables.add(getResources().getDrawable(R.drawable.round_gmail));
        mDrawables.add(getResources().getDrawable(R.drawable.round_gplus));
        mDrawables.add(getResources().getDrawable(R.drawable.round_sound));
        mDrawables.add(getResources().getDrawable(R.drawable.round_superuser));
        mDrawables.add(getResources().getDrawable(R.drawable.round_twitter));
    }
}
