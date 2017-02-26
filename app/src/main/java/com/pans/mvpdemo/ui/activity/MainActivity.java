package com.pans.mvpdemo.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.pans.mvpdemo.R;
import com.pans.mvpdemo.base.BaseActivity;
import com.pans.mvpdemo.ui.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

/***********
 * @author pans
 * @date 2016/7/14
 * @describ
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private int checkedMenuItemId;

    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;

    @Override
    protected void initStatusBar() {
        // 安卓手机版本5.0以上设置,在style设置过就不需要再设置了
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            // 设置透明状态栏,这样才能让 ContentView 向上
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 设置状态栏颜色
            window.setStatusBarColor(getResources().getColor(android.R.color.holo_red_light));
            ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
            if (mContentView != null) {
                View mChildView = mContentView.getChildAt(0);
                if (mChildView != null) {
                    // 注意不是设置 ContentView 的 FitsSystemWindows,
                    // 而是设置 ContentView 的第一个子 View.使其为系统 View 预留空间.
                    ViewCompat.setFitsSystemWindows(mChildView, true);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {

        mDrawerLayout = ((DrawerLayout) findViewById(R.id.main_drawer_layout));
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_nav_view);
        if (navigationView != null) {
            initDrawerContent(navigationView);
        }

        // 设置actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("首页");
            // 设置左边的图标
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.main_fab_add_task);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSnackBar("hhh");
                }
            });
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tabLayout);

        /*MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();

            addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.contentFrame);
        }*/

        ArrayList<String> list = new ArrayList<>();
        list.add("Tab1");
        list.add("Tab2");
        list.add("Tab3");
        list.add("Tab4");
        list.add("Tab5");
        list.add("Tab6");
        list.add("Tab7");
        list.add("Tab8");
        mViewPager = ((ViewPager) findViewById(R.id.main_viewPager));
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), list));
        if (tabLayout != null) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.setupWithViewPager(mViewPager);
        }

    }

    /**
     * 初始化侧拉菜单
     *
     * @param navigationView 侧拉菜单
     */
    private void initDrawerContent(final NavigationView navigationView) {

        // 添加侧拉菜单的头部,设置头布局的控件
//        LinearLayout headerView = (LinearLayout) navigationView.inflateHeaderView(R.layout.nav_header);
        LinearLayout headerView = (LinearLayout) navigationView.getHeaderView(0);
        headerView.findViewById(R.id.main_nav_btn_login).setOnClickListener(this);
        // 条目的点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.list_navigation_menu_item:
                        // Do nothing, we're already on that screen
                        break;
                    case R.id.statistics_navigation_menu_item:
                        /*Intent intent =
                                new Intent(TasksActivity.this, StatisticsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);*/
                        break;
                    default:
                        break;
                }
                if (checkedMenuItemId != 0) {
                    navigationView.getMenu().findItem(checkedMenuItemId).setChecked(false);
                }
                // Close the navigation drawer when an item is selected.
                item.setChecked(true);  // 根据条目的check条件设置条目的显示状态
                checkedMenuItemId = item.getItemId();
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }

    /**
     * 设置actionbar的条目
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        // 点击"返回"按钮时触发的逻辑
        super.onBackPressed();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_nav_btn_login:
                presentController(LoginActivity.class);
                mDrawerLayout.closeDrawers();
                break;
        }

    }

    /************
     * 跳转
     *
     * @param cls
     */
    public void presentController(Class cls, View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            super.presentController(cls);
            return;
        }

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.detail));
        startActivity(new Intent(this, DetailActivity.class), options.toBundle());

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<String> mData;

        public ViewPagerAdapter(FragmentManager fm, ArrayList<String> list) {
            super(fm);
            mData = list;
        }

        @Override
        public Fragment getItem(int position) {
            return MainFragment.newInstance();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mData.get(position);
        }
    }

}
