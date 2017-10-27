package com.wxy.hyprodemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.wxy.hyprodemo.utils.LogUtils;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/27.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("Service","start");
        Observable.interval(10, java.util.concurrent.TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(aLong -> {
                    LogUtils.e("Service","socket");
                });
    }
}
