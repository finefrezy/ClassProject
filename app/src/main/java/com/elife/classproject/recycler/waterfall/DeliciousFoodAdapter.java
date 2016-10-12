package com.elife.classproject.recycler.waterfall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.elife.classproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DeliciousFoodAdapter extends RecyclerView.Adapter<DeliciousFoodVH> {

	private List<DeliciousFoodModel> mDeliciousFoodList;
	private Context mContext;
	private OnItemClickListener onItemClistListener;

	public DeliciousFoodAdapter(Context ctx, List<DeliciousFoodModel> deliciousFoodList) {
		mDeliciousFoodList = deliciousFoodList;
		mContext = ctx;
	}

	@Override
	public int getItemCount() {
		if (null == mDeliciousFoodList) {
			return 0;
		}
		return mDeliciousFoodList.size();
	}

	/**
	 * 为控件填充内容
	 */
	@Override
	public void onBindViewHolder(DeliciousFoodVH viewHolder, int position) {

		viewHolder.mContext = mContext;
		viewHolder.mPosition = position;

		Picasso.with(mContext)
				.load(mDeliciousFoodList.get(position).foodImg)
				.placeholder(R.drawable.head_icon1)
				.error(R.drawable.head_icon1)
				// 指定大小的图片
//                .resizeDimen(R.dimen.width_height, R.dimen.width_height)
//                .centerInside()
				.tag(mContext)
				// 填充imageview
				.fit()
				.into(viewHolder.mUrlImage);


		viewHolder.mNameText.setText(mDeliciousFoodList.get(position).foodName);
		viewHolder.mDescTest.setText(mDeliciousFoodList.get(position).foodDesc);
		viewHolder.mPraiseImage.setTag(position + "");

	}

	@Override
	public DeliciousFoodVH onCreateViewHolder(ViewGroup parent, final int position) {
		// View view = LayoutInflater.from(parent.getContext()).inflate(
		// R.layout.water_fall_item, parent, false);

		Log.d("DeliciousFoodVH", "onCreateViewHolder = " + position);
		View view = View.inflate(parent.getContext(), R.layout.water_fall_item,
				null);



		return new DeliciousFoodVH(view, onItemClistListener);
	}

	public interface OnItemClickListener {
		void onItemClick(int postion);
	}

	public void setOnItemClistListener(OnItemClickListener itemClistListener) {
		this.onItemClistListener = itemClistListener;
	}

}
