package com.elife.classproject.recycler.waterfall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.elife.classproject.R;

public class DeliciousFoodVH extends RecyclerView.ViewHolder {

	ImageView mUrlImage;
	TextView mNameText;
	TextView mDescTest;
	ImageView mPraiseImage;
	public int mPosition;
	
	public Context mContext;
	
	
	public DeliciousFoodVH(View itemView, final DeliciousFoodAdapter.OnItemClickListener onItemClistListener) {
		super(itemView);
		mUrlImage = (ImageView) itemView.findViewById(R.id.item_img);
		mNameText = (TextView) itemView.findViewById(R.id.item_title);
		mDescTest = (TextView) itemView.findViewById(R.id.item_desc);
		mPraiseImage = (ImageView) itemView.findViewById(R.id.praise);

		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (null != onItemClistListener) {
					onItemClistListener.onItemClick(mPosition);
				}
			}
		});
		
		mPraiseImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("DeliciousFoodVH", "" + mPosition);
			}
		});
		
	}

}
