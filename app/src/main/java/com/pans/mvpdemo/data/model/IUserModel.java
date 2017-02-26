package com.pans.mvpdemo.data.model;

import com.pans.mvpdemo.contract.LoginContract;

/***********
 * @author pans
 * @date 2016/7/19
 * @describ
 */
public interface IUserModel {

    void login(String username, String password, LoginContract.OnLoginListener loginListener);

}
