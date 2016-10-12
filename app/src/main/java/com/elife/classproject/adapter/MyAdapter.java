package com.elife.classproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elife.classproject.R;

import java.util.List;

/**
 * Created by tzhang on 2016/9/2.
 */
public class MyAdapter extends BaseAdapter{

    List<InfoModel> mInfoList;
    Context mContext;
    // 一般传递这样的两个参数，如果再多根据需要来看
    public MyAdapter(List<InfoModel> infoList, Context context) {
        mInfoList = infoList;
        mContext = context;
    }

    // 显示多少行记录，多少个item
    @Override
    public int getCount() {
        if (null == mInfoList) {
            return 0;
        }

        return mInfoList.size();
    }

    // 返回对应的list对象
    @Override
    public Object getItem(int i) {
        return mInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // 返回每一个item-》view
    // listview优化 ViewHolder
    @Override
    public View getView(int i, View view, ViewGroup ewGroup) {

        ViewHoler viewHoler = null;

        Log.d("====================", i + "===========");

        if (null == view) {
            // 解析自定义的布局成我们需要的view
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_demo, null);
            viewHoler = new ViewHoler();
            viewHoler.imageView = (ImageView) view.findViewById(R.id.photo);
            viewHoler.icon = (ImageView) view.findViewById(R.id.icon);
            viewHoler.textName = (TextView) view.findViewById(R.id.name);
            viewHoler.textDesc = (TextView) view.findViewById(R.id.describe);
            viewHoler.textTime = (TextView) view.findViewById(R.id.time);


            view.setTag(viewHoler);
        } else {
            viewHoler = (ViewHoler) view.getTag();
        }
        viewHoler.imageView.setBackgroundResource(mInfoList.get(i).getPhoto());
        viewHoler.icon.setImageResource(mInfoList.get(i).getIcon());
        viewHoler.textName.setText(mInfoList.get(i).getName());
        viewHoler.textDesc.setText(mInfoList.get(i).getDescribe());
        viewHoler.textTime.setText(mInfoList.get(i).getTime());
        return view;
    }


    class ViewHoler {
        ImageView imageView;
        ImageView icon;
        TextView textName;
        TextView textDesc;
        TextView textTime;
    }
}
