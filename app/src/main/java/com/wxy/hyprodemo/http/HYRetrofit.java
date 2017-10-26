package com.wxy.hyprodemo.http;

import com.wxy.hyprodemo.BuildConfig;
import com.wxy.hyprodemo.utils.AppConfig;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * 网络请求对象
 * <p>
 * Created by Wxy
 *
 * @version 1.0
 */
public class HYRetrofit implements Serializable {

    // init初次尝试连接5s，失败后第二次尝试连接10s，第三次连接150s
    public static final long connectTimeout_5 = 5;
    public static final long connectTimeout_10 = 10;
    public static final long connectTimeout_150 = 4;
    // NOTE:后端设置超时时间2分半
//    public static final long readTimeout_150 = 150;

    private static HYRetrofit sInstance;
    private static Retrofit.Builder sBuilder;
    private static HYNetManager.HYNetApi sNetApi;

    private static OkHttpClient.Builder sOkHttpBuilder;
    private static OkHttpClient sClient;


    private HYRetrofit() {

    }

    public static HYRetrofit getInstance() {
        if (null == sInstance) {
            sInstance = new HYRetrofit();
        }
        return sInstance;
    }


    static {
        // OkHttp3
        sOkHttpBuilder = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            // print Log
            sOkHttpBuilder.addInterceptor(new LoggingInterceptor());
//            sOkHttpBuilder.addInterceptor(new MyInterceptor());
        }

        sOkHttpBuilder.retryOnConnectionFailure(false);
        sOkHttpBuilder.readTimeout(connectTimeout_150, TimeUnit.SECONDS);
        sOkHttpBuilder.connectTimeout(connectTimeout_150, TimeUnit.SECONDS);
        sClient = sOkHttpBuilder.build();

        sBuilder = new Retrofit.Builder();
    }

    /**
     * 设置连接超时时间
     *
     * @param timeOut
     */
    public void setConnectTimeout(long timeOut) {
        sOkHttpBuilder.connectTimeout(timeOut, TimeUnit.SECONDS);
    }

    public void setReadTimeout(long timeOut) {
        sOkHttpBuilder.readTimeout(timeOut, TimeUnit.SECONDS);
    }


    /**
     * Get NetApi instance
     *
     * @param serverUrl
     * @return
     */
    public static HYNetManager.HYNetApi getNetApiInstance(String serverUrl) {
        if (sNetApi == null) {
            synchronized (HYRetrofit.class) {
                if (sNetApi == null) {
                    Retrofit retrofit = sBuilder
                            .client(sClient)
                            .baseUrl(serverUrl + "/")
                            .addConverterFactory(new JsonConverterFactory())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    sNetApi = retrofit.create(HYNetManager.HYNetApi.class);
                }
            }
        }
        return sNetApi;
    }

    public static HYNetManager.HYNetApi getNetApiInstance() {
        return getNetApiInstance(AppConfig.SERVER_URL_INIT_1);
    }



}



