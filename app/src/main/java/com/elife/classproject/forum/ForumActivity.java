package com.elife.classproject.forum;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.elife.classproject.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 下拉刷新可以同过给顶部加一个线性布局实现，动态改变线性布局的高度
 * @author tzhang
 *
 */
public class ForumActivity extends Activity implements OnRefreshListener {
	private static final String TAG = ForumActivity.class.getSimpleName();

	RecyclerView mRecyclerView;
	private ForumAdapter mForumAdapter;
	private List<FroumModel> mForumList;
	private int mScreenWidth;
	private SwipeRefreshLayout mSwipeRefreeshLayout;
	private int mLastVisibleItem;
	private int mFirsrtVisibleItem;
	private LinearLayoutManager mLayoutManager;

	private LinearLayout mTopLinear;

	private static final int LOAD_MORE = 0X100;

	private int mRawX = 0;
	private int mRawY = 0;

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case LOAD_MORE:
				addData(2);
				refreshHandler();
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum);

		mSwipeRefreeshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
		mRecyclerView = (RecyclerView) findViewById(R.id.forum_list);
		mTopLinear = (LinearLayout) findViewById(R.id.top_height);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 0);
		mTopLinear.setLayoutParams(params);

		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		mScreenWidth = mDisplayMetrics.widthPixels;

		// 进度条的颜色变化，最多可以设置4种颜色
		mSwipeRefreeshLayout.setColorSchemeResources(R.color.blue, R.color.red,
				R.color.green);
		mSwipeRefreeshLayout.setOnRefreshListener(this);
		// 第一次进入页面的时候显示加载进度条
		mSwipeRefreeshLayout.setProgressViewOffset(false, 0, (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
						.getDisplayMetrics()));

		// 使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(this);
		// 如果你需要显示的是横向滚动的列表或者竖直滚动的列表，则使用这个LayoutManager。
		// 生成这个LinearLayoutManager之后可以设置他滚动的方向，默认竖直滚动，所以这里没有显式地设置。
		mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(mLayoutManager);

		mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView,
					int newState) {
				super.onScrollStateChanged(recyclerView, newState);

				if (newState == RecyclerView.SCROLL_STATE_IDLE
						&& mLastVisibleItem + 1 == mForumAdapter.getItemCount()) {
					// mSwipeRefreeshLayout.setRefreshing(true);

					// mLayoutManager.getChildAt(mLastVisibleItem).findViewById(R.id.footer_progressbar).setVisibility(View.VISIBLE);
					// mLayoutManager.getChildAt(mLastVisibleItem).findViewById(R.id.footer_progressbar).setVisibility(View.VISIBLE);
					mHandler.sendEmptyMessage(LOAD_MORE);
				}

			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);


				mFirsrtVisibleItem = mLayoutManager
						.findFirstVisibleItemPosition();
				mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

			}

		});

		mSwipeRefreeshLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:

					mRawX = (int) event.getRawX();
					mRawY = (int) event.getRawY();
					break;
				case MotionEvent.ACTION_MOVE:

					setTopHeight((int) event.getRawY() - mRawY);
					mRawY = (int) event.getRawY();

					break;
				case MotionEvent.ACTION_UP:

					if (mFirsrtVisibleItem == 0) {
						resetTopHeight();
					}

					break;
				}
				return false;
			}
		});

		/**
		 * setColorSchemeColors(int... colors) 设置 进度条的颜色变化，最多可以设置4种颜色
		 * 设置SwipeRefreshLayout当前是否处于刷新状态
		 * ，一般是在请求数据的时候设置为true，在数据被加载到View中后，设置为false
		 * setProgressViewOffset(boolean scale, int start, int end)
		 * 调整进度条距离屏幕顶部的距离
		 */
		// mSwipeRefreeshLayout.setRefreshing(true);
		mSwipeRefreeshLayout.setProgressViewOffset(false, -80, 100);
		initData(10);
		refreshHandler();
	}

	/**
	 * update ui about refresh data or load more data
	 */
	private void refreshHandler() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				mSwipeRefreeshLayout.setRefreshing(false);
				if (null == mForumAdapter) {
					mForumAdapter = new ForumAdapter(mForumList,
							ForumActivity.this, mScreenWidth);
					mRecyclerView.setAdapter(mForumAdapter);
				} else {
					mForumAdapter.notifyDataSetChanged();
				}

			}
		}, 2000);
	}

	/**
	 * first start, init data
	 * 
	 * @param count
	 */
	private void initData(int count) {
		if (null == mForumList) {
			mForumList = new ArrayList<FroumModel>();
		} else {
			mForumList.clear();
		}
		addData(count);
	}

	/**
	 * add data
	 * 
	 * @param count
	 */
	private void addData(int count) {
		for (int i = 0; i < count; i++) {
			mForumList.add(getFm(i));
		}
	}

	public FroumModel getFm(int i) {

		FroumModel fm = new FroumModel();

		if (i % 2 == 0) {
			fm.userName = "奥巴马";
			fm.iconId = R.drawable.head_icon;
			fm.msgContent = "We are the ones we have beening waiting for! 我们就是我们一直在寻找的救世主!"
					+ "The world has changed, and we must change with it. 世界已经变了,我们必须同时改变。"
					+ "This union may never be perfect, but generation after generation has shown that it can always be perfected！"
					+ "我们的国家也许从来就无法完美,但一代又一代人显示美国的国家可以不断被改善.";
			fm.imgResIds = new int[] { R.drawable.banner_aisi,
					R.drawable.banner_kitty, R.drawable.banner_mangseng,
					R.drawable.banner_xiaoxin };
		} else {
			fm.userName = "习大大";
			fm.iconId = R.drawable.head_icon1;
			fm.msgContent = "功崇惟志，业广惟勤！打铁还需自身硬，要容得下尖锐批评，让青春在时代进步中焕发出绚丽的光彩！";
			fm.imgResIds = new int[] { R.drawable.a11, R.drawable.a12,
					R.drawable.a13, R.drawable.a14, R.drawable.a15,
					R.drawable.a16, R.drawable.a17, R.drawable.a18 };
		}
		fm.timeValue = System.currentTimeMillis() - i * 1000 * 60 * 60;
		return fm;

	}

	public void refreshData(int addFlag) {
		FroumModel fm = getFm(addFlag);
		mForumList.add(0, fm);
	}

	@Override
	public void onRefresh() {
		mSwipeRefreeshLayout.setRefreshing(true);
		refreshData(0);
		refreshHandler();
	}

	public void setTopHeight(int dy) {
		if (dy > 0) {
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTopLinear
					.getLayoutParams();
			params.height = params.height + dy/3;
			mTopLinear.setLayoutParams(params);
		}
	}

	public void resetTopHeight() {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTopLinear
				.getLayoutParams();
		params.height = 0;
		mTopLinear.setLayoutParams(params);
	}

}
