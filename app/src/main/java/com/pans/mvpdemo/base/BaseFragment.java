package com.pans.mvpdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

public class BaseFragment extends Fragment {

    protected BaseActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (BaseActivity) this.getActivity();
    }

    /******
     * 显示提示框
     *
     * @param msg
     */
    public void showToast(String msg) {
        activity.showToast(msg);
    }

    /***********
     * 日志打印
     * 需要 设置自定义的 TAG 时，重写该方法就可以了
     *
     * @param msg
     */
    public void log(String msg) {
        activity.log(msg);
    }

    /*******
     * 把像素转换成当前屏幕的dip值
     *
     * @param size 像素值
     * @return
     */
    public int pxToDip(int size) {
        return activity.pxToDip(size);
    }

    public void showSnackBar(String msg) {
        activity.showSnackBar(msg, null, null);
    }

    public void showSnackBar(String msg, String actionMsg, View.OnClickListener listener) {
        activity.showSnackBar(msg, actionMsg, listener);
    }

}
