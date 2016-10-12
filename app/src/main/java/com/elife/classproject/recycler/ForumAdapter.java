package com.elife.classproject.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elife.classproject.R;

import java.util.List;


public class ForumAdapter extends RecyclerView.Adapter {

	private List<ForumMessageModel> mListForums;

	public ForumAdapter(List<ForumMessageModel> forumMessages) {
		mListForums = forumMessages;
	}

	@Override
	public int getItemCount() {
		if (null == mListForums) {
			return 0;
		}
		return mListForums.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		ForumViewHolder fvh = (ForumViewHolder) viewHolder;
		fvh.mPosition = position;
		fvh.mTextMsg.setText(mListForums.get(position).userMessage);
		fvh.mTextName.setText(mListForums.get(position).userName);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

		View view = View.inflate(viewGroup.getContext(), R.layout.recycler_forum_item,
				null);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		view.setLayoutParams(lp);
		return new ForumViewHolder(view);

	}

	private IRecyclerViewListener mRecyclerViewListener;

	public interface IRecyclerViewListener {
		public void onItemClick(int position);

		public boolean onItemLongClick(int position);
	}

	public void setOnRecyclerViewListener(Context forumMsgActivity) {
		mRecyclerViewListener = (IRecyclerViewListener) forumMsgActivity;
	}

	class ForumViewHolder extends ViewHolder implements
			OnClickListener, OnLongClickListener {

		private TextView mTextName;
		private TextView mTextMsg;
		private View mRootView;
		private int mPosition;

		public ForumViewHolder(View view) {
			super(view);
			mTextMsg = (TextView) view.findViewById(R.id.forum_message);
			mTextName = (TextView) view.findViewById(R.id.forum_name);
			mRootView = view.findViewById(R.id.root_view);
			mRootView.setOnClickListener(this);
			mRootView.setOnLongClickListener(this);

		}

		@Override
		public boolean onLongClick(View v) {
			if (null != mRecyclerViewListener) {
				return mRecyclerViewListener.onItemLongClick(mPosition);
			}

			return false;
		}

		@Override
		public void onClick(View v) {
			if (null != mRecyclerViewListener) {
				mRecyclerViewListener.onItemLongClick(mPosition);
			}

		}

	}

	public void addData(int position) {
		ForumMessageModel fmm = new ForumMessageModel();
		fmm.userMessage = "insert" + position;
		fmm.userName = "insert" + position;
		mListForums.add(position, fmm);
		notifyItemInserted(position);
	}

	public void removeData(int position) {
		mListForums.remove(position);
		notifyItemRemoved(position);
	}

}
