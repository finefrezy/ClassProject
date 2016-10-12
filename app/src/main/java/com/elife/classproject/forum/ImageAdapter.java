package com.elife.classproject.forum;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;

import com.elife.classproject.R;
import com.elife.classproject.forum.cornerrect.ImageCropUtil;
import com.elife.classproject.forum.holder.ImageViewHolder;


public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

	private int[] mCyclerImages;
	private Context mContext;
	private int mScreenWidth;

	public ImageAdapter(int[] images, Context ctx, int width) {
		mCyclerImages = images;
		mContext = ctx;
		mScreenWidth = width - 15;
	}

	@Override
	public int getItemCount() {
		if (null == mCyclerImages) {
			return 0;
		}


		return mCyclerImages.length;
	}

	@Override
	public void onBindViewHolder(ImageViewHolder ivh, int position) {

		ivh.mPosition = position;
		Bitmap bitmap = ImageCropUtil.decodeResource(mContext,
				mCyclerImages[position], "", mScreenWidth / 3,
				mScreenWidth / 3, ScaleType.FIT_XY);
		ivh.mImageView.setImageBitmap(bitmap);
	}

	@Override
	public ImageViewHolder onCreateViewHolder(ViewGroup vg, int position) {


		View view = View.inflate(vg.getContext(), R.layout.image_item, null);

		return new ImageViewHolder(view, mCyclerImages, mContext, mScreenWidth);
	}
}
