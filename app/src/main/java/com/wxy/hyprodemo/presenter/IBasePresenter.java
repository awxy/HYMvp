package com.wxy.hyprodemo.presenter;

import com.wxy.hyprodemo.iView.IBaseView;

import rx.subscriptions.CompositeSubscription;
/*
*  Author: Wxy
*  Time:   2017/10/25   14:36
*  Description: This is IBasePresenter   
*/

public abstract class IBasePresenter<T extends IBaseView> {
//    public CopyOnWriteArrayList<BaseSubscriber> subscribers = new CopyOnWriteArrayList<>();
protected CompositeSubscription subscribers;//管理所有的订阅
    protected T mIView;

    public IBasePresenter(T iView) {
        mIView = iView;
        this.subscribers = new CompositeSubscription();
    }

    public void init() {
        mIView.initView();
    }

    /**
     * Presenter释放资源
     */
    public void release() {
//        for (BaseSubscriber subscriber : subscribers) {
//            if (subscriber != null) {
//                subscriber.unsubscribe();
//            }
//        }
        if(subscribers!=null)
            subscribers.unsubscribe();
        if (mIView != null)
            mIView = null;
    }

    ;

}
