package com.elife.classproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.elife.classproject.FirstActivity;
import com.elife.classproject.R;

public class FirstFragment extends Fragment {


    View mView;
    private Button mBtn;

    public FirstFragment() {
    }




    // 创建View并返回
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("-----", "FirstFragment-> onCreateView");
        mView = inflater.inflate(R.layout.fragment_first, container, false);

        return mView;
    }

    // 做一些控件的初始化与事件注册---还可以做一些业务
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e("-----", "FirstFragment-> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        mBtn = (Button) mView.findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "btn", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setClass(getActivity(), FirstActivity.class);
                startActivity(intent);
            }
        });

        FragActivity fa = (FragActivity) getActivity();
        fa.main();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("-----", "FirstFragment-> onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("-----", "FirstFragment-> onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("-----", "FirstFragment-> onDestroyView");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("-----", "FirstFragment-> onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("-----", "FirstFragment-> onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("-----", "FirstFragment-> onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("-----", "FirstFragment-> onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("-----", "FirstFragment-> onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("-----", "FirstFragment-> onStop");
    }

    public void test() {

    }
}
