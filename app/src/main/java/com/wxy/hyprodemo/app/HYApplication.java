package com.wxy.hyprodemo.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.wxy.hyprodemo.service.MyService;
import com.wxy.hyprodemo.utils.LogUtils;
import com.wxy.hyprodemo.utils.ServiceUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/25.
 */
/*
*  Author: Wxy
*  Time:   2017/10/25   15:53
*  Description: This is HYApplication
*/
public class HYApplication extends Application {

    public static AndroidLogAdapter mLogAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        //Logger setting
        LogUtils.init();
        startObservice();
        ServiceUtils.startService(this,MyService.class);

    }

    private void startObservice() {
        Observable.interval(10, java.util.concurrent.TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if(!ServiceUtils.isServiceRunning(this, getPackageName()+".service.MyService")){
                        ServiceUtils.startService(this,MyService.class);
                        LogUtils.e("running",false+"");
                    }
                });
    }
}
