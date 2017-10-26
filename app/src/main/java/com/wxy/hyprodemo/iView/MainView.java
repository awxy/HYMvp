package com.wxy.hyprodemo.iView;

import com.wxy.hyprodemo.bean.MainPageInfo;

/**
 * Created by Administrator on 2017/10/25.
 */
/*
*  Author: Wxy
*  Time:   2017/10/25   15:20
*  Description: This is HYMainActivity   
*/
public interface MainView extends IBaseView{
    void showLoading();
    void showSuccess(MainPageInfo info);
    void showError(String msg);
}
