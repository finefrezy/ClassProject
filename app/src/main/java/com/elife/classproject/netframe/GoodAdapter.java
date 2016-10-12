package com.elife.classproject.netframe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elife.classproject.R;
import com.elife.classproject.network.GoodModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tzhang on 2016/9/13.
 */
public class GoodAdapter  extends BaseAdapter {
    List<GoodModel> mListGood;
    LayoutInflater mInflater;
    private Context context;
    public GoodAdapter( List<GoodModel> listGood, Context context) {
        this.mListGood = listGood;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public int getCount() {
        if (null == mListGood) {
            return 0;
        }
        return mListGood.size();
    }

    @Override
    public Object getItem(int i) {
        return mListGood.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (null == view) {
            view = mInflater.inflate(R.layout.item_list_good, null);
            viewHolder = new ViewHolder();

            viewHolder.imageGood = (ImageView) view.findViewById(R.id.good_photo);
            viewHolder.textName = (TextView) view.findViewById(R.id.good_name);
            viewHolder.textPrice = (TextView) view.findViewById(R.id.price);
            viewHolder.textPostFee = (TextView) view.findViewById(R.id.post_fee);

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


//        NetUtils.imageResult( viewHolder.imageGood, "http://10.50.8.79:8088/elife/" + mListGood.get(i).getGoodImg(), new NetUtils.OnCallBack() {
//            @Override
//            public void setBitmap(Bitmap bitmap, ImageView view) {
//                view.setImageBitmap(bitmap);
//            }
//        });

        Picasso.with(context)
                .load("http://10.50.8.79:8088/elife/" + mListGood.get(i).getGoodImg())
                .placeholder(R.drawable.live_head_passerby)
                .error(R.drawable.live_head_passerby)
                // 指定大小的图片
//                .resizeDimen(R.dimen.width_height, R.dimen.width_height)
//                .centerInside()
                .tag(context)
                // 填充imageview
                .fit()
                .into(viewHolder.imageGood);



        viewHolder.textName.setText(mListGood.get(i).getGoodName());
        viewHolder.textPrice.setText("￥ " + mListGood.get(i).getGoodPrice());

        if (mListGood.get(i).getGoodPostFee() == 0) {
            viewHolder.textPostFee.setText("免运费");

        } else {
            viewHolder.textPostFee.setText("运费: ￥" + mListGood.get(i).getGoodPostFee());

        }

        return view;
    }
}

class ViewHolder {
    ImageView imageGood;
    TextView textName;
    TextView textPrice;
    TextView textPostFee;

}
