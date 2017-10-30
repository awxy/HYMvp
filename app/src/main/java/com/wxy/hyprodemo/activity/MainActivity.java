package com.wxy.hyprodemo.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.wxy.hyprodemo.R;
import com.wxy.hyprodemo.base.BaseActivity;
import com.wxy.hyprodemo.db.News;
import com.wxy.hyprodemo.fragment.HomeFragment;
import com.wxy.hyprodemo.iView.IBaseView;
import com.wxy.hyprodemo.utils.LogUtils;

import org.litepal.LitePal;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IBaseView {


    @Bind(R.id.btn_db)
    Button btnDb;
    @Bind(R.id.fl_content)
    FrameLayout flContent;
    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;


    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }

    }


    @Override
    protected void initPresenter() {
        //如果不需要Presenter  return null
        //手动调用 initView()
        initView();
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn_post)
    public void onClick(View v) {
        Intent intent = new Intent(this, ReleaseActivity.class);
        startActivity(intent);
    }

    @Override
    public void initView() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
//        hideFragments(transaction);
        if (mHomeFragment == null) {
            mHomeFragment = HomeFragment.newInstance("1");
            transaction.add(R.id.fl_content, mHomeFragment);
            transaction.show(mHomeFragment);
        }
        transaction.commitAllowingStateLoss();
        SQLiteDatabase database = LitePal.getDatabase();
        News news = new News("title", "body");
        List<News> all = news.findAll(News.class);
        for(News s : all){
            LogUtils.e("sql",s.toString());
        }

//        news.save();
    }


}
