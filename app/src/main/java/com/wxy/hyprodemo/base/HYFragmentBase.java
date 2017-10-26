package com.wxy.hyprodemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.wxy.hyprodemo.presenter.IBasePresenter;

import butterknife.ButterKnife;

/**
 * The base fragment in application.
 *
 * @author gufei
 * @version 1.0
 * @createDate 2015-10-16
 * @lastUpdate 2015-10-16
 */
public abstract class HYFragmentBase<T extends IBasePresenter> extends RxFragment {

    private boolean isVisible;
    protected T mPresenter;
    protected Context mContext;
    protected Activity mActivity;
    private boolean isPrepared;
    protected View mRootView;


    protected abstract void getExtraArguments();

    protected abstract int getContentViewId();

    protected abstract void initPresenter();

    protected void onInvisible() {
    }

    protected void onVisible() {
    }

    /*
      懒加载
     */
    protected void lazyLoad() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getActivity();
        mActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtraArguments();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(),
                container, false);
        ButterKnife.bind(this, mRootView);
        initPresenter();
        isPrepared = true;
        if (isPrepared && isVisible)
            lazyLoad();
        return mRootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);//解绑
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    protected void go2Activity(Intent intent) {
        startActivity(intent);
    }


}
