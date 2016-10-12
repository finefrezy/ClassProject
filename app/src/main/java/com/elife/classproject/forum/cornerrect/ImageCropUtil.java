package com.elife.classproject.forum.cornerrect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView.ScaleType;

public class ImageCropUtil {

	/**
	 * 圆角矩形工具
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;

			left = 0;
			top = 0;
			right = width;
			bottom = width;

			height = width;

			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;

			float clip = (width - height) / 2;

			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;

			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);// 设置画笔无锯齿
		canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas

		// 以下有两种方法画圆,drawRounRect和drawCircle
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
		// canvas.drawCircle(roundPx, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
		canvas.drawBitmap(bitmap, src, dst, paint); // 以Mode.SRC_IN模式合并bitmap和已经draw了的Circle
		return output;
	}

	/**
	 * 根据宽度获取图片
	 * @param context
	 * @param id
	 * @param filePath
	 * @param width
	 * @param type
	 * @return
	 */
	public static Bitmap descodeResourceByWidth(Context context, int id, String filePath, int width, ScaleType type) {
		BitmapFactory.Options decodeOptions = new BitmapFactory.Options();

		decodeOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), id, decodeOptions);
//		BitmapFactory.decodeFile(filePath, decodeOptions); 
		
		int actualWidth = decodeOptions.outWidth;
		int actualHeight = decodeOptions.outHeight;
		
		
 
		//  2400 * 1440
//		800 * 480 

		// Then compute the dimensions we would ideally like to decode to.
		int desiredWidth = width;
		int desiredHeight = getResizedHeightByWidth(width,
				actualWidth, actualHeight);

		// Decode to the nearest power of two scaling factor.
		decodeOptions.inJustDecodeBounds = false;
		// TODO(ficus): Do we need this or is it okay since API 8 doesn't
		// support it?
		// decodeOptions.inPreferQualityOverSpeed =
		// PREFER_QUALITY_OVER_SPEED;
		decodeOptions.inSampleSize = findBestSampleSize(actualWidth,
				actualHeight, desiredWidth, desiredHeight);
		Bitmap tempBitmap = BitmapFactory.decodeResource(
				context.getResources(), id, decodeOptions);
		Bitmap bitmap = null;
		// If necessary, scale down to the maximal acceptable size.
		if (tempBitmap != null
				&& (tempBitmap.getWidth() > desiredWidth || tempBitmap
						.getHeight() > desiredHeight)) {
			bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth, desiredHeight,
					true);
			
			tempBitmap.recycle();
		} else {
			bitmap = tempBitmap;
		}
		return bitmap;
	}
	
	
	/**
	 * The real guts of parseNetworkResponse. Broken out for readability.
	 */
	public static Bitmap decodeResource(Context context, int id, String filePath, int width, int height, ScaleType type) {
		BitmapFactory.Options decodeOptions = new BitmapFactory.Options();

		decodeOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), id, decodeOptions);
//		BitmapFactory.decodeFile(filePath, decodeOptions); 
		
		int actualWidth = decodeOptions.outWidth; 
		int actualHeight = decodeOptions.outHeight;

		// Then compute the dimensions we would ideally like to decode to.
		int desiredWidth = getResizedDimension(width, height, actualWidth,
				actualHeight, type);
		int desiredHeight = getResizedDimension(width, height, actualHeight,
				actualWidth, type); 

		// Decode to the nearest power of two scaling factor.
		decodeOptions.inJustDecodeBounds = false;
		// TODO(ficus): Do we need this or is it okay since API 8 doesn't
		// support it?
		// decodeOptions.inPreferQualityOverSpeed =
		// PREFER_QUALITY_OVER_SPEED;
		decodeOptions.inSampleSize = findBestSampleSize(actualWidth,
				actualHeight, desiredWidth, desiredHeight);// 10
		Bitmap tempBitmap = BitmapFactory.decodeResource(
				context.getResources(), id, decodeOptions);
		Bitmap bitmap = null;
		// If necessary, scale down to the maximal acceptable size.
		if (tempBitmap != null
				&& (tempBitmap.getWidth() > desiredWidth || tempBitmap
						.getHeight() > desiredHeight)) {
			bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth, desiredHeight,
					true);
			
			tempBitmap.recycle();
		} else {
			bitmap = tempBitmap;
		}
		return bitmap;
	}

	/**
	 * Returns the largest power-of-two divisor for use in downscaling a bitmap
	 * that will not result in the scaling past the desired dimensions.
	 * 
	 * @param actualWidth
	 *            Actual width of the bitmap
	 * @param actualHeight
	 *            Actual height of the bitmap
	 * @param desiredWidth
	 *            Desired width of the bitmap
	 * @param desiredHeight
	 *            Desired height of the bitmap
	 */
	// Visible for testing.
	static int findBestSampleSize(int actualWidth, int actualHeight,
			int desiredWidth, int desiredHeight) {
		double wr = (double) actualWidth / desiredWidth;
		double hr = (double) actualHeight / desiredHeight;
		double ratio = Math.min(wr, hr);
		float n = 1.0f;
		while ((n * 2) <= ratio) {
			n *= 2;
		}

		return (int) n;
	}

	private static int getResizedHeightByWidth(int width, int actualWidth, int actualHeight) {
		double ratio = (double) actualWidth / (double) width;
		return (int) (actualHeight/ratio);
	}
	
	
	/**
	 * Scales one side of a rectangle to fit aspect ratio.
	 * 
	 * @param maxPrimary
	 *            Maximum size of the primary dimension (i.e. width for max
	 *            width), or zero to maintain aspect ratio with secondary
	 *            dimension
	 * @param maxSecondary
	 *            Maximum size of the secondary dimension, or zero to maintain
	 *            aspect ratio with primary dimension
	 * @param actualPrimary
	 *            Actual size of the primary dimension
	 * @param actualSecondary
	 *            Actual size of the secondary dimension
	 * @param scaleType
	 *            The ScaleType used to calculate the needed image size.
	 */
	private static int getResizedDimension(int maxPrimary, int maxSecondary,
			int actualPrimary, int actualSecondary, ScaleType scaleType) {

		// If no dominant value at all, just return the actual.
		if ((maxPrimary == 0) && (maxSecondary == 0)) {
			return actualPrimary;
		}

		// If ScaleType.FIT_XY fill the whole rectangle, ignore ratio.
		if (scaleType == ScaleType.FIT_XY) {
			if (maxPrimary == 0) {
				return actualPrimary;
			}
			return maxPrimary;
		}

		// If primary is unspecified, scale primary to match secondary's scaling
		// ratio.
		if (maxPrimary == 0) {
			double ratio = (double) maxSecondary / (double) actualSecondary;
			return (int) (actualPrimary * ratio);
		}

		if (maxSecondary == 0) {
			return maxPrimary;
		}

		double ratio = (double) actualSecondary / (double) actualPrimary;
		int resized = maxPrimary;

		// If ScaleType.CENTER_CROP fill the whole rectangle, preserve aspect
		// ratio.
		if (scaleType == ScaleType.CENTER_CROP) {
			if ((resized * ratio) < maxSecondary) {
				resized = (int) (maxSecondary / ratio);
			}
			return resized;
		}

		if ((resized * ratio) > maxSecondary) {
			resized = (int) (maxSecondary / ratio);
		}
		return resized;
	}

}
