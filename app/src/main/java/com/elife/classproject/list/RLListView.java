package com.elife.classproject.list;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.elife.classproject.R;

public class RLListView extends ListView implements OnScrollListener {

	private static final String TAG = RLListView.class.getName();

	private Scroller mScroller; // used for scroll back
	private float mLastY = -1; // save event y
	private OnScrollListener mScrollListener; // user's scroll listener

	// the interface to trigger refresh and load more.
	private IXListViewListener mListViewListener;

	// -- header view
	private HeaderView mHeaderView;
	// header view content, use it to calculate the Header's height. And hide it
	// when disable pull refresh.
	private RelativeLayout mHeaderViewContent;
	private TextView mHeaderTimeView;
	private int mHeaderViewHeight; // header view's height
	private boolean mEnablePullRefresh = true;
	private boolean mPullRefreshing = false; // is refreashing.

	// -- footer view
	private FooterView mFooterView;
	private boolean mEnablePullLoad;
	private boolean mPullLoading;
	private boolean mIsFooterReady = false;

	// total list items, used to detect(观察) is at the bottom of listview.
	private int mTotalItemCount;

	// for mScroller, scroll back from header or footer.
	private int mScrollBack;
	private final static int SCROLLBACK_HEADER = 0;
	private final static int SCROLLBACK_FOOTER = 1;
	// scroll back duration
	private final static int SCROLL_DURATION = 400;
	// when pull up >= 50px at bottom, trigger load more.
	private final static int PULL_LOAD_MORE_DELTA = 50;

	private final static float OFFSET_RADIO = 1.8f;

	public RLListView(Context context) {
		super(context);
		initView(context);
	}

	public RLListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public RLListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	public void initView(Context context) {
		mScroller = new Scroller(context, new DecelerateInterpolator());
		// RLListView need the scroll event, and it will dispatch the event to
		// user's listener (as a proxy).
		super.setOnScrollListener(this);

		// init header view
		mHeaderView = new HeaderView(context);
		mHeaderViewContent = (RelativeLayout) mHeaderView
				.findViewById(R.id.header_content);
		mHeaderTimeView = (TextView) mHeaderView.findViewById(R.id.header_time);
		addHeaderView(mHeaderView);

		// init footer view
		mFooterView = new FooterView(context);

		// init header height
		// getViewTreeObserver()->view事件的观察者,OnGlobalLayoutListener()当一个视图树将要绘制时，所要调用的回调函数的接口类
		mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						mHeaderViewHeight = mHeaderViewContent.getHeight();
						getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
					}
				});

	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		// make sure FooterView is the last footer view, and only add once.
		if (mIsFooterReady == false) {
			mIsFooterReady = true;
			addFooterView(mFooterView);
		}
		super.setAdapter(adapter);
	}

	/**
	 * enable or disable pull down refresh feature.
	 * 
	 * @param enable
	 */
	public void setPullRefreshEnable(boolean enable) {
		mEnablePullRefresh = enable;
		if (!mEnablePullRefresh) { // disable, hide the content
			mHeaderViewContent.setVisibility(View.INVISIBLE);
		} else {
			mHeaderViewContent.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * enable or disable pull up load more feature.
	 * 
	 * @param enable
	 */
	public void setPullLoadEnable(boolean enable) {
		mEnablePullLoad = enable;
		if (!mEnablePullLoad) {
			mFooterView.hide();
			mFooterView.setOnClickListener(null);
			// make sure "pull up" don't show a line in bottom when listview
			// with one page
			setFooterDividersEnabled(false);
		} else {
			mPullLoading = false;
			mFooterView.show();
			mFooterView.setState(FooterView.STATE_NORMAL);
			// make sure "pull up" don't show a line in bottom when listview
			// with one page
			setFooterDividersEnabled(true);
			// both "pull up" and "click" will invoke load more.
			mFooterView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startLoadMore();
				}
			});
		}
	}

	/**
	 * stop refresh, reset header view.
	 */
	public void stopRefresh() {
		if (mPullRefreshing == true) {
			mPullRefreshing = false;
			resetHeaderHeight();
		}
	}

	/**
	 * stop load more, reset footer view.
	 */
	public void stopLoadMore() {
		if (mPullLoading == true) {
			mPullLoading = false;
			mFooterView.setState(FooterView.STATE_NORMAL);
		}
	}

	/**
	 * set last refresh time
	 * 
	 * @param time
	 */
	public void setRefreshTime(String time) {
		mHeaderTimeView.setText(time);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// MOVE 2  DOWN 0  UP 1
		Log.d(TAG, "onTouchEvent = " + ev.getAction());

		// getRawX()和getRawY()获得的是相对屏幕的位置，getX()和getY()获得的永远是相对view的触摸位置坐标
		// （这两个值不会超过view的长度和宽度）
		if (mLastY == -1) {
			mLastY = ev.getRawY();

//			Log.d(TAG, "mTotalItemCount = " + mTotalItemCount);
		}

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastY = ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float deltaY = ev.getRawY() - mLastY;
			mLastY = ev.getRawY();

//			Log.d(TAG, "getFirstVisiblePosition = " + getFirstVisiblePosition());
//			Log.d(TAG, "getLastVisiblePosition = " + getLastVisiblePosition());

			if (getFirstVisiblePosition() == 0
					&& (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
				// the first item is showing, header has shown or pull down.
				updateHeaderHeight(deltaY / OFFSET_RADIO);
				invokeOnScrolling();
			} else if (getLastVisiblePosition() == mTotalItemCount - 1
					&& (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
				// last item, already pulled up or want to pull up.
				updateFooterHeight(-deltaY / OFFSET_RADIO);
			}
			break;
		default:
			mLastY = -1; // reset
			if (getFirstVisiblePosition() == 0) {
				// invoke refresh
				if (mEnablePullRefresh
						&& mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
					mPullRefreshing = true;
					// 更新头部UI显示
					mHeaderView.setState(HeaderView.STATE_REFRESHING);
					// 开始请求数据
					if (mListViewListener != null) {
						mListViewListener.onRefresh();
					}
				}
				resetHeaderHeight();
			} else if (getLastVisiblePosition() == mTotalItemCount - 1) {
				// invoke load more.
				if (mEnablePullLoad
						&& mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA
						&& !mPullLoading) {
					// 开始加载更多数据
					startLoadMore();
				}
				resetFooterHeight();
			}
			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * reset header view's height.
	 */
	private void resetHeaderHeight() {
		int height = mHeaderView.getVisiableHeight();
		if (height == 0) // not visible.
			return;
		// refreshing and header isn't shown fully. do nothing.
		if (mPullRefreshing && height <= mHeaderViewHeight) {
			return;
		}
		int finalHeight = 0; // default: scroll back to dismiss header.
		// is refreshing, just scroll back to show all the header.
		if (mPullRefreshing && height > mHeaderViewHeight) {
			finalHeight = mHeaderViewHeight;
		}
		mScrollBack = SCROLLBACK_HEADER;

		// 第一个参数是起始移动的x坐标值，第二个是起始移动的y坐标值，第三个第四个参数都是移到某点的坐标值，而duration
		// 当然就是执行移动的时间
		mScroller.startScroll(0, height, 0, finalHeight - height,
				SCROLL_DURATION);
		// trigger computeScroll
		invalidate();
	}

	private void updateHeaderHeight(float delta) {
		mHeaderView.setVisiableHeight((int) delta
				+ mHeaderView.getVisiableHeight());
		if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
			if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
				mHeaderView.setState(HeaderView.STATE_READY);
			} else {
				mHeaderView.setState(HeaderView.STATE_NORMAL);
			}
		}
		// 防止listview item继续滚动
		setSelection(0); // scroll to top each time
	}

	private void resetFooterHeight() {
		int bottomMargin = mFooterView.getBottomMargin();
		if (bottomMargin > 0) {
			mScrollBack = SCROLLBACK_FOOTER;
			mScroller.startScroll(0, bottomMargin, 0, -bottomMargin,
					SCROLL_DURATION);
			invalidate();
		}
	}

	private void updateFooterHeight(float delta) {
		int height = mFooterView.getBottomMargin() + (int) delta;
		if (mEnablePullLoad && !mPullLoading) {
			if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
													// more.
				mFooterView.setState(FooterView.STATE_READY);
			} else {
				mFooterView.setState(FooterView.STATE_NORMAL);
			}
		}
		mFooterView.setBottomMargin(height);

		// setSelection(mTotalItemCount - 1); // scroll to bottom
	}

	private void startLoadMore() {
		mPullLoading = true;
		mFooterView.setState(FooterView.STATE_LOADING);
		if (mListViewListener != null) {
			mListViewListener.onLoadMore();
		}
	}

	// computeScroll在父控件执行drawChild时，会调用这个方法,控制滑屏控制流程
	// 由父视图调用,用来请求子视图根据偏移值 mScrollX,mScrollY重新绘制
	// 在绘制View时，会在draw()过程调用该
	// 方法。因此， 再配合使用Scroller实例，我们就可以获得当前应该的偏移坐标，手动使View/ViewGroup偏移至该处
	// 空方法 ， 为了实现偏移控制，一般自定义View/ViewGroup都需要重载该方法
	@Override
	public void computeScroll() {

		// 返回值为boolean，true说明滚动尚未完成，false说明滚动已经完成
		if (mScroller.computeScrollOffset()) {

			Log.d(TAG, "getCurrY = " + mScroller.getCurrY());

			if (mScrollBack == SCROLLBACK_HEADER) {
				mHeaderView.setVisiableHeight(mScroller.getCurrY());
			} else {
				mFooterView.setBottomMargin(mScroller.getCurrY());
			}
			postInvalidate();
			invokeOnScrolling();
		}
		super.computeScroll();
	}

	private void invokeOnScrolling() {
		if (mScrollListener instanceof OnXScrollListener) {
			OnXScrollListener l = (OnXScrollListener) mScrollListener;
			// 调用接口实现类中的方法，当界面滚动执行方法
			l.onXScrolling(this);
		}
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		mScrollListener = l;
	}

	/**
	 * SCROLL_STATE_IDLE = 0 SCROLL_STATE_TOUCH_SCROLL = 1 SCROLL_STATE_FLING =
	 * 2
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (mScrollListener != null) {
			mScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// send to user's listener
		mTotalItemCount = totalItemCount;
		if (mScrollListener != null) {
			mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount);
		}
	}

	public void setXListViewListener(IXListViewListener l) {
		mListViewListener = l;
	}

	/**
	 * implements this interface to get refresh/load more event.
	 */
	public interface IXListViewListener {
		public void onRefresh();

		public void onLoadMore();
	}

	/**
	 * you can listen ListView.OnScrollListener or this one. it will invoke
	 * onXScrolling when header/footer scroll back.
	 */
	public interface OnXScrollListener extends OnScrollListener {
		public void onXScrolling(View view);
	}

	// 任何声明在xml中的RLListView或new出的RLListView实例，均持有该对象，均可通过该方法调用HeadView的addMoreView方法
	public void addMoreView(View view) {
		mHeaderView.addMoreView(view);
	}

}
