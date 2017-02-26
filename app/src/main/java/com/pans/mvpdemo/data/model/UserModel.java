package com.pans.mvpdemo.data.model;

import com.pans.mvpdemo.contract.LoginContract;
import com.pans.mvpdemo.data.bean.UserBean;

/***********
 * @author pans
 * @date 2016/7/19
 * @describ
 */
public class UserModel implements IUserModel {

    @Override
    public void login(final String username, final String password, final LoginContract.OnLoginListener loginListener) {

        //模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("zhy".equals(username) && "123".equals(password)) {
                    UserBean user = new UserBean();
                    user.setUsername(username);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFailed();
                }
            }
        }.start();

    }

}
