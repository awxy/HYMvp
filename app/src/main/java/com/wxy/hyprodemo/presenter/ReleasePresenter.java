package com.wxy.hyprodemo.presenter;

import com.wxy.hyprodemo.bean.PMInfo;
import com.wxy.hyprodemo.http.BaseSubscriber;
import com.wxy.hyprodemo.http.HYNetManager;
import com.wxy.hyprodemo.iView.IBaseView;
import com.wxy.hyprodemo.utils.LogUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/30.
 */

public class ReleasePresenter extends IBasePresenter {
    public ReleasePresenter(IBaseView iView) {
        super(iView);
    }

    public void doIng() {
        subscribers.add(
                HYNetManager.getInstance().getPm("18272939309", "asd123456")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseSubscriber<PMInfo>() {
                            @Override
                            public void noData(String msg) {
                                LogUtils.e("data", "nodata");
                            }

                            @Override
                            public void onSuccess(PMInfo info) {
                                LogUtils.e("data", "success");
                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtils.e("data", "success");
                            }
                        })
        );
    }
}
