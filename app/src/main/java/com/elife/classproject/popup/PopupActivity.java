package com.elife.classproject.popup;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.elife.classproject.R;


// 如果键盘弹不出来，可以搜索android软键盘的显示与隐藏解决这个问题
public class PopupActivity extends AppCompatActivity {

    private Button mBtnPopup;
    private LayoutInflater mInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        mBtnPopup = (Button) findViewById(R.id.popup);
        mInflater = LayoutInflater.from(this);

        mBtnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });
    }

    private void showPopup() {
       View view = mInflater.inflate(R.layout.popup_item, null);

        Button btn = (Button) view.findViewById(R.id.popup_btn);



        // width height   ---- ViewGroup.LayoutParams.MATCH_PARENT
        // 像素 屏幕密度
        // px sp dp转化关系
        // 300代表的是像素
        final PopupWindow pw = new PopupWindow(view,300, ViewGroup.LayoutParams.WRAP_CONTENT);
        // PopupWindow点击外面小时，可以设置一下2个属性
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setOutsideTouchable(true);

//        pw.showAsDropDown(mBtnPopup);
        // x y
//        pw.showAsDropDown(mBtnPopup, 100,100);

//        pw.showAtLocation(mBtnPopup, Gravity.LEFT| Gravity.TOP, 20, 20);

// 版本兼容，当前系统的版本号android.os.Build.VERSION.SDK_INT
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            Toast.makeText(getApplicationContext(), "===" + android.os.Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
            pw.showAsDropDown(mBtnPopup, 20, 20, Gravity.LEFT );
        } else {
            pw.showAtLocation(mBtnPopup, Gravity.LEFT, 20, 20);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "123", Toast.LENGTH_SHORT).show();
                if ( pw.isShowing()) {
                    pw.dismiss();
                }
            }
        });

    }
}
