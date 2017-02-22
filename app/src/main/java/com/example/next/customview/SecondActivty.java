package com.example.next.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Next on 2016/9/19.
 */
public class SecondActivty extends AppCompatActivity {

    private MyAdapter mMyAdapter;

    private MyListView mMyListView;

    private List<String> mStringList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mMyAdapter = new MyAdapter(this, R.layout.item_listview, mStringList);
       // mMyListView = (MyListView) findViewById(R.id.id_list_view);
        mMyListView.setOnDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                mStringList.remove(index);
                mMyAdapter.notifyDataSetChanged();
            }
        });
        mMyListView.setAdapter(mMyAdapter);
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mStringList.add("ZXHSB " + i);
        }
    }
}
