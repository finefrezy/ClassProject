package com.elife.classproject.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by tzhang on 2016/9/19.
 * <p/>
 * paint 属性设置简单总结
 * 图形绘制相关：
 * public void set(Paint src)  根据已有画笔的属性进行赋值
 * public void setColor(int color) 设置颜色
 * public void setAlpha(int alpha) 设置透明度，alpha为透明度,取值范围为0~255,数值越小越透明
 * public void setARGB(int a, int r, int g, int b)  设置透明度和颜色，a代表透明度，r，g，b代表颜色值
 * public void setAntiAlias(boolean aa) 设置是否使用抗锯齿功能，比较耗资源，减慢绘制速度
 * public void setDither(boolean dither) 设定是否使用图像抖动，如true，绘制出来的图片颜色更饱满、清晰
 * public void setStyle(android.graphics._Original_Paint.Style style) 设置画笔的样式，为FILL，FILL_AND_STROKE，或STROKE
 * <p/>
 * <p/>
 * 调用drawCircle、drawOval、drawArc、drawRect等方法时，我们既可以绘制对应图形的填充面，也可以只绘制该图形的轮廓线，控制的关键在于画笔Paint中的style。Paint通过setStyle方法设置要绘制的类型，style有取三种值：Paint.Style.FILL、Paint.Style.STROKE和Paint.Style.FILL_AND_STROKE。
 * 当style为FILL时，绘制是填充面，FILL是Paint默认的style；
 * 当style为STROKE时，绘制的是图形的轮廓线；
 * 当style为FILL_AND_STROKE时，同时绘制填充面和轮廓线，不过这种情况用的不多，因为填充面和轮廓线是用同一种颜色绘制的，区分不出轮廓线的效果。
 * public void setStrokeCap(Cap cap)
 * <p/>
 * 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式  Cap.ROUND,或方形样式Cap.SQUARE
 * public void setStrokeWidth(float width) 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度
 * <p/>
 * <p/>
 * 文字绘制相关：
 * public void setTextSize(float textSize) 设置文字大小
 * public void setTextScaleX(float scaleX) 设置文字x轴的缩放比例，可以实现文字的拉伸效果
 * public void setTextSkewX(float skewX)  设置文字倾斜弧度
 * public void setUnderlineText(boolean flag) 设置文字下划线效果
 * public void setStrikeThruText(boolean flag) 设置删除线效果
 * public Typeface setTypeface(Typeface typeface) 设置字体风格
 * public void setTextAlign(android.graphics._Original_Paint.Align align) 设置文字的对齐方向
 * 其中有两个属性设置需要作说明：
 * 1、public Typeface setTypeface(Typeface typeface) ，接收参数为 Typeface对象，在Typeface.java类中，比较简单的，有defaultFromStyle方法返回Typeface对象：
 * public static Typeface defaultFromStyle(int style) {}
 * 2、public void setTextAlign(android.graphics._Original_Paint.Align align) 设置文字的对齐方向，接收的参数为Paint的内部枚举类Align的值，可选LEFT、CENTER和RIGHT。
 */
public class CanvasView extends View {

    Paint paint;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.GREEN);
        paint.setStrokeJoin(Paint.Join.ROUND);// 连接点
        paint.setStrokeCap(Paint.Cap.ROUND);// 笔头圆润
        paint.setStrokeWidth(3);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     *
     * View 的onDraw方法注释
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("------------", "onDraw");
//        canvas.drawColor(Color.RED);
        canvas.drawCircle(100, 100, 90, paint);

        //绘制弧线区域

        RectF rect = new RectF(200, 0, 300, 100);
        paint.setColor(Color.RED);
//        canvas.drawRect(rect, paint);

        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                0,  //开始角度
                -90, //扫过的角度
                false, //是否是扇形
                paint);

        canvas.drawColor(Color.WHITE);
        // 一条线，起始点10,10终点 100,100
        canvas.drawLine(10, 10, 100, 100, paint);

//        //定义一个矩形区域
        RectF oval = new RectF(0,110,300,500);
//        //矩形区域内切椭圆
        canvas.drawOval(oval, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(25f);
        //按照既定点 绘制文本内容
        canvas.drawPosText("相见时难别亦难", new float[]{
                10,10, //第一个字母在坐标10,10
                40,40, //第二个字母在坐标20,20
                80,80, //....
                100,150,
                150,200,
                200,360,
                360,400

        }, paint);
//
//        RectF rect1 = new RectF(50, 50, 200, 200);
//
//        canvas.drawRect(rect1, paint);
//
//
//
        RectF rect2 = new RectF(300, 20, 400, 200);

        canvas.drawRoundRect(rect2,
                10, //x轴的半径
                50, //y轴的半径
                paint);

        canvas.drawColor(Color.BLUE);

        canvas.translate(100, 100);

        RectF rect1 = new RectF(0, 0, 200, 200);
        canvas.drawRect(rect1, paint);
//
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path(); //定义一条路径
        path.moveTo(10, 10); //移动到 坐标10,10
        path.lineTo(50, 60);
        path.lineTo(200,80);
        path.lineTo(10, 10);
//
        canvas.drawPath(path, paint);
//
        canvas.translate(0, 200);
        Path path1 = new Path(); //定义一条路径
        path1.moveTo(20, 20); //移动到 坐标10,10
        path1.lineTo(300, 60);
        path1.lineTo(200,340);
        path1.lineTo(20, 20);
//        canvas.drawPath(path1, paint);
        canvas.drawTextOnPath("春蚕到死丝方尽，蜡炬成灰泪始干。", path1, 10, 10, paint);

    }
}
