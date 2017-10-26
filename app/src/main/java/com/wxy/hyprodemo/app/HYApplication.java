package com.wxy.hyprodemo.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.wxy.hyprodemo.utils.LogUtils;

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



    }
}
