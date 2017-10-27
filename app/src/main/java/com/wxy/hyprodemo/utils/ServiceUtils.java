package com.wxy.hyprodemo.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27.
 */

public class ServiceUtils {
    private static Context mContext ;

    private static void init(Context context) {
        if (mContext == null)
            mContext = context;
    }

    /**
     * 判断服务是否运行
     *
     * @param className 完整包名的服务类名
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isServiceRunning(Context context, final String className) {
        init(context);
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> info = activityManager.getRunningServices(0x7FFFFFFF);
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningServiceInfo aInfo : info) {
            if (className.equals(aInfo.service.getClassName())) return true;
        }
        return false;
    }

    /**
     * 启动服务
     *
     * @param cls 服务类
     */
    public static void startService(Context context,final Class<?> cls) {
        init(context);
        Intent intent = new Intent(mContext, cls);
        mContext.startService(intent);
    }
}
