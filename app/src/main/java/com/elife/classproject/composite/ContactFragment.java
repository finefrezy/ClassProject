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
public class ContactFragment extends Fragment {


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("-----", "ContactFragment-> onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e("-----", "ContactFragment-> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("-----", "ContactFragment-> onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("-----", "ContactFragment-> onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("-----", "ContactFragment-> onDestroyView");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("-----", "ContactFragment-> onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("-----", "ContactFragment-> onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("-----", "ContactFragment-> onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("-----", "ContactFragment-> onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("-----", "ContactFragment-> onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("-----", "ContactFragment-> onStop");
    }


}
