package com.elife.classproject.logistics;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.elife.classproject.R;

/**
 * Created by tzhang on 2015/12/3.
 */
public class AddressAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private LogisticsModel mLogisticsModel;
    private Context mContext;

    public AddressAdapter(Context context, LogisticsModel logisticsModel) {
        mInflater = LayoutInflater.from(context);
        mLogisticsModel = logisticsModel;
        mContext = context;
    }

    @Override
    public int getCount() {
        if (null == mLogisticsModel.result.list) {
            return 0;
        }
        return mLogisticsModel.result.list.size();
    }

    @Override
    public Object getItem(int position) {
        return mLogisticsModel.result.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.linear_list_item
                    , null);
            viewHolder.imageIcon = (ImageView) convertView.findViewById(R.id.image_icon);
            viewHolder.imageLine = (ImageView) convertView.findViewById(R.id.image_line);
            viewHolder.textPoint = (TextView) convertView.findViewById(R.id.point_address);
            viewHolder.textTime = (TextView) convertView.findViewById(R.id.point_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == 0) {
            //TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, mContext.getResources().getDisplayMetrics());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(CommonUtil.dip2px(mContext, 24), CommonUtil.dip2px(mContext, 24));
            params.gravity = Gravity.CENTER_HORIZONTAL;
            params.topMargin = CommonUtil.dip2px(mContext, 10);
            viewHolder.imageIcon.setLayoutParams(params);
            viewHolder.imageIcon.setBackgroundResource(R.drawable.corner_shape);

            FrameLayout.LayoutParams paramsLine = new FrameLayout.LayoutParams(CommonUtil.dip2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            paramsLine.gravity = Gravity.CENTER_HORIZONTAL;
            paramsLine.topMargin = CommonUtil.dip2px(mContext, 20);
            viewHolder.imageLine.setLayoutParams(paramsLine);

            viewHolder.textPoint.setText(mLogisticsModel.result.list.get(position).remark);
            viewHolder.textTime.setText(mLogisticsModel.result.list.get(position).datetime);
            viewHolder.textPoint.setTextColor(mContext.getResources().getColor(R.color.point_green));
            viewHolder.textTime.setTextColor(mContext.getResources().getColor(R.color.point_green));
        } else {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(CommonUtil.dip2px(mContext, 16), CommonUtil.dip2px(mContext, 16));
            params.gravity = Gravity.CENTER_HORIZONTAL;
            params.topMargin = CommonUtil.dip2px(mContext, 13);
            viewHolder.imageIcon.setLayoutParams(params);
            viewHolder.imageIcon.setBackgroundResource(R.drawable.corner_gray_shape);

            FrameLayout.LayoutParams paramsLine = new FrameLayout.LayoutParams(CommonUtil.dip2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            paramsLine.gravity = Gravity.CENTER_HORIZONTAL;
            viewHolder.imageLine.setLayoutParams(paramsLine);

            viewHolder.textPoint.setText(mLogisticsModel.result.list.get(position).remark);
            viewHolder.textTime.setText(mLogisticsModel.result.list.get(position).datetime);

            viewHolder.textPoint.setTextColor(mContext.getResources().getColor(R.color.point_dark_gray));
            viewHolder.textTime.setTextColor(mContext.getResources().getColor(R.color.point_dark_gray));
        }
        return convertView;
    }

    class ViewHolder {
        ImageView imageLine;
        ImageView imageIcon;
        TextView textPoint;
        TextView textTime;
    }
}
