package com.elife.classproject.circlemenu;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.elife.classproject.R;

/**
 * Created by tzhang on 2016/10/19.
 */
public class BetterCircleMenuLayout extends ViewGroup {

    /**
     * 该容器内child item的默认尺寸
     */
    private static final float RADIO_DEFAULT_CHILD_DIMENSION = 1 / 4f;
    /**
     * 该容器的内边距，无视padding属性，如需边距请用该变量
     */
    private static final float RADIO_PADDING_LAYOUT = 1 / 12F;
    /**
     * 圆形直径
     */
    private int mRadius;
    /**
     * 该容器的内边距，无视padding属性，如需边距请用该变量
     */
    private float mPadding;
    /**
     * 布局时开始角度
     */
    private double mStartAngle = 0;
    /**
     * 菜单项的文本
     */
    private String[] mItemTexts;
    /**
     * 菜单项的图标
     */
    private int[] mItemImgs;
    /**
     * 菜单的个数
     */
    private int mMenuItemCount;
    /**
     * 菜单布局资源id
     */
    private int mMenuItemLayoutId = R.layout.circle_menu_item;


    private ListAdapter mListAdapter;

    public void setAdapter(ListAdapter adapter) {
        this.mListAdapter = adapter;
    }

    /**
     * MenuItem的点击事件接口
     */
    private OnItemClickListener mOnItemClickListener;

    public BetterCircleMenuLayout(Context context) {
        super(context);

    }

    public BetterCircleMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 无视padding
        setPadding(0, 0, 0, 0);
    }

    public BetterCircleMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//    public void setMenuItemIconsAndTexts(int[] images, String[] texts) {
//        if (images == null && texts == null) {
//            throw new IllegalStateException("无法填充数据");
//        }
//
//        mItemImgs = images;
//        mItemTexts = texts;
//
//        // 初始化MenuCount
//        mMenuItemCount = (images == null) ? texts.length : images.length;
//
//        if (images != null && texts != null) {
//            mMenuItemCount = Math.min(images.length, texts.length);
//        }
//        // 构建菜单项
//        bindMenuItems();
//    }

    private void bindMenuItems() {

        for (int i = 0; i < mListAdapter.getCount(); i++) {
            View itemView = mListAdapter.getView(i, null, this);
            final int position = i;
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != mOnItemClickListener) {
                        mOnItemClickListener.onItemClick(view, position);
                    }
                }
            });
            addView(itemView);
        }

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e("BetterCircleMenuLayout", "onMeasure");

        if (mListAdapter != null) {
            bindMenuItems();
        }
    }
    //
//    private void initMenuItem(View itemView, int i) {
//        ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
//        TextView textView = (TextView) itemView.findViewById(R.id.text);
//        imageView.setVisibility(View.VISIBLE);
//        imageView.setImageResource(mItemImgs[i]);
//
//        textView.setVisibility(View.VISIBLE);
//        textView.setText(mItemTexts[i]);
//
//    }

    /**
     * menuitem的布局，一定要在setMenuItemIconsAndTexts方法前调用
     *
     * @param menuLayoutId
     */
    public void setOnMenuItemLayoutId(int menuLayoutId) {
        this.mMenuItemLayoutId = menuLayoutId;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.e("BetterCircleMenuLayout", "onMeasure");

        measureSelf(widthMeasureSpec, heightMeasureSpec);
        measureChildView();
    }

    /**
     * 丈量自身尺寸
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    private void measureSelf(int widthMeasureSpec, int heightMeasureSpec) {

        int resWidth = 0;
        int resHeight = 0;

        // 测量模式与测量值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            // 主要设置为背景图的高度
            // view的基本测量数据默认取其背景尺寸，除非允许更大的尺寸。
            // 子view必须重写onMeasure(int, int)来提供其内容更加准确的测量数值。
            // 如果被重写，子类确保测量的height和width至少是view的最小高度和宽度(通过getSuggestedMinimumHeight()和getSuggestedMinimumWidth()获取)。
            resWidth = getSuggestedMinimumWidth();
            // 如果未设置背景图片，则设置为屏幕宽高的默认值
            resWidth = (resWidth == 0 ? width : resWidth);

            resHeight = getSuggestedMinimumHeight();
            resHeight = (resHeight == 0 ? width : resHeight);
        } else {
            // 若都为精确值，则直接去最小值
            resWidth = resHeight = Math.min(width, height);
        }

        setMeasuredDimension(resWidth, resHeight);
    }

    /**
     * 丈量菜单项尺寸
     */
    private void measureChildView() {
        mRadius = Math.max(getMeasuredWidth(), getMeasuredHeight());

        int count = getChildCount();

        // menu item尺寸
        int childSize = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);

        int childMode = MeasureSpec.EXACTLY;

        // 迭代测量
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            // 计算Menu item的尺寸，以及设计好的模式，去对item进行测量
            int makeMeasureSpec = -1;

            makeMeasureSpec = MeasureSpec.makeMeasureSpec(childSize, childMode);
            child.measure(makeMeasureSpec, makeMeasureSpec);
        }

        mPadding = RADIO_PADDING_LAYOUT * mRadius;


    }

    /**
     * 调用场景：在view给其孩子设置尺寸和位置时被调用。子view，包括孩子在内，必须重写onLayout(boolean, int, int, int, int)方法，并且调用各自的layout(int, int, int, int)方法
     * 参数说明：参数changed表示view有新的尺寸或位置；参数l表示相对于父view的Left位置；参数t表示相对于父view的Top位置；参数r表示相对于父view的Right位置；参数b表示相对于父view的Bottom位置
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.e("BetterCircleMenuLayout", "onLayout");

        final int childCount = getChildCount();
        int left;
        int top;
        // menu item尺寸
        int itemWidth = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);
        // 根据item的个数，计算item布局占用的角度
        float angleDelay = 360 / childCount;

        for (int i = 0; i <  childCount; i ++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            // 其实角度
            mStartAngle %= 360;
            // 中心点到menu item中心的距离
            float distanceFromCenter = mRadius / 2f - itemWidth/2 - mPadding;

            // distanceFromCenter cosa即menu item中心点的left坐标
            left = mRadius / 2 + (int) ( Math.round(distanceFromCenter * Math.cos(Math.toRadians(mStartAngle))) - 1/2f * itemWidth);
            top = mRadius / 2 +(int) ( Math.round(distanceFromCenter * Math.sin(Math.toRadians(mStartAngle))) - 1/2f * itemWidth);

            child.layout(left, top, left + itemWidth, top + itemWidth);

            mStartAngle += angleDelay;
        }

    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public void requestLayout() {
        super.requestLayout();
        Log.e("BetterCircleMenuLayout", "requestLayout");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.e("BetterCircleMenuLayout", "draw");
    }



}
