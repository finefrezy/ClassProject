package com.elife.classproject.composite;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elife.classproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("-----", "HomeFragment-> onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e("-----", "HomeFragment-> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("-----", "HomeFragment-> onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("-----", "HomeFragment-> onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("-----", "HomeFragment-> onDestroyView");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("-----", "HomeFragment-> onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("-----", "HomeFragment-> onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("-----", "HomeFragment-> onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("-----", "HomeFragment-> onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("-----", "HomeFragment-> onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("-----", "HomeFragment-> onStop");
    }

    public void test() {

    }

}
