package com.elife.classproject.forum.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.elife.classproject.R;
import com.elife.classproject.forum.cornerrect.ImageCropUtil;


public class ImageViewHolder extends RecyclerView.ViewHolder implements
		OnClickListener {

	public ImageView mImageView;
	public int mPosition;
	int[] mCyclerImages;
	Context mContext;
	private int mScreenWidth;
	PopupWindow mPopupWindow;

	public ImageViewHolder(View view, int[] cyclerImages, Context context,
			int screenWidth) {
		super(view);
		this.mCyclerImages = cyclerImages;
		this.mContext = context;
		mImageView = (ImageView) view.findViewById(R.id.image_item);
		mImageView.setOnClickListener(this);
		mScreenWidth = screenWidth;
	}

	@Override
	public void onClick(View v) {
		// Toast.makeText(mContext, "dd", Toast.LENGTH_LONG).show();
		View view = View.inflate(mContext, R.layout.forum_image, null);
		ViewPager viewpager = (ViewPager) view
				.findViewById(R.id.image_view_pager);
		final TextView textView = (TextView) view
				.findViewById(R.id.count_change);
		textView.setText((mPosition + 1) + "/" + mCyclerImages.length);

		viewpager.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				textView.setText((position + 1) + "/" + mCyclerImages.length);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int position) {

			}
		});
		ForumImagePager forumImagePager = new ForumImagePager();
		viewpager.setAdapter(forumImagePager);
		viewpager.setCurrentItem(mPosition);

		mPopupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, false);
		mPopupWindow.setOutsideTouchable(false);
		mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
	}

	class ForumImagePager extends PagerAdapter {

		@Override
		public int getCount() {

			return mCyclerImages.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((ImageView) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = new ImageView(mContext);
			Bitmap bitmap = ImageCropUtil.descodeResourceByWidth(mContext,
					mCyclerImages[position], "", mScreenWidth,
					ScaleType.FIT_CENTER);
			imageView.setImageBitmap(bitmap);

			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mPopupWindow.dismiss();
				}
			});

			container.addView(imageView);

			return imageView;
		}

	}

}
