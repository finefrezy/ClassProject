package com.elife.classproject.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Bitmap.Config.ARGB_8888
 * A：透明度 Alpha
 * R：红色 Red
 * G：绿 Green
 * B：蓝 Blue
 * Bitmap.Config ARGB_4444：每个像素占四位，即A=4，R=4，G=4，B=4，那么一个像素点占4+4+4+4=16位
 * Bitmap.Config ARGB_8888：每个像素占八位，即A=8，R=8，G=8，B=8，那么一个像素点占8+8+8+8=32位
 * Bitmap.Config RGB_565：即R=5，G=6，B=5，没有透明度，那么一个像素点占5+6+5=16位
 * Bitmap.Config ALPHA_8：只有透明度，没有颜色。
 * 一般情况下我们都是使用的ARGB_8888，由此可知它是最占内存的，因为一个像素占32位，8位=1字节，所以一个像素占4字节的内存。
 * 假设有一张480x800的图片，如果格式为ARGB_8888，那么将会占用1500KB的内存
 * <p/>
 * ARGB_4444：内存占用减少一半，但是每个值图片失真度很严重，这个参数本身已经不推荐使用了。
 * RGB_565：内存占用减少一半，舍弃了透明度，同时三色值也有部分损失，但是图片失真度很小。
 * ARGB_4444不推荐使用。我们大多数是用的还是ARGB_8888和RGB_565。
 * RGB_565能够在保证图片质量的情况下大大减少内存的开销，是解决oom（out of memory内存溢出）的一种方法。但是一定要注意RGB_565
 * 是没有透明度的，如果图片本身需要保留透明度，那么就不能使用RGB_565
 */
public class CanvasActivity extends AppCompatActivity {

    private ImageView mImageCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_canvas);
//        mImageCanvas = (ImageView) findViewById(R.id.image_canvas);

        // 创建一个bitmap，200 * 200
//        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
//        // 将bitmap作为Canvas的画布（画布）
//        Canvas canvas = new Canvas(bitmap);
//        // 绘制背景
//        canvas.drawColor(Color.BLUE);
//        // 画笔
//        Paint paint = new Paint();
//        paint.setColor(Color.YELLOW);
//        paint.setStrokeJoin(Paint.Join.ROUND);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStrokeWidth(3);
//        // 使用paint画一个圆，圆心（100,100），半径90
//        canvas.drawCircle(100, 100, 90, paint);
//        mImageCanvas.setImageBitmap(bitmap);

        setContentView(new CanvasView(this));
//        setContentView(new CanvasClock(this));


    }
}
