package com.wxy.hyprodemo.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.squareup.leakcanary.LeakCanary;
import com.wxy.hyprodemo.service.MyService;
import com.wxy.hyprodemo.utils.LogUtils;
import com.wxy.hyprodemo.utils.ServiceUtils;

import org.litepal.LitePal;

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
        LitePal.initialize(this);
        //Logger setting
        LogUtils.init();
        startObservice();
        ServiceUtils.startService(this,MyService.class);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
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
