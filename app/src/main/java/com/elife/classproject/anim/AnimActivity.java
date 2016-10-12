package com.elife.classproject.anim;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.elife.classproject.R;

// http://www.2cto.com/kf/201411/353170.html
// http://blog.csdn.net/lmj623565791/article/details/38067475/
public class AnimActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mFrameAnimImage;

    AnimationDrawable animationDrawable;
    Button mStartFrameButton;
    Button mStopFrameButton;


    Button mTranslateBtn;
    Button mScaleBtn;
    Button mRotateBtn;
    Button mAlphaBtn;
    Button mSetBtn;

    Button mValueObjectAnim;

    ImageView mImageTween;
    ImageView mImageObject;
    ImageView mImagePorperty;
    ImageView mImageSport;

    private Button mObjectObjectAnim;
    private Button mSetObjectAnim;

    private Button mRotatePropertyAnim;
    private Button mPropertyImage;
    private Button mHolderProperty;
    private Button mVerticalProperty;
    private Button mPwxProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        mStartFrameButton = (Button) findViewById(R.id.start_anim);
        mStopFrameButton = (Button) findViewById(R.id.stop_anim);

        mTranslateBtn = (Button) findViewById(R.id.translate_anim);
        mScaleBtn = (Button) findViewById(R.id.scale_anim);
        mRotateBtn = (Button) findViewById(R.id.rotate_anim);
        mAlphaBtn = (Button) findViewById(R.id.alpha_anim);
        mSetBtn = (Button) findViewById(R.id.set_anim);

        mValueObjectAnim = (Button) findViewById(R.id.value_object_anim);
        mObjectObjectAnim = (Button) findViewById(R.id.object_object_anim);
        mSetObjectAnim = (Button) findViewById(R.id.set_object_anim);

        mRotatePropertyAnim = (Button) findViewById(R.id.rotate_property_anim);
        mPropertyImage = (Button) findViewById(R.id.alsc_property_anim);
        mHolderProperty = (Button) findViewById(R.id.values_holder_property);
        mVerticalProperty = (Button) findViewById(R.id.vertical_property_anim);
        mPwxProperty = (Button) findViewById(R.id.pwx_property_anim);


        mImageTween = (ImageView) findViewById(R.id.tween_anim);
        // xml配置属性动画image
        mImageObject = (ImageView) findViewById(R.id.object_anim);
        // 代码设置属性动画image
        mImagePorperty = (ImageView) findViewById(R.id.image_liutao);
        // 运动的图片（抛物线）
        mImageSport = (ImageView) findViewById(R.id.image_sport);


        mStartFrameButton.setOnClickListener(this);
        mStopFrameButton.setOnClickListener(this);

        mTranslateBtn.setOnClickListener(this);
        mScaleBtn.setOnClickListener(this);
        mRotateBtn.setOnClickListener(this);
        mAlphaBtn.setOnClickListener(this);
        mSetBtn.setOnClickListener(this);
        mValueObjectAnim.setOnClickListener(this);
        mObjectObjectAnim.setOnClickListener(this);
        mSetObjectAnim.setOnClickListener(this);

        mRotatePropertyAnim.setOnClickListener(this);
        mPropertyImage.setOnClickListener(this);
        mHolderProperty.setOnClickListener(this);

        mVerticalProperty.setOnClickListener(this);
        mPwxProperty.setOnClickListener(this);

        // 帧动画图片   Frame Animation
        mFrameAnimImage = (ImageView) findViewById(R.id.anim_draw);
//         mFrameAnimImage.setImageResource(R.drawable.anim_paly);
        animationDrawable = (AnimationDrawable) mFrameAnimImage.getDrawable();



        // 补间动画的点击事件不会随着控件的移动位置发生变化，可点击位置是自己初始时的位置 Tween


        mImageTween.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "11111111111", Toast.LENGTH_SHORT).show();
            }
        });

        // 属性动画的点击事件会随着控件的移动位置也发生变化  Property（Object） Animation
        mImageSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "property", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.start_anim:
                // 帧动画启动
                animationDrawable.start();
                break;
            case R.id.stop_anim:
                // 帧动画停止
                animationDrawable.stop();
                break;
            case R.id.translate_anim:
                // 通过加载xml配置文件，设置动画
//                TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(AnimActivity.this, R.anim.anim_translate);
//                mImageTween.startAnimation(translateAnimation);

                // 通过代码方式设置动画
                TranslateAnimation tt = new TranslateAnimation(0f, 300f, 0f, 400f);
                tt.setDuration(2000);
                mImageTween.startAnimation(tt);
                break;
            case R.id.scale_anim:
                ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(AnimActivity.this, R.anim.anim_scale);
                mImageTween.startAnimation(scaleAnimation);
                break;
            case R.id.rotate_anim:
                Animation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(AnimActivity.this, R.anim.anim_rotate);
                mImageTween.startAnimation(rotateAnimation);
                break;
            case R.id.alpha_anim:
                Animation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(AnimActivity.this, R.anim.anim_alpha);
                mImageTween.startAnimation(alphaAnimation);
                break;
            case R.id.set_anim:

                AnimationSet as = new AnimationSet(true);
                TranslateAnimation tt1 = new TranslateAnimation(0f, 100f, 0f, 200f);
                tt1.setDuration(2000);
                as.addAnimation(tt1);

                Animation alAn = new AlphaAnimation(1.0f, 0.2f);
                alAn.setDuration(2000);
                alAn.setStartOffset(2000);
                as.addAnimation(alAn);


                alAn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        AnimationSet as = new AnimationSet(true);
//                        TranslateAnimation tt1 = new TranslateAnimation(0f,-100f, 0f,-200f);
//                        tt1.setDuration(2000);
//                        as.addAnimation(tt1);
//                        mImageTween.startAnimation(as);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                Animation alAn1 = new AlphaAnimation(1.0f, 5.0f);
                alAn1.setDuration(2000);
                alAn1.setStartOffset(4000);
                as.addAnimation(alAn1);

                TranslateAnimation tt2 = new TranslateAnimation(0f, -100f, 0f, -200f);
                tt2.setDuration(2000);
                tt2.setStartOffset(6000);
                as.addAnimation(tt2);

                mImageTween.startAnimation(as);

//               AnimationSet animation = (AnimationSet) AnimationUtils.loadAnimation(AnimActivity.this, R.anim.anim_set);
////                animation.setFillAfter(true);
//                mImageTween.startAnimation(animation);
//
//                // 可写可不写，如果需要对动画做监听，就写
//                animation.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
////                        AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(AnimActivity.this, R.anim.anim_set_recover);
////                        mImageTween.startAnimation(animationSet);// 再次调用从最开始的图开始变化
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {
//
//                        }
//                });

                break;
            case R.id.value_object_anim:
                // ValueAnimator动画----可以自己定义具体的行为，因为这个动画会提供回调方法，供实现动画行为
                ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);
                // 因为方法回调的很频繁，10ms回调一次，所以实现动画看起来不会卡（突变）
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        // 当前动画值，即为当前宽度比例值
                        int currentValue = (Integer) valueAnimator.getAnimatedValue();
                        Log.e("--------", currentValue + "");
                        // 根据比例更改目标view的宽度
                        ViewGroup.LayoutParams params = mImageObject.getLayoutParams();

                        // 设置布局宽高属性
                        mImageObject.getLayoutParams().width = currentValue;
                        mImageObject.getLayoutParams().height = currentValue;
//                        mImageObject.setLayoutParams(mImageObject.getLayoutParams());
                        mImageObject.requestLayout();// 重新计算布局属性
                    }
                });
                valueAnimator.start();
                break;
            case R.id.object_object_anim:

                // 将目标view进行包装
                ViewWrapper wrapper = new ViewWrapper(mImageObject);
                // 将xml转化为ObjectAnimator对象
                ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.object_animator);
                // 设置动画的目标对象为包装后的view
                objectAnimator.setTarget(wrapper);
                // 启动动画
                objectAnimator.start();
                break;
            case R.id.set_object_anim:
                // 将目标view进行包装
                ViewWrapper wrapper1 = new ViewWrapper(mImageObject);
                // 将xml转化为ObjectAnimator对象
                AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
                // 设置动画的目标对象为包装后的view
                animatorSet.setTarget(wrapper1);
                // 启动动画
                animatorSet.start();
                break;
            case R.id.rotate_property_anim:
                ObjectAnimator//
                        .ofFloat(mImagePorperty, "rotationX", 0.0F, 360.0F)//
                        .setDuration(1500)//
                        .start();
                break;
            case R.id.alsc_property_anim:

                ObjectAnimator anim = ObjectAnimator//
                        .ofFloat(mImagePorperty, "alsc", 1.0F, 0.0F)//
                        .setDuration(1500);//
                anim.start();
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float cVal = (Float) animation.getAnimatedValue();
                        mImagePorperty.setAlpha(cVal);
                        mImagePorperty.setScaleX(cVal);
                        mImagePorperty.setScaleY(cVal);
                    }
                });
                break;

            case R.id.values_holder_property:
                // 透明度从1F-0F-1F
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                        0f, 1f);

                // x轴的缩放从1F-0F-1F
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                        0, 1f);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                        0, 1f);
                ObjectAnimator.ofPropertyValuesHolder(mImagePorperty, pvhX, pvhY, pvhZ).setDuration(2500).start();
                break;
            case R.id.vertical_property_anim:

//                ValueAnimator animator = ValueAnimator.ofFloat(0, 300
//                        - mImageSport.getHeight());
//
//                animator.setTarget(mImageSport);
//                animator.setDuration(1000).start();
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
//
//                        float value = (float) valueAnimator.getAnimatedValue();
//
//                        mImageSport.setTranslationY(value * 2);
//                    }
//                });
                playWithAfter();
                break;
            case R.id.pwx_property_anim:

                ValueAnimator value = new ValueAnimator();
                value.setDuration(3000);
                value.setObjectValues(new PointF(0, 0));
                value.setInterpolator(new LinearInterpolator());
                value.setEvaluator(new TypeEvaluator<PointF>() {
                    // fraction = t / duration
                    @Override
                    public PointF evaluate(float fraction, PointF startValue,
                                           PointF endValue) {
                        // x方向200px/s ，则y方向0.5 * 10 * t
                        PointF point = new PointF();
                        point.x = 200 * fraction * 3;
                        point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                        return point;
                    }
                });

                value.start();
                value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF point = (PointF) animation.getAnimatedValue();
                        mImageSport.setX(point.x);
                        mImageSport.setY(point.y);

                    }
                });


                playTogether();
                break;
        }
    }


    // 多个动画一起执行
    public void playTogether() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mImageSport, "scaleX",
                1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mImageSport, "scaleY",
                1.0f, 2f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());// 匀速放大
        //两个（也可以更多）动画同时执行
        animSet.playTogether(anim1, anim2);
        animSet.start();
    }


    // 设置执行顺序
    public void playWithAfter() {
        float cx = mImageSport.getX();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mImageSport, "scaleX",
                1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mImageSport, "scaleY",
                1.0f, 2f);
        // 在X轴方向上移动
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mImageSport,
                "x", cx, 0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mImageSport,
                "x", cx);
        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim4).after(anim3);
        animSet.setDuration(2000);
        animSet.start();
    }
}
