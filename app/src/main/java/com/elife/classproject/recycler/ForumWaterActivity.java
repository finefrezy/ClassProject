package com.elife.classproject.recycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.elife.classproject.R;

import java.util.ArrayList;
import java.util.List;


public class ForumWaterActivity extends Activity implements
		ForumAdapter.IRecyclerViewListener, OnClickListener {

	RecyclerView mRecyclerView;
	private ForumAdapter mForumAdapter;
	private List<ForumMessageModel> mForumMessages;

	Button mAddBtn;
	Button mRemoveBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_msg);

		mRecyclerView = (RecyclerView) findViewById(R.id.forum_list);

		mAddBtn = (Button) findViewById(R.id.add_one);
		mRemoveBtn = (Button) findViewById(R.id.remove_one);
		mAddBtn.setOnClickListener(this);
		mRemoveBtn.setOnClickListener(this);
		

		// 使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
		mRecyclerView.setHasFixedSize(true);

		// 传入的是StaggeredGridLayoutManager.VERTICAL代表有多少列；那么传入的如果是StaggeredGridLayoutManager.HORIZONTAL就代表有多少行
		// 如果是横向的时候，item的宽度需要注意去设置
		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
				StaggeredGridLayoutManager.VERTICAL));

		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

		initData();
		mForumAdapter = new ForumAdapter(mForumMessages);
		mForumAdapter.setOnRecyclerViewListener(this);
		mRecyclerView.setAdapter(mForumAdapter);
	}

	void initData() {

		mForumMessages = new ArrayList<ForumMessageModel>();

		for (int i = 0; i < 20; i++) {
			ForumMessageModel fmm = new ForumMessageModel();
			fmm.userIconInt = R.drawable.head_icon;
			fmm.userMessage = "this is message " + i;
			fmm.userName = "user " + i;
			mForumMessages.add(fmm);
		}

	}

	@Override
	public void onItemClick(int position) {
		Toast.makeText(getApplicationContext(), "click => " + position,
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onItemLongClick(int position) {
		Toast.makeText(getApplicationContext(), "long click = >" + position,
				Toast.LENGTH_LONG).show();
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_one:
			mForumAdapter.addData(3);
			
			
			break;
		case R.id.remove_one:
			mForumAdapter.removeData(3);
			break;
		}
	}
}
