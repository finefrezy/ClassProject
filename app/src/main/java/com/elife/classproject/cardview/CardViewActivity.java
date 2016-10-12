package com.elife.classproject.cardview;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import com.elife.classproject.R;

public class CardViewActivity extends Activity {

	/**
	 * CardView继承至FrameLayout类，可以在一个卡片布局中一致性的显示内容，卡片可以包含圆角和阴影。CardView是一个Layout，
	 * 可以布局其他View
	 *
	 * CardView常用属性：
	 * card_view:cardElevation 阴影的大小 card_view:cardMaxElevation 阴影最大高度
	 * card_view:cardBackgroundColor 卡片的背景色 card_view:cardCornerRadius 卡片的圆角大小
	 * card_view:contentPadding 卡片内容于边距的间隔 card_view:contentPaddingBottom
	 * card_view:contentPaddingTop card_view:contentPaddingLeft
	 * card_view:contentPaddingRight card_view:contentPaddingStart
	 * card_view:contentPaddingEnd card_view:cardUseCompatPadding
	 * 设置内边距，V21+的版本和之前的版本仍旧具有一样的计算方式 card_view:cardPreventConrerOverlap
	 * 在V20和之前的版本中添加内边距，这个属性为了防止内容和边角的重叠
	 */

	private CardView mCardView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_view);

		mCardView = (CardView) findViewById(R.id.card_view);
		mCardView.setBackgroundColor(Color.CYAN);

	}


}
