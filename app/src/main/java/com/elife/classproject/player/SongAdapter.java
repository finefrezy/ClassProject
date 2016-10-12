package com.elife.classproject.player;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elife.classproject.R;

import java.util.List;

/**
 * Created by tzhang on 2016/9/15.
 */
public class SongAdapter extends BaseAdapter {

    List<SongModel> mSongList;
    LayoutInflater mInflater;

    public SongAdapter(List<SongModel> songList, Context context) {
        this.mSongList = songList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (null != mSongList) {
            return mSongList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return mSongList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (null == view) {
            view = mInflater.inflate(R.layout.song_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageIcon = (ImageView) view.findViewById(R.id.image_song);
            viewHolder.textTitle = (TextView) view.findViewById(R.id.text_title);
            viewHolder.textDesc = (TextView) view.findViewById(R.id.text_desc);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if(TextUtils.isEmpty(mSongList.get(i).getAlbumImagePath())) {
            viewHolder.imageIcon.setImageResource(R.drawable.live_icn_vote);
        } else {
            viewHolder.imageIcon.setImageBitmap(BitmapFactory.decodeFile(mSongList.get(i).getAlbumImagePath()));
        }

        viewHolder.textTitle.setText(mSongList.get(i).getTitle());
        viewHolder.textDesc.setText(mSongList.get(i).getAlbum());


        return view;
    }
}

class ViewHolder {
    ImageView imageIcon;
    TextView textTitle;
    TextView textDesc;
    TextView textLong;
}
