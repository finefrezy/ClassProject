package com.elife.classproject.list;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.elife.classproject.R;

import java.util.ArrayList;

public class RLListViewActivity extends Activity implements RLListView.IXListViewListener {
	private RLListView mListView;
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> items = new ArrayList<String>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rllist_view);
		mListView = (RLListView) findViewById(R.id.listView);
		geneItems();
		mListView.setPullLoadEnable(true);
		mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
		mListView.setAdapter(mAdapter);
//		mListView.setPullRefreshEnable(false);
		mListView.setXListViewListener(this);
		
		
		
		mHandler = new Handler();
	}

	private void geneItems() {
		for (int i = 0; i != 10; ++i) {
			items.add("数据" + (++start));
		}
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				// mAdapter.notifyDataSetChanged();
				mAdapter = new ArrayAdapter<String>(RLListViewActivity.this,
						R.layout.list_item, items);
				mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 4000);
	}

	@Override
	public void onLoadMore() {
		
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
}
