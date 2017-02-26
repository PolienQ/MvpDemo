package com.pans.mvpdemo.contract;

import com.pans.mvpdemo.base.BasePresenter;
import com.pans.mvpdemo.base.BaseView;
import com.pans.mvpdemo.data.bean.UserBean;

/***********
 * @author pans
 * @date 2016/7/21
 * @describ
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        String getUserName();

        String getPassword();

        void clearUserName();

        void clearPassword();

        void showLoading();

        void hideLoading();

        void toMainActivity(UserBean userBean);

        void showFailedError();

        void checkMail();

        void setUserNameError();

        void setPasswordError();

        void clearTextInputLayoutError();

    }

    interface Presenter extends BasePresenter {

        void login();

        void clear();
    }

    interface OnLoginListener {

        void loginSuccess(UserBean userBean);

        void loginFailed();

    }
}
