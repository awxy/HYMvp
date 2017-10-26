package com.wxy.hyprodemo.presenter;

import com.wxy.hyprodemo.iView.IBaseView;

import rx.Subscription;
/*
*  Author: Wxy
*  Time:   2017/10/25   14:36
*  Description: This is IBasePresenter   
*/

public abstract class IBasePresenter<T extends IBaseView> {

    protected Subscription mSubscription;
    protected T mIView;

    public IBasePresenter(T iView) {
        mIView = iView;
    }

    public void init(){
        mIView.initView();
    }

    /**
     * Presenter释放资源
     */
    public abstract void release();

}
