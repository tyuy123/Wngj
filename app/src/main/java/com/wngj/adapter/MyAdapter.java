package com.wngj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wngj.bean.MyBean;
import com.wngj.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NiKOo on 16/11/30.
 */
public class MyAdapter extends BaseAdapter{
    private Context mContext;
    private List<MyBean> mList = new ArrayList<MyBean>();

    public MyAdapter(Context context, List<MyBean> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, null);
        }
        TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
        MyBean bean = mList.get(position);
        tv_item.setText(bean.title);
        tv_item.setBackgroundColor(Color.parseColor("#"+bean.color));
        return view;
    }
}
