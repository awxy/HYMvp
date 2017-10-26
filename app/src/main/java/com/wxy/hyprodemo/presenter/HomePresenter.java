package com.wxy.hyprodemo.presenter;

import com.wxy.hyprodemo.http.HYNetManager;
import com.wxy.hyprodemo.iView.HomeView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
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
public class HomePresenter extends IBasePresenter<HomeView> {

    public HomePresenter(HomeView iView) {
        super(iView);
    }
    public void getHomePageData(String dictid ){
        mIView.showLoading();
        Observable.interval(2, TimeUnit.SECONDS).subscribe(f->{
            HYNetManager.getInstance().getHomePageData(dictid,"1","0","2")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(info->{
                        mIView.showSuccess(info);
                    },error->{
                        mIView.showError(error.getMessage());
                    });
        });

    }
    @Override
    public void release() {

    }
}