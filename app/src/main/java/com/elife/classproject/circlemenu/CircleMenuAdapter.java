package com.elife.classproject.circlemenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elife.classproject.R;

import java.util.List;

/**
 * Created by tzhang on 2016/10/20.
 */
public class CircleMenuAdapter extends BaseAdapter{

    List<MenuItem> mMenuItems;

    public CircleMenuAdapter(List<MenuItem> mMenuItems) {
        this.mMenuItems = mMenuItems;
    }

    @Override
    public int getCount() {
        return mMenuItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mMenuItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.circle_menu_item, viewGroup, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
        TextView textView = (TextView) itemView.findViewById(R.id.text);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(mMenuItems.get(i).imageId);

        textView.setVisibility(View.VISIBLE);
        textView.setText(mMenuItems.get(i).tilte);

        return itemView;
    }

}
