package com.pans.mvpdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.pans.mvpdemo.R;
import com.pans.mvpdemo.base.BaseActivity;
import com.pans.mvpdemo.data.model.UserModel;
import com.pans.mvpdemo.presenter.UserLoginPresenter;
import com.pans.mvpdemo.ui.fragment.LoginFragment;

/***********
 * @author pans
 * @date 2016/7/19
 * @describ
 */
public class LoginActivity extends BaseActivity {

    private UserLoginPresenter mUserLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("登录");
//            actionBar.setHomeActionContentDescription("登录");
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // 创建fragment
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();

            // 同一个页面,根据传过来的intent显示不同的设置和数据
            /*if (getIntent().hasExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID)) {
                taskId = getIntent().getStringExtra(
                        AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID);
                actionBar.setTitle(R.string.edit_task);
                Bundle bundle = new Bundle();
                bundle.putString(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID, taskId);
                addEditTaskFragment.setArguments(bundle);
            } else {
                actionBar.setTitle(R.string.add_task);
            }*/

            addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.contentFrame);
        }

        // 创建presenter并把model和fragment(view)传进去
        // 在presenter的构造方法中调用fragment的setPresenter使得fragment获取presenter对象
        mUserLoginPresenter = new UserLoginPresenter(new UserModel(), loginFragment);

    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    // 设置actionbar点击事件
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /**
     * 左上角的actionbar按钮点击事件
     *
     * @return 是否往下传递
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
