package com.wxy.hyprodemo.activity;

import android.view.View;

import com.wxy.hyprodemo.R;
import com.wxy.hyprodemo.base.BaseActivity;
import com.wxy.hyprodemo.iView.IBaseView;
import com.wxy.hyprodemo.presenter.ReleasePresenter;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/30.
 */

public class ReleaseActivity extends BaseActivity<ReleasePresenter> implements IBaseView{

    @Override
    protected void initPresenter() {
       mPresenter = new ReleasePresenter(this);
    }

    @Override
    protected int getContentId() {
        return R.layout.release_activity;
    }

    @Override
    public void initView() {
//        HYNetManager.getInstance().getPm("18272939309", "asd123456")
//                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(f->{
//
//                });

    }
    @OnClick(R.id.btn_start)
    public void onClick(View v){
        mPresenter.doIng();
    }
}
