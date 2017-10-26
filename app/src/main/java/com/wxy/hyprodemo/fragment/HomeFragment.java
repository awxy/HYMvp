package com.wxy.hyprodemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wxy.hyprodemo.R;
import com.wxy.hyprodemo.base.HYFragmentBase;
import com.wxy.hyprodemo.bean.HomePageInfo;
import com.wxy.hyprodemo.iView.HomeView;
import com.wxy.hyprodemo.presenter.HomePresenter;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/10/26.
 */

public class HomeFragment extends HYFragmentBase<HomePresenter> implements HomeView {
    @Bind(R.id.rv)
    RecyclerView recyclerView;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    private static String DICTID = "mDictid";
    private String mDictid;
    private ArrayList mDatas;
    private BaseQuickAdapter<HomePageInfo.ResultBean.ZhiboBean, BaseViewHolder> mAdapter;

    public static HomeFragment newInstance(String dictid) {
        Bundle bundle = new Bundle();
        bundle.putString(DICTID, dictid);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    protected void getExtraArguments() {
        Bundle arguments = getArguments();
        mDictid = arguments.getString(DICTID);
    }

    @Override
    protected void onVisible() {
        super.onVisible();
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter(this);
        mPresenter.init();
    }

    @Override
    public void initView() {
        mDatas = new ArrayList<HomePageInfo.ResultBean.ZhiboBean>();
        mAdapter = new BaseQuickAdapter<HomePageInfo.ResultBean.ZhiboBean, BaseViewHolder>(R.layout.item_home,mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, HomePageInfo.ResultBean.ZhiboBean item) {
                helper.setText(R.id.tv_name,item.getClassifyname());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(mAdapter);
        //首页请求
        mPresenter.getHomePageData(mDictid);
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccess(HomePageInfo info) {
        mAdapter.addData(info.getResult().getZhibo());
        mAdapter.notifyDataSetChanged();
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        pbLoading.setVisibility(View.GONE);
    }
}
