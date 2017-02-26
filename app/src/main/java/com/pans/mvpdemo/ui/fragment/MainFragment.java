package com.pans.mvpdemo.ui.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pans.mvpdemo.R;
import com.pans.mvpdemo.base.BaseFragment;
import com.pans.mvpdemo.base.MyApplication;
import com.pans.mvpdemo.ui.adatper.MainAdapter;

import java.util.ArrayList;

/***********
 * @author pans
 * @date 2016/7/21
 * @describ
 */
public class MainFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTvNoData;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = ((RecyclerView) root.findViewById(R.id.main_recyclerView));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
//        mRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout = ((SwipeRefreshLayout) root.findViewById(R.id.main_refresh));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MyApplication.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showSnackBar("刷新成功");
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        mTvNoData = ((TextView) root.findViewById(R.id.main_tv_noData));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item4");
        list.add("item5");
        list.add("item6");
        list.add("item7");
        if (list.size() != 0) {
            mTvNoData.setVisibility(View.GONE);
        }
        mRecyclerView.setAdapter(new MainAdapter(this, list));

    }
}
