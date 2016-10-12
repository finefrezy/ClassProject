package com.elife.classproject.forum;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.elife.classproject.R;
import com.elife.classproject.forum.cornerrect.ImageCropUtil;
import com.elife.classproject.forum.holder.FooterViewHolder;
import com.elife.classproject.forum.holder.FroumViewHolder;
import com.elife.classproject.logistics.CommonUtil;

import java.util.List;


public class ForumAdapter extends RecyclerView.Adapter<ViewHolder> {
	List<FroumModel> mForumList;
	Context mContext;
	private int mScreenWidth;
	
	 private static final int TYPE_ITEM = 0;  
	 private static final int TYPE_FOOTER = 1; 

	public ForumAdapter(List<FroumModel> forumList, Context ctx, int width) {
		this.mForumList = forumList;
		mContext = ctx;
		mScreenWidth = width;
	}

	@Override  
	 public int getItemViewType(int position) {  
	  // 最后一个item设置为footerView  
	  if (position + 1 == getItemCount()) {  
	   return TYPE_FOOTER;  
	  } else {  
	   return TYPE_ITEM;  
	  }  
	 }
	
	@Override
	public int getItemCount() {
		if (null == mForumList) {
			return 0;
		}
		return mForumList.size() + 1;
	}

	@Override
	public void onBindViewHolder(ViewHolder vh, int position) {
		 if (!(vh instanceof FroumViewHolder)) {
			 return;
		 }
		 
		Log.d("onBindViewHolder", position + "");
		
		FroumViewHolder viewHolder = (FroumViewHolder) vh;
		viewHolder.mPosition = position;
		viewHolder.mContext = mContext;
		
		viewHolder.mNameText.setText(mForumList.get(position).userName);

		Bitmap bitmap = ImageCropUtil.toRoundBitmap(ImageCropUtil
				.decodeResource(mContext, mForumList.get(position).iconId, "",
						CommonUtil.dip2px(mContext, 30),
						CommonUtil.dip2px(mContext, 30), ScaleType.FIT_XY));
		viewHolder.mImageIcon.setImageBitmap(bitmap);
		viewHolder.mMesgText.setText(mForumList.get(position).msgContent);
		viewHolder.mTimeText.setText(CommonUtil.timeFormat(mForumList
				.get(position).timeValue));
		
		viewHolder.mLikeCountText.setText(String.valueOf(mForumList.get(position).likeCount));

		ImageAdapter imageAdapter = new ImageAdapter(
				mForumList.get(position).imgResIds, mContext, mScreenWidth);

		
		
		int tempWidth = 0;
		if (mForumList.get(position).imgResIds.length <= 3) {
			tempWidth = mScreenWidth / 3;
		} else if (mForumList.get(position).imgResIds.length <= 6) {
			tempWidth = mScreenWidth * 2 / 3;
		} else {
			tempWidth = mScreenWidth;
		}
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, tempWidth);
		params.rightMargin = CommonUtil.dip2px(mContext, 10);
		params.leftMargin = CommonUtil.dip2px(mContext, 10);
		params.topMargin = CommonUtil.dip2px(mContext, 3);
		viewHolder.mImageRecyclerView.setLayoutParams(params);
		
		viewHolder.mImageRecyclerView.setAdapter(imageAdapter);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {
		Log.d("onCreateViewHolder",  "-------");
		if (viewType == TYPE_ITEM) {   
			View view = View.inflate(vg.getContext(), R.layout.forum_item, null);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			view.setLayoutParams(lp);

			return new FroumViewHolder(view, mForumList);
		} else if (viewType == TYPE_FOOTER){
			View view = View.inflate(vg.getContext(), R.layout.footer_load_more, null);
			
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			view.setLayoutParams(lp);
			return new FooterViewHolder(view);
		}
		
		return null;
	}

}
