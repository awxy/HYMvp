package com.wxy.hyprodemo.iView;

/**
 * Created by Administrator on 2017/10/25.
 */
/*
*  Author: Wxy
*  Time:   2017/10/25   15:20
*  Description: This is HYMainActivity   
*/
public interface HomeView extends IBaseView{
    void showLoading();
    void showSuccess(Object info);
    void showError(String msg);
}
