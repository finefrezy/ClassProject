package com.elife.classproject.recycler.waterfall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elife.classproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DeliciousFoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<DeliciousFoodModel> mDeliciousFoodList;
	private Context mContext;
	private OnItemClickListener onItemClistListener;

	private static int VIEW_ITEM = 1;
	private static int VIEW_HEADER = 0;

	public DeliciousFoodAdapter(Context ctx, List<DeliciousFoodModel> deliciousFoodList) {
		mDeliciousFoodList = deliciousFoodList;
		mContext = ctx;
	}

	@Override
	public int getItemCount() {
		if (null == mDeliciousFoodList) {
			return 0;
		}
		return mDeliciousFoodList.size() + 1;
	}

	@Override
	public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
		super.onViewAttachedToWindow(holder);
		ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
		if (lp != null
				&& lp instanceof StaggeredGridLayoutManager.LayoutParams
				&& holder.getLayoutPosition() == 0) {
			StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
			p.setFullSpan(true);
		}
	}

	@Override
	public int getItemViewType(int position) {
		if (position == 0) {
			return VIEW_HEADER;
		} else {
			return VIEW_ITEM;
		}

	}

	/**
	 * 为控件填充内容
	 */
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {

		if( vh instanceof DeliciousFoodVH) {
			DeliciousFoodVH viewHolder = (DeliciousFoodVH) vh;
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
		} else if (vh instanceof WaterHeader) {
			((WaterHeader) vh).textView.setText("11111111111111111111111111111111111111111111");
		}

	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int position) {
		// View view = LayoutInflater.from(parent.getContext()).inflate(
		// R.layout.water_fall_item, parent, false);

		Log.d("DeliciousFoodVH", "onCreateViewHolder = " + position);


		if (position == 0){
			TextView textView = new TextView(mContext);
			return new WaterHeader(textView);
		} else {
			View view = View.inflate(parent.getContext(), R.layout.water_fall_item,
					null);
			return new DeliciousFoodVH(view, onItemClistListener);
		}

	}

	public interface OnItemClickListener {
		void onItemClick(int postion);
	}

	public void setOnItemClistListener(OnItemClickListener itemClistListener) {
		this.onItemClistListener = itemClistListener;
	}

}
