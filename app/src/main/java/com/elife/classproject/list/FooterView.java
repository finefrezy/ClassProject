package com.elife.classproject.list;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elife.classproject.R;

public class FooterView extends LinearLayout {

	private View mContentView;
	private View mProgressBar;
	
//	private View mImageView;
	private TextView mHintView;
	private Context mContext;

	
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_LOADING = 2;
	
	public FooterView(Context context) {
		super(context);
		initView(context);
	}

	public FooterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		mContext = context;
		LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext)
				.inflate(R.layout.footer_load, null);
		addView(moreView);
		moreView.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		mContentView = moreView.findViewById(R.id.footer_content);
//		mImageView = moreView.findViewById(R.id.footer_image);
		mProgressBar = moreView.findViewById(R.id.footer_progressbar);
		mHintView = (TextView) moreView.findViewById(R.id.footer_hint_textview);
	}

	/**
	 * hide footer when disable pull load more
	 */
	public void hide() {
		LayoutParams lp = (LayoutParams) mContentView
				.getLayoutParams();
		lp.height = 0;
		mContentView.setLayoutParams(lp);
	}

	/**
	 * show footer about height
	 */
	public void show() {
		LayoutParams lp = (LayoutParams) mContentView
				.getLayoutParams();
		lp.height = LayoutParams.WRAP_CONTENT;
		mContentView.setLayoutParams(lp);
	}
	
	/**
	 * loading status with showing view
	 */
	public void loading() {
		mHintView.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.VISIBLE);
//		mImageView.setVisibility(View.VISIBLE);
	}
	/**
	 * normal status with showing view
	 */
	public void normal() {
		mHintView.setVisibility(View.VISIBLE);
//		mImageView.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.GONE);
	}
	/**
	 * set margin bottom value when pull up
	 * @param height
	 */
	public void setBottomMargin(int height) {
		if (height < 0) return ;
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.bottomMargin = height;
		mContentView.setLayoutParams(lp);
	}
	
	/**
	 * @return
	 */
	public int getBottomMargin() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		return lp.bottomMargin;
	}
	
	public void setState(int state) {
		mHintView.setVisibility(View.INVISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
//		mImageView.setVisibility(View.INVISIBLE);
		mHintView.setVisibility(View.INVISIBLE);
		if (state == STATE_READY) {
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.footer_hint_ready);
		} else if (state == STATE_LOADING) {
			mProgressBar.setVisibility(View.VISIBLE);
//			mImageView.setVisibility(View.VISIBLE);
		} else {
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.footer_hint_normal);
		}
	}

}
