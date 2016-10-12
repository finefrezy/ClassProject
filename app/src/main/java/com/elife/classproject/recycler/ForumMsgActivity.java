package com.elife.classproject.recycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.elife.classproject.R;

import java.util.ArrayList;
import java.util.List;

public class ForumMsgActivity extends Activity implements ForumAdapter.IRecyclerViewListener {

	RecyclerView mRecyclerView;
	private ForumAdapter mForumAdapter;
	private List<ForumMessageModel> mForumMessages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum_msg);

		mRecyclerView = (RecyclerView) findViewById(R.id.forum_list);
		// 使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
		mRecyclerView.setHasFixedSize(true);

		/*
		 * LinearLayoutManager 现行管理器，支持横向、纵向。 GridLayoutManager 网格布局管理器
		 * StaggeredGridLayoutManager 瀑布流式布局管理器
		 */

		 LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		// 如果你需要显示的是横向滚动的列表或者竖直滚动的列表，则使用这个LayoutManager。
		// 生成这个LinearLayoutManager之后可以设置他滚动的方向，默认竖直滚动，所以这里没有显式地设置。
		 layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		 mRecyclerView.setLayoutManager(layoutManager);

//		mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

		// mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
//		// DividerItemDecoration.VERTICAL_LIST));
//		mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
		initData();
		mForumAdapter = new ForumAdapter(mForumMessages);
		mForumAdapter.setOnRecyclerViewListener(this);

		mRecyclerView.setAdapter(mForumAdapter);
	}

	void initData() {

		mForumMessages = new ArrayList<ForumMessageModel>();

		for (int i = 0; i < 20; i++) {
			ForumMessageModel fmm = new ForumMessageModel();
			if (i % 2 == 0) {
				fmm.userIconInt = R.drawable.head_icon;
				fmm.userMessage = "this is message " + i + "";
				fmm.time = "2015-10-11 12:00:21";
			} else {
				fmm.userIconInt = R.drawable.head_icon1;
				fmm.userMessage = i + " 红豆生南国，春来发几枝。 愿君多采撷，此物最相思";
				fmm.time = "2015-11-11 12:00:21";
			}

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
}
