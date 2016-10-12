package com.elife.classproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elife.classproject.R;

/**
 * Created by tzhang on 2016/9/7.
 */
public class SecondFragment extends Fragment {


    private ImageView mImageView;
    private View mView;
    private ICallBack mCallBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("-----", "SecondFragment-> onCreateView");
        mView = inflater.inflate(R.layout.fragment_second, container, false);

        Bundle bundle = getArguments();
        Log.e("-----", "SecondFragment-> onCreateView" + bundle.getString("abc"));
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e("-----", "SecondFragment-> onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        mImageView = (ImageView) mView.findViewById(R.id.image);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mCallBack) {
                    mCallBack.callBack(1000);
                }
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("-----", "SecondFragment-> onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("-----", "SecondFragment-> onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("-----", "SecondFragment-> onDestroyView");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("-----", "SecondFragment-> onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("-----", "SecondFragment-> onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("-----", "SecondFragment-> onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("-----", "SecondFragment-> onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("-----", "SecondFragment-> onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("-----", "SecondFragment-> onStop");
    }


    public void second() {

    }

    public void setmCallBack(ICallBack callBack) {
        this.mCallBack = callBack;
    }

    public interface ICallBack {

        void callBack(int i);
    }


}
