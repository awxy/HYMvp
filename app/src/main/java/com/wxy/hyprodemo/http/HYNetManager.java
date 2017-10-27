package com.wxy.hyprodemo.http;

import com.wxy.hyprodemo.bean.HomePageInfo;
import com.wxy.hyprodemo.bean.MainPageInfo;
import com.wxy.hyprodemo.bean.PMInfo;

import java.io.Serializable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Net api
 * <p>
 * Created by Chang.Xiao on 2016/5/30.
 *
 * @version 1.0
 */
public final class HYNetManager implements Serializable {
    private static HYNetManager sInstance;
    private static HYNetApi sApi;
    public interface HYNetApi {
        @POST("Api/TAdvert/gethomeAdvert")
        @FormUrlEncoded
        Observable<MainPageInfo> getMainPageData(@Field("dictid")String dictid,@Field("p")String p,@Field("classifyid")String classifyid,@Field("alocation")String alocation);

        @POST("Api/TAdvert/gethomeAdvert")
        @FormUrlEncoded
        Observable<HomePageInfo> getHomePageData(@Field("dictid")String dictid,@Field("p")String p,@Field("classifyid")String classifyid,@Field("alocation")String alocation);

        @POST("interface/v1.5/login")
        @FormUrlEncoded
        Observable<PMInfo> getPm(@Field("PhoneNumber")String phoneNumber, @Field("PassWord")String PassWord);


    }
    public Observable<MainPageInfo> getMainPageData(String dictid,String p ,String classifyid,String alocation){
        return sApi.getMainPageData(dictid,p,classifyid,alocation);
    }
    public Observable<HomePageInfo> getHomePageData(String dictid, String p , String classifyid, String alocation){
        return sApi.getHomePageData(dictid,p,classifyid,alocation);
    }
    public Observable<PMInfo> getPm(String phoneNumber, String passWord){
        return sApi.getPm(phoneNumber,passWord);
    }
    public static HYNetManager getInstance(){
        if(sInstance == null){
            sInstance = new HYNetManager();
        }
        sApi = HYRetrofit.getNetApiInstance();
        return sInstance;
    }
}
