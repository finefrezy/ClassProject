package com.elife.classproject.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elife.classproject.R;
import com.elife.classproject.project.constant.ConstantData;
import com.elife.classproject.project.model.CategoryGoodsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tzhang on 2016/9/24.
 */
public class NormalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private INormalAdapterListener mNormalAdapterListener;
    private List<CategoryGoodsModel.GoodDetailModel> mGoodDetailList;
    private Context mContext;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER = 1;


    public NormalAdapter(List<CategoryGoodsModel.GoodDetailModel> goodDetailList, Context ctx) {
        this.mGoodDetailList = goodDetailList;
        this.mContext = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        switch (viewType) {
//            case TYPE_ITEM:
//                break;
//            case TYPE_HEADER:
                View view = View.inflate(parent.getContext(), R.layout.normal_adapter_item,
                        null);
                return new NormalViewHolder(view);
//        }



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
        normalViewHolder.position = position;

        normalViewHolder.textName.setText(mGoodDetailList.get(position).title + " " + mGoodDetailList.get(position).standard);
        normalViewHolder.textPrice.setText(mGoodDetailList.get(position).price + "元");
        Picasso.with(mContext).load(ConstantData.SERVER_URL_IMAGE_CATE + mGoodDetailList.get(position).img).into(normalViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        if (null == mGoodDetailList) {
            return 0;
        }
        return mGoodDetailList.size();
    }

    public interface INormalAdapterListener {
        public void onItemClick(int position);
    }

    public void setOnRecyclerViewListener(Context activity) {
        mNormalAdapterListener = (INormalAdapterListener) activity;
    }


    class NormalViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public ImageView imageView;
        public TextView textName;
        public TextView textPrice;
        public int position;


        public NormalViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.normal_image);
            textPrice = (TextView) itemView.findViewById(R.id.price_good);
            textName = (TextView) itemView.findViewById(R.id.name_good);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (null != mNormalAdapterListener) {
                mNormalAdapterListener.onItemClick(position);
            }
        }
    }
}




