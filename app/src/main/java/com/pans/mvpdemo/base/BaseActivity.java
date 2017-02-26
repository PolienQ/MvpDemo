package com.pans.mvpdemo.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.pans.mvpdemo.util.LogUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 没有标题栏
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/

        //沉浸式状态栏相关代码  必须写在setContentView()之前 5.0之后
        /*getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }*/

        initStatusBar();

        super.onCreate(savedInstanceState);
        MyApplication.getInstance().activityManager.add(this);

    }

    protected void initStatusBar() {
        // 安卓手机版本5.0以上设置,在style设置过就不需要再设置了
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            // 设置透明状态栏,这样才能让 ContentView 向上
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 设置状态栏颜色
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
            if (mContentView != null) {
                View mChildView = mContentView.getChildAt(0);
                if (mChildView != null) {
                    // 注意不是设置 ContentView 的 FitsSystemWindows,
                    // 而是设置 ContentView 的第一个子 View.使其不为系统 View 预留空间.
                    ViewCompat.setFitsSystemWindows(mChildView, true);
                }
            }
        }*/
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().activityManager.remove(this);

    }

    /***********
     * 日志打印
     * 需要 设置自定义的 TAG 时，重写该方法就可以了
     *
     * @param msg
     */
    public void log(String msg) {
        LogUtil.d(msg);
    }

    /*******
     * 把像素转换成当前屏幕的dip值
     *
     * @param size 像素值
     * @return
     */
    public int pxToDip(int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics());
    }

    /*******
     * 把dip转换成当前屏幕的像素值
     *
     * @param dp
     * @return
     */
    private int dipToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    // 退出应用
    public void exit() {
        // 关闭栈内所有的Activity
        for (Activity activity : MyApplication.getInstance().activityManager) {
            activity.finish();
        }

        // 释放相关的引用资源，比如单例类

    }

    /************
     * 跳转
     *
     * @param cls
     */
    public void presentController(Class cls) {
        presentController(cls, null);
    }

    /************
     * 跳转
     *
     * @param cls
     * @param data
     */
    public void presentController(Class cls, Intent data) {
        Intent intent = new Intent(this, cls);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivity(intent);

        // 第一个参数：进入的
        // 第二个参数：退出的
//        this.overridePendingTransition(R.anim.inside_translate, R.anim.outside_translate);
    }


    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 添加fragment到activity中
     *
     * @param fragmentManager fragment管理器
     * @param fragment        fragment
     * @param frameId         放fragment的FrameLayout布局的id
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
//        checkNotNull(fragmentManager);
//        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public void showSnackBar(String msg) {
        showSnackBar(msg, null, null);
    }

    public void showSnackBar(String msg, String actionMsg, View.OnClickListener listener) {
        View currentFocus = getCurrentFocus();
        if (currentFocus == null) {
            showToast(msg);
        } else {
            Snackbar.make(currentFocus, msg, Snackbar.LENGTH_LONG).setAction(actionMsg, listener).show();
        }
    }
}
