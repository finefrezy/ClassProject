package com.elife.classproject.composite;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elife.classproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    private OnCallBackListener mOnCallBackListener;
    private Button mBtn;
    private View mView;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("-----", "DiscoverFragment-> onCreateView");
        mView = inflater.inflate(R.layout.fragment_discover, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("-----", "DiscoverFragment-> onActivityCreated");
        mBtn = (Button) mView.findViewById(R.id.btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mOnCallBackListener) {
                    mOnCallBackListener.onCallBack("ABC");
                }
            }
        });

    }

    public void setOnCallBackListener(OnCallBackListener listener){
        mOnCallBackListener = listener;
    }

   public interface OnCallBackListener{
        void onCallBack(String str);
    }


  

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("-----", "DiscoverFragment-> onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("-----", "DiscoverFragment-> onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("-----", "DiscoverFragment-> onDestroyView");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("-----", "DiscoverFragment-> onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("-----", "DiscoverFragment-> onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("-----", "DiscoverFragment-> onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("-----", "DiscoverFragment-> onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("-----", "DiscoverFragment-> onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("-----", "DiscoverFragment-> onStop");
    }
}
