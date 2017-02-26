package com.pans.mvpdemo.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.pans.mvpdemo.R;
import com.pans.mvpdemo.base.BaseActivity;

/***********
 * @author pans
 * @date 2016/7/25
 * @describ
 */
public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置共享元素 跳转动画
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//            getWindow().setExitTransition(new Explode());
            /* 调用ViewAnimationUtils下的createCircularReveal方法，方法返回一个Animator对象，
            这个Animator可以设置动画响应属性，调用start方法开始播放动画，
            可以看出CircularReveal是一个属性动画，动画设置同属性动画的即可。
            Transition transition = getWindow().getSharedElementEnterTransition();
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    //过渡结束 执行想要的动画或者操作.
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });*/
        }
        setContentView(R.layout.activity_detail);

        init();
    }

    private void init() {

        setSupportActionBar((Toolbar) findViewById(R.id.detail_toolBar));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("折叠详情页");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
