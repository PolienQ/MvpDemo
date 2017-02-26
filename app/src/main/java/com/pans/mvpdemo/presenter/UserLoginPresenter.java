package com.pans.mvpdemo.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.pans.mvpdemo.contract.LoginContract;
import com.pans.mvpdemo.data.bean.UserBean;
import com.pans.mvpdemo.data.model.UserModel;

/***********
 * @author pans
 * @date 2016/7/19
 * @describ
 */
public class UserLoginPresenter implements LoginContract.Presenter {

    private UserModel userModel;
    private LoginContract.View userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(UserModel userModel, LoginContract.View userLoginView) {
        this.userModel = userModel;
        this.userLoginView = userLoginView;

        start();
        userLoginView.setPresenter(this);
    }

    public void login() {

        userLoginView.clearTextInputLayoutError();
        String userName = userLoginView.getUserName();
        if (TextUtils.isEmpty(userName)) {
            userLoginView.setUserNameError();
            return;
        }
        String password = userLoginView.getPassword();
        if (TextUtils.isEmpty(password)) {
            userLoginView.setPasswordError();
            return;
        }

        userLoginView.showLoading();

        userModel.login(userName, password, new LoginContract.OnLoginListener() {
            @Override
            public void loginSuccess(final UserBean userBean) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(userBean);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();

        userLoginView.clearTextInputLayoutError();
    }

    @Override
    public void start() {
        // 初始化
    }
}
