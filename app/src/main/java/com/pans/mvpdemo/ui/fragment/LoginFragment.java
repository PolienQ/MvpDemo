package com.pans.mvpdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pans.mvpdemo.R;
import com.pans.mvpdemo.base.BaseFragment;
import com.pans.mvpdemo.contract.LoginContract;
import com.pans.mvpdemo.data.bean.UserBean;

import java.util.regex.Pattern;

/***********
 * @author pans
 * @date 2016/7/20
 * @describ
 */
public class LoginFragment extends BaseFragment implements LoginContract.View, View.OnClickListener {

    private LoginContract.Presenter mPresenter;
    private TextInputEditText mEtUserName;
    private TextInputEditText mEtPassword;
    private TextInputLayout mUserNameWrapper;
    private TextInputLayout mPasswordWrapper;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 设置activity的UI
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.login_done);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.saveTask(mTitle.getText().toString(), mDescription.getText().toString());
                mPresenter.login();
//                showToast("登录");
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        mUserNameWrapper = ((TextInputLayout) root.findViewById(R.id.login_userName_wrapper));
        mEtUserName = (TextInputEditText) root.findViewById(R.id.login_et_userName);
        mPasswordWrapper = ((TextInputLayout) root.findViewById(R.id.login_password_wrapper));
        mEtPassword = (TextInputEditText) root.findViewById(R.id.login_et_password);
        root.findViewById(R.id.login_btn_login).setOnClickListener(this);
        root.findViewById(R.id.login_btn_clear).setOnClickListener(this);

        setHasOptionsMenu(true);    // 设置为true后会回调onCreateOptionMenu方法
        setRetainInstance(true);    // 异常摧毁时跳过fragment生命周期的onCreate和onDestroy方法
        return root;
    }

    @Override
    public String getUserName() {
        return mEtUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        if (getUserName() != null) {
            mEtUserName.setText("");
        }
    }

    @Override
    public void clearPassword() {
        if (getPassword() != null) {
            mEtPassword.setText("");
        }
    }

    @Override
    public void showLoading() {
        showToast("打开Loading框");
    }

    @Override
    public void hideLoading() {
        showToast("隐藏Loading框");
    }

    @Override
    public void toMainActivity(UserBean userBean) {
        showToast("跳转到首页");
    }

    @Override
    public void showFailedError() {
        showToast("失败提示");
    }

    @Override
    public void checkMail() {
        String patten = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
        boolean mail = Pattern.compile(patten).matcher("mail").matches();
    }

    @Override
    public void setUserNameError() {
//        mUserNameWrapper.setError("用户名为空");

        showSnackBar("用户名不能为空", "点击", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("SnackBar按钮被点击");
            }
        });
    }

    @Override
    public void setPasswordError() {
//        mPasswordWrapper.setError("密码为空");

        showSnackBar("密码不能为空");
    }

    @Override
    public void clearTextInputLayoutError() {
        mUserNameWrapper.setErrorEnabled(false);
        mPasswordWrapper.setErrorEnabled(false);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_btn_login:
                mPresenter.login();
                break;
            case R.id.login_btn_clear:
                mPresenter.clear();
                break;
        }

    }


}
