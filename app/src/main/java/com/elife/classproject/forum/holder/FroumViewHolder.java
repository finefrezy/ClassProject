package com.elife.classproject.forum.holder;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elife.classproject.R;
import com.elife.classproject.forum.FroumModel;
import com.elife.classproject.forum.divider.SpacingGridItemDecoration;

import java.util.List;


public class FroumViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
	public int mPosition;
	public Context mContext;
	
	public TextView mNameText;
	public TextView mTimeText;
	public TextView mMesgText;
	public ImageView mImageIcon;
	public ImageView mGoodImage;
	public ImageView mCommentImage;
	public ImageView mForwardImage;
	public CardView mCardForumView;
	public RecyclerView mImageRecyclerView;
	public TextView mLikeCountText;
	
	
	LinearLayout mForwardLinear;
	LinearLayout mLikeLinear;
	LinearLayout mCommentLinear;
	
	List<FroumModel> mForumList;
	public FroumViewHolder(View view, List<FroumModel> forumList) {
		super(view);
		mForumList = forumList;
		
		mNameText = (TextView) view.findViewById(R.id.user_name);
		mTimeText = (TextView) view.findViewById(R.id.comment_time);
		mMesgText = (TextView) view.findViewById(R.id.comment_content);
		mLikeCountText = (TextView) view.findViewById(R.id.like_count);
		mImageIcon = (ImageView) view.findViewById(R.id.image_icon);
		
		mForwardLinear = (LinearLayout) view.findViewById(R.id.status_forward);
		mCommentLinear = (LinearLayout) view.findViewById(R.id.status_comment);
		mLikeLinear = (LinearLayout) view.findViewById(R.id.status_like);
		
		mImageRecyclerView = (RecyclerView) view.findViewById(R.id.forum_image_list);
		
		mImageRecyclerView.setHasFixedSize(true);
		
		// confirm decoration invoking once to avoid spacing added again
		mImageRecyclerView.addItemDecoration(new SpacingGridItemDecoration(3, 6, false));
		mImageRecyclerView
				.setLayoutManager(new StaggeredGridLayoutManager(3,
						StaggeredGridLayoutManager.VERTICAL));
		
		mImageIcon.setOnClickListener(this);
		mMesgText.setOnClickListener(this);
		mForwardLinear.setOnClickListener(this);
		mCommentLinear.setOnClickListener(this);
		mLikeLinear.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.image_icon:
			Toast.makeText(mContext, "image_icon " + mPosition, Toast.LENGTH_LONG).show();
			break;
		case R.id.comment_content:
			Toast.makeText(mContext, "image_comment " + mPosition, Toast.LENGTH_LONG).show();
			break;
		case R.id.status_forward:
			Toast.makeText(mContext, "status_forward " + mPosition, Toast.LENGTH_LONG).show();
			break;
		case R.id.status_comment:
			Toast.makeText(mContext, "status_comment " + mPosition, Toast.LENGTH_LONG).show();
			break;
		case R.id.status_like:
			PropertyValuesHolder valuesHolderX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.2f, 1.3F);
			PropertyValuesHolder valuesHolderY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.2f, 1.3F);

	        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mLikeCountText, valuesHolderX, valuesHolderY);
	        objectAnimator.setDuration(500).setInterpolator(new AccelerateDecelerateInterpolator());
	        objectAnimator.setRepeatCount(1);
	        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
	        objectAnimator.start();
			
//			ObjectAnimator animator = ObjectAnimator.ofFloat(mLikeCountText, "scaleX", 1.0f, 1.5f);
//			ObjectAnimator animator1 = ObjectAnimator.ofFloat(mLikeCountText, "scaleY", 1.0f, 1.5f);
//	        animator.setDuration(2000).start();
			
			Toast.makeText(mContext, "status_like " + mPosition, Toast.LENGTH_LONG).show();
			mForumList.get(mPosition).likeCount = mForumList.get(mPosition).likeCount + 1;
			mLikeCountText.setText(mForumList.get(mPosition).likeCount + "");
			break;
		}
	}

}
