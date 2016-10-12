package com.elife.classproject.forum.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class ZTRecyclerView extends RecyclerView implements OnScrollListener {

	public ZTRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ZTRecyclerView(Context context) {
		super(context);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}
	
	

}
