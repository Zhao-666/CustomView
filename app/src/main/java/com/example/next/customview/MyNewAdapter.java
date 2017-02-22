package com.example.next.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Next on 2016/11/1.
 */
public class MyNewAdapter extends ArrayAdapter<MyNewAdapter.DataHolder> {

    private List<DataHolder> mDataLists = new ArrayList<>();
    private int mResource;
    private View.OnClickListener mOnClickListener;

    public MyNewAdapter(Context context, int resource, List<DataHolder> objects, View.OnClickListener onClickListener) {
        super(context, resource, objects);
        mDataLists = objects;
        mResource = resource;
        mOnClickListener = onClickListener;
    }

    @Override
    public DataHolder getItem(int position) {
        return mDataLists.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.id_title_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DataHolder item = mDataLists.get(position);
        viewHolder.mTextView.setText(item.title);
        TextView textView = (TextView) convertView.findViewById(R.id.id_delect_text);
        textView.setOnClickListener(mOnClickListener);
        item.rootView = (LinearLayout) convertView.findViewById(R.id.id_lin_root);
        item.rootView.scrollTo(0, 0);
        return convertView;
    }

    public void removeItem(int position) {
        mDataLists.remove(position);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView mTextView;
    }

    public static class DataHolder {
        String title;
        LinearLayout rootView;
    }

}
