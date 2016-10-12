package com.elife.classproject.baseview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.elife.classproject.R;

import java.util.List;

/**
 * Created by tzhang on 2016/9/19.
 */
public class RandomTextView extends View {

    private String mText;
    private int mTextColor;
    private int mTextSize;
    private Paint mPaint;
    private Rect mShowRect;

    private List<String> mListString;

    public RandomTextView(Context context) {
        this(context, null);
    }

    // 默认调用的构造器
    public RandomTextView(Context context, AttributeSet attrs) {


        this(context, attrs, 0);
        Log.e("RandomTextView", "RandomTextView--->2");
    }

    public RandomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.e("RandomTextView", "RandomTextView--->3");
        // 获取自定义属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RandomTextView, defStyleAttr, 0);
        int count = a.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.RandomTextView_text:
                    mText = a.getString(attr);
                    break;
                case R.styleable.RandomTextView_textColor:
                    // 获取不到采用默认颜色
                    mTextColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.RandomTextView_textSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

            }

        }
        a.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
//        mPaint.setColor(mTextColor);
        mShowRect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mShowRect);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListString && mListString.size() > 0) {
                    mText = mListString.get((int) (Math.random() * mListString.size()));
//                postInvalidate();
//                invalidate();
                    requestLayout();
                }

            }
        });
    }

    /**
     *
     *
     * 当控件的父元素正要放置该控件时调用.父元素会问子控件一个问题，“你想要用多大地方啊？”，然后传入两个参数——widthMeasureSpec和heightMeasureSpec.
     这两个参数指明控件可获得的空间以及关于这个空间描述的元数据.
     更好的方法是你传递View的高度和宽度到setMeasuredDimension方法里,这样可以直接告诉父控件，需要多大地方放置子控件


     * wrap_content
     * 系统帮我们测量的高度和宽度都是MATCH_PARNET，当我们设置明确的宽度和高度时，系统帮我们测量的结果就是我们设置的结果，当我们设置为WRAP_CONTENT,或者MATCH_PARENT系统帮我们测量的结果就是MATCH_PARENT的长度
     * 当设置了WRAP_CONTENT时，我们需要自己进行测量，即重写onMesure方法”：
     * 重写之前先了解MeasureSpec的specMode,一共三种类型：
     * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     * AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     * UNSPECIFIED：表示子布局想要多大就多大，很少使用
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("RandomTextView", "onMeasure");

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);// 内容，明确的宽度，EXACTLY使用

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int width;// 需要分配宽度
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;// 明确的宽度
        } else {
            mPaint.setTextSize(mTextSize);// 设置字体大小
            mPaint.getTextBounds(mText, 0, mText.length(), mShowRect);// 获取矩形，矩形中装载文本信息
            float textWidth = mShowRect.width();// 文本内容的长度
            //期望的长度（待分配大小）
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mShowRect);
            float textHeight = mShowRect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("RandomTextView", "onDraw" + getWidth() + ", " + mShowRect.width());
        mPaint.setColor(Color.GREEN);

        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTextColor);

        int lines = mShowRect.width()/300;
        int x = mText.length()/lines;
        canvas.drawText(mText, getWidth() / 2 - mShowRect.width() / 2, getHeight() / 2 + mShowRect.height() / 2, mPaint);// 居中绘制
    }


    public void setListData(List<String> strList) {
        Log.e("RandomTextView", "setListData");
        mListString = strList;
    }
}
