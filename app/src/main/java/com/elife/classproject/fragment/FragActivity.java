package com.elife.classproject.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elife.classproject.R;

// fragment与activity之间的传值与方法互相调用，必须掌握
public class FragActivity extends AppCompatActivity {// implements SecondFragment.ICallBack

    private Button mBtn;
            private FirstFragment mFf;
     SecondFragment sf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("-----", "FragActivity-> onCreate");
        setContentView(R.layout.activity_frag);

        mFf = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        mFf.test();

        mBtn = (Button) findViewById(R.id.frag);

       final FragmentManager fragmentManager = getSupportFragmentManager();// 因为当前activity继承FragmentActivity，使用该方法
        // getFragmentManager当前activity继承Activity，使用该方法
         FragmentTransaction ft = fragmentManager.beginTransaction();


        Bundle bundle = new Bundle();
        bundle.putString("abc", "123");
        sf = new SecondFragment();
        ft.add(R.id.dynamic_container, sf);
        ft.addToBackStack(null);
        sf.setArguments(bundle);
        sf.setmCallBack(new SecondFragment.ICallBack() {
            @Override
            public void callBack(int i) {
                Toast.makeText(getApplicationContext(), "" + i, Toast.LENGTH_SHORT).show();
            }
        });
        ft.commit();



       final FirstFragment firstFragment = new FirstFragment();
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fragmentManager.beginTransaction();

                // 销毁之前的fragment（remove），让新的fragment显示在上面
//                ft.replace(R.id.dynamic_container, firstFragment);
                // 把对应的fragmeng销毁
//                ft.remove(sf);

                // 方法没有执行
                if (!sf.isHidden()) {
                    ft.hide(sf);
                }


                if (null == fragmentManager.findFragmentByTag("first")) {
                    ft.add(R.id.dynamic_container, firstFragment, "first");
                    ft.addToBackStack(null);
                } else {
                    if (firstFragment.isHidden()) {
                        ft.hide(sf);
                        // 方法没有执行
                        ft.show(firstFragment);
                    } else {
                        ft.hide(firstFragment);
                        ft.show(sf);
                    }

                }


                ft.commit();
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("-----", "FragActivity-> onDestroy");
    }

    @Override
    protected void onStart() {
        Log.e("-----", "FragActivity-> onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("-----", "FragActivity-> onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("-----", "FragActivity-> onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("-----", "FragActivity-> onResume");
    }

    public void main() {
        sf.second();

    }

//    @Override
//    public void callBack(int i) {
//        Toast.makeText(getApplicationContext(), "" + i, Toast.LENGTH_SHORT).show();
//    }
}
