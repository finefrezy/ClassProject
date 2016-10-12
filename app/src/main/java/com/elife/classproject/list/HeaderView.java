package com.elife.classproject.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elife.classproject.R;

public class HeaderView extends LinearLayout {

	private View mView;
	private LinearLayout mContainer;
	private LinearLayout mContainerMore;
	
	private LinearLayout mHeadTextLinear;
	
	private ImageView mArrowImageView;
	private ProgressBar mProgressBar;
	private TextView mHintTextView;

	private Animation mRotateUpAnim;
	private Animation mRotateDownAnim;
	private final int ROTATE_ANIM_DURATION = 180;

	private int mState = STATE_NORMAL;
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_REFRESHING = 2;

	public HeaderView(Context context) {
		super(context);
		initView(context);
	}

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context ctx) {
		// 初始情况，设置下拉刷新view高度为0
		LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, 0);

		
		mView =  LayoutInflater.from(ctx).inflate(
				R.layout.header_refresh, null);
		mContainer = (LinearLayout) mView.findViewById(R.id.head_container);

		mContainer.setLayoutParams(lp);
		
//		RelativeLayout.LayoutParams lpr = new RelativeLayout.LayoutParams(
//				LayoutParams.WRAP_CONTENT, 60);
//		lpr.addRule(Gravity.CENTER_HORIZONTAL);
//		mHeadTextLinear = (LinearLayout) mView.findViewById(R.id.header_text);
//		mHeadTextLinear.setLayoutParams(lpr);
		
		mContainerMore = (LinearLayout) mView.findViewById(R.id.head_more);

		addView(mView);
		setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);

		mArrowImageView = (ImageView) findViewById(R.id.header_arrow);
		mHintTextView = (TextView) findViewById(R.id.header_hint_textview);
		mProgressBar = (ProgressBar) findViewById(R.id.header_progressbar);

		 mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
		 Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
		 0.5f);
		 mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		 mRotateUpAnim.setFillAfter(true);

		/**
		 * float fromDegrees：旋转的开始角度。 float toDegrees：旋转的结束角度。 int
		 * pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
		 * float pivotXValue：X坐标的伸缩值。 int
		 * pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
		 * float pivotYValue：Y坐标的伸缩值
		 */
		// 中心点旋转
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		// ture表示动画结束后停留在动画的最后位置，false表示动画结束后回到初始位置，默认为false
		mRotateDownAnim.setFillAfter(true);

	}

	/**
	 * 
	 * @param state
	 *            当前的处于状态
	 */
	public void setState(int state) {
		// 保证一个状态只执行一次
		if (state == mState)
			return;

		if (state == STATE_REFRESHING) { // 显示进度
			mArrowImageView.clearAnimation();
			mArrowImageView.setVisibility(View.INVISIBLE);
			mProgressBar.setVisibility(View.VISIBLE);
		} else { // 显示箭头图片
			mArrowImageView.setVisibility(View.VISIBLE);
			mProgressBar.setVisibility(View.INVISIBLE);
		}

		switch (state) {
		case STATE_NORMAL:
			// 手没有离开屏幕，状态由ready状态变回normal状态，并没有执行过刷新
			if (mState == STATE_READY && null != mRotateDownAnim) {
				mArrowImageView.startAnimation(mRotateDownAnim);
			}
			// 手已离开屏幕，状态由refreshing状态变回normal状态（向上滑动是header隐藏）
			if (mState == STATE_REFRESHING) {
				mArrowImageView.clearAnimation();
			}
			mHintTextView.setText(R.string.header_hint_normal);
			break;
		case STATE_READY:
			// 再次保证一个状态只执行一次
			if (mState != STATE_READY && null != mRotateUpAnim) {
				mArrowImageView.clearAnimation();
				mArrowImageView.startAnimation(mRotateUpAnim);
				mHintTextView.setText(R.string.header_hint_ready);
			}
			break;
		case STATE_REFRESHING:
			mHintTextView.setText(R.string.header_hint_loading);
			break;
		default:
		}

		mState = state;
	}

	/**
	 * 
	 * @param height
	 *            设置可见高度
	 */
	public void setVisiableHeight(int height) {
		if (height < 0)
			height = 0;
		LayoutParams lp = (LayoutParams) mContainer
				.getLayoutParams();
		lp.height = height;
		mContainer.setLayoutParams(lp);
	}

	/**
	 * 获取可见的高度
	 * 
	 * @return
	 */

	public int getVisiableHeight() {
		return mContainer.getLayoutParams().height;
	}

	
	public void addMoreView(View view) {
		mContainerMore.addView(view);
	}
}
