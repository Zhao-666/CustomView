package com.example.next.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Next on 2016/10/21.
 */
public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Drawable> mDrawables;
    private int mlength = 0;
    private ListView mListView;
    private Animation mAnimation;
    private int mFirstItem = 0, mFirstTop = 0;
    public boolean isScroll = false;

    public ListAdapter(Context context, List<Drawable> drawables, ListView listView, int length) {
        this.mContext = context;
        this.mDrawables = drawables;
        this.mListView = listView;
        this.mlength = length;
        mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.bottom_in_anim);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View firstChild = view.getChildAt(0);
                if (firstChild == null) return;
                int top = firstChild.getTop();

                isScroll = firstVisibleItem > mFirstItem || mFirstTop > top;
                mFirstItem = firstVisibleItem;
                mFirstTop = top;
            }
        });
    }

    @Override
    public int getCount() {
        return mlength;
    }

    @Override
    public Object getItem(int position) {
        return mDrawables.get(position % mDrawables.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder viewHolder;
        if (convertView == null) {
            viewHolder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.id_image_view);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.id_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }
        if (isScroll) {
            convertView.startAnimation(mAnimation);
        }
        viewHolder.mImageView.setImageDrawable(mDrawables.get(position % mDrawables.size()));
        viewHolder.mTextView.setText(String.valueOf(position));
        return convertView;
    }

    class Holder {
        public ImageView mImageView;
        public TextView mTextView;
    }
}
