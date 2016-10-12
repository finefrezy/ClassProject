package com.elife.classproject.palette;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.elife.classproject.R;


public class PaletteActivity extends Activity {
	/**
	 * Palette从图像中提取突出的颜色，这样可以把色值赋给ActionBar、或者其他，可以让界面整个色调统一。
	 * 
	 * Palette这个类中提取以下突出的颜色：
	 * 
	 * Vibrant （有活力）
	 * 
	 * Vibrant dark（有活力 暗色）
	 * 
	 * Vibrant light（有活力 亮色）
	 * 
	 * Muted （柔和）
	 * 
	 * Muted dark（柔和 暗色）
	 * 
	 * Muted light（柔和 亮色）
	 * 
	 * // These should be used when you have access to the underlying image
	 * loading thread. // Picasso allows this through a Transformation. For
	 * other libraries, YMMV. // Uses the default palette size (16). Palette p =
	 * Palette.generate(bitmap); // Allows you to specify the maximum palette
	 * size, in this case 24. Palette p = Palette.generate(bitmap, 24); //
	 * Asynchronous methods // -------------------------------- // This is the
	 * quick and easy integration path. Internally uses an AsyncTask so // this
	 * may not be optimal (since you're dipping in and out of threads) // Uses
	 * the default palette size (16). Palette.generateAsync(bitmap, new
	 * Palette.PaletteAsyncListener() {
	 * 
	 * @Override public void onGenerated(Palette palette) { // Here's your
	 *           generated palette } }); // Allows you to specify the maximum
	 *           palette size, in this case 24. Palette.generateAsync(bitmap,
	 *           24, new Palette.PaletteAsyncListener() {
	 * @Override public void onGenerated(Palette palette) { // Here's your
	 *           generated palette } });
	 * 
	 *           你可能注意到了可以设置palette的size。size越大，花费的时间越长，而越小，可以选择的色彩也越小。
	 *           最佳的选择是根据image的用途：
	 * 
	 *           头像之类的，size最好在24-32之间；
	 * 
	 *           风景大图之类的 size差不多在8-16
	 */

	TextView mText1;
	TextView mText2;
	ImageView mImageView;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_palette);
		mText1 = (TextView) findViewById(R.id.text1);
		mText2 = (TextView) findViewById(R.id.text2);
		mImageView = (ImageView) findViewById(R.id.image_view);

		Bitmap bm = BitmapFactory.decodeResource(getResources(),
				R.drawable.banner_aisi);

//		new Palette.Builder(bm).generate(new Palette.PaletteAsyncListener(){
//
//			@Override
//			public void onGenerated(Palette palette) {
//
//			}
//		});


		// Palette的部分
		Palette.generateAsync(bm, new Palette.PaletteAsyncListener() {
			/**
			 * 提取完之后的回调方法
			 */
			@Override
			public void onGenerated(Palette palette) {
				/**
				 * 采集的样本（swatch）
				 * Vibrant.
				 * Palette.getVibrantSwatch() Vibrant （有活力）
				 * dark. Palette.getDarkVibrantSwatch() （有活力 暗色）
				 * Vibrant light.Palette.getLightVibrantSwatch()（有活力 亮色）
				 * Muted.
				 * Palette.getMutedSwatch() （柔和）
				 * Muted dark.
				 * Palette.getDarkMutedSwatch() （柔和 暗色）
				 * Muted light.
				 * Palette.getLightMutedSwatch()（柔和 亮色）
				 *
				 *
				 * getPopulation(): the amount of pixels which this swatch
				 * represents. getRgb(): the RGB value of this color. getHsl():
				 * the HSL value of this color. getBodyTextColor(): the RGB
				 * value of a text color which can be displayed on top of this
				 * color. getTitleTextColor(): the RGB value of a text color
				 * which can be displayed on top of this color.
				 */
				// 返回可能是空，所以在使用vibrant时必须判断,如下所示
				Palette.Swatch vibrant = palette.getDarkMutedSwatch();
				/* 界面颜色UI统一性处理,看起来更Material一些 */

				// 先判空，再使用
				if (null != vibrant) {
					mText1.setBackgroundColor(vibrant.getRgb());
					mText1.setTextColor(vibrant.getTitleTextColor());

					mText2.setBackgroundColor(vibrant.getRgb());
					mText2.setTextColor(vibrant.getBodyTextColor());
				}



			}
		});
	}

}
