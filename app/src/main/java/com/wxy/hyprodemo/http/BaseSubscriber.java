package com.wxy.hyprodemo.http;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/10/27.
 */

public abstract  class BaseSubscriber<T extends Object> extends Subscriber<T> {

    //code 200  没有数据
    public abstract  void noData(String msg);
    //code 200
    public abstract  void onSuccess(T t);
    @Override
    public void onNext(T t) {
        if(t==null){
            onError(new Throwable("no errorCode"));
            this.unsubscribe();
            return;
        }
//        if(!t.isHaveData){
//            noData(t.Errormsg);
//        }else{
//            onSuccess(t);
//        }
    }

    @Override
    public void onCompleted() {
    }

}
