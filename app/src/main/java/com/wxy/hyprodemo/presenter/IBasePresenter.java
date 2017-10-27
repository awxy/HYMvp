package com.wxy.hyprodemo.presenter;

import com.wxy.hyprodemo.http.BaseSubscriber;
import com.wxy.hyprodemo.iView.IBaseView;

import java.util.ArrayList;
/*
*  Author: Wxy
*  Time:   2017/10/25   14:36
*  Description: This is IBasePresenter   
*/

public abstract class IBasePresenter<T extends IBaseView> {
    public ArrayList<BaseSubscriber> subscribers = new ArrayList<>();

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
    public  void release(){
        for(BaseSubscriber subscriber : subscribers){
            if(subscriber!=null){
                subscriber.unsubscribe();
            }
        }
    };

}
