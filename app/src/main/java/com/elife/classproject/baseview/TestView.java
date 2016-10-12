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
 * Created by tzhang on 2016/9/20.
 */
public class TestView extends View {


    private int mTextSize;
    private String mText;
    private int mTextColor;
    private Paint mPaint;
    private int mBackgroundColor;

    private Rect mShowRect;
    private List<String> mListString;

    public TestView(Context context) {
        this(context, null);
    }

    // 布局文件中声明默认调用的构造器
    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 获取自定义属性的值，如果没有给自定义的属性赋值
    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RandomTextView, defStyleAttr, 0);

        int count = a.getIndexCount();


        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.RandomTextView_text:
                    mText = a.getString(attr);
                    break;
                case R.styleable.RandomTextView_textColor:
                    mTextColor = a.getInt(attr, Color.BLACK);
                    break;
                case R.styleable.RandomTextView_backgroundColor:
                    mBackgroundColor = a.getInt(attr, Color.BLACK);
                    break;
                case R.styleable.RandomTextView_textSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;

            }



        }
// 用完一定回收，防止多次使用出现数值的混乱
        a.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);

        mShowRect = new Rect();
        
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListString && mListString.size() > 0) {
                    mText = mListString.get((int) (Math.random() * mListString.size()));
                    // Android提供了Invalidate方法实现界面刷新，但是Invalidate不能直接在线程中调用，因为他是违背了单线程模型：Android UI操作并不是线程安全的，并且这些操作必须在UI线程中调用。
                    // postInvalidate 可以直接在子线程中调用
//                    postInvalidate();// 调用这个方法就会接着调用onDraw方法
//                    invalidate();// 调用这个方法就会接着调用onDraw方法
                    requestLayout();// onMeasure然后调用onDraw方法
                }
            }
        });
    }

// 控件显示大小的计算，即显示view的宽度与高度的计算，计算好之后，通过set方法设置使用
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("TestView", "onMeasure");

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTextSize);
            //  0, mText.length() 装载字符的开始位置到结束位置
            mPaint.getTextBounds(mText, 0, mText.length(), mShowRect);
            width = mShowRect.width() + getPaddingLeft() + getPaddingRight();
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTextSize);
            //  0, mText.length() 装载字符的开始位置到结束位置
            mPaint.getTextBounds(mText, 0, mText.length(), mShowRect);

            height = mShowRect.height() + getPaddingTop() + getPaddingBottom();


        }

        setMeasuredDimension(width, height);

    }

    // 界面显示内容的绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("TestView", "onDraw");
        canvas.drawColor(mBackgroundColor);
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTextColor);

        Log.e("onDraw", "onDraw" + getWidth() + "," + mShowRect.width());
        canvas.drawText(mText, getWidth() / 2 - mShowRect.width() / 2, getHeight() / 2 + mShowRect.height() / 2, mPaint);// 居中绘制

    }

    public void setListData(List<String> strList) {
        Log.e("TestView", "setListData");
        mListString = strList;
    }
}
