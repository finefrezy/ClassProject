package com.elife.classproject.forum.holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elife.classproject.R;

public class FooterViewHolder extends RecyclerView.ViewHolder{

	ProgressBar mProgressBar;
	TextView mHintText;
	
	
	public FooterViewHolder(View view) {
		super(view);
		mProgressBar = (ProgressBar) view.findViewById(R.id.footer_progressbar);
		mHintText = (TextView) view.findViewById(R.id.footer_hint_textview);
	}

}
