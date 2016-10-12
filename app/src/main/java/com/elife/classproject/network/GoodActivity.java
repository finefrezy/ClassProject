package com.elife.classproject.network;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.elife.classproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class GoodActivity extends AppCompatActivity {


    ListView mListView;

    List<GoodModel> listGood;

    GoodAdapter mGoodAdapter;

    LayoutInflater mLayoutInflater;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        mLayoutInflater = LayoutInflater.from(this);

        mListView = (ListView) findViewById(R.id.list);

        initData();
    }

    private void initData() {

        GoodAsyncTask goodAsync = new GoodAsyncTask();
        goodAsync.execute();
    }

    public class GoodAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPostExecute(String s) {

            mGoodAdapter = new GoodAdapter();
            mListView.setAdapter(mGoodAdapter);

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String json = NetUtils.getGoodInfo();
                Gson gson = new Gson();
                Type type = new TypeToken<List<GoodModel>>() {
                }.getType();
                listGood = gson.fromJson(json, type);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class GoodAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (null == listGood) {
                return 0;
            }
            return listGood.size();
        }

        @Override
        public Object getItem(int i) {
            return listGood.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;

            if (null == view) {
                view = mLayoutInflater.inflate(R.layout.item_list_good, null);
                viewHolder = new ViewHolder();

                viewHolder.imageGood = (ImageView) view.findViewById(R.id.good_photo);
                viewHolder.textName = (TextView) view.findViewById(R.id.good_name);
                viewHolder.textPrice = (TextView) view.findViewById(R.id.price);
                viewHolder.textPostFee = (TextView) view.findViewById(R.id.post_fee);

                view.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) view.getTag();
            }


            NetUtils.imageResult( viewHolder.imageGood, "http://10.50.8.79:8088/elife/" + listGood.get(i).getGoodImg(), new NetUtils.OnCallBack() {
                     @Override
                     public void setBitmap(Bitmap bitmap,ImageView view) {
                         view.setImageBitmap(bitmap);
                     }
                 });



            viewHolder.textName.setText(listGood.get(i).getGoodName());
            viewHolder.textPrice.setText("￥ " + listGood.get(i).getGoodPrice());

            if (listGood.get(i).getGoodPostFee() == 0) {
                viewHolder.textPostFee.setText("免运费");

            } else {
                viewHolder.textPostFee.setText("运费: ￥" + listGood.get(i).getGoodPostFee());

            }

            return view;
        }
    }

    class ViewHolder {
        ImageView imageGood;
        TextView textName;
        TextView textPrice;
        TextView textPostFee;
    }

}




