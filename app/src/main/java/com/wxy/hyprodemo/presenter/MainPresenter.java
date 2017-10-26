package com.wxy.hyprodemo.presenter;

import com.wxy.hyprodemo.http.HYNetManager;
import com.wxy.hyprodemo.iView.MainView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/25.
 */
/*
*  Author: Wxy
*  Time:   2017/10/25   15:30
*  Description: This is MainPresenter   
*/
public class MainPresenter extends IBasePresenter<MainView> {

    public MainPresenter(MainView iView) {
        super(iView);
    }
    public void getMainPageData(){
        HYNetManager.getInstance().getMainPageData("1","1","0","2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info->{
                    mIView.showSuccess(info);
                },error->{
                    mIView.showError(error.getMessage());
                });
    }
    @Override
    public void release() {

    }
}
