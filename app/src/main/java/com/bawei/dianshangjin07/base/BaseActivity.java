package com.bawei.dianshangjin07.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 页面基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    //定义
    private BasePresenter basePresenter;
    private Unbinder bind;
    //封装
    public BasePresenter getBasePresenter() {
        return basePresenter;
    }
    //创建视图
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        basePresenter = initPresenter();
        initView();
    }
    //销毁视图
    @Override
    protected void onDestroy() {
        super.onDestroy();
        initDestroy();
        bind.unbind();
        if(basePresenter != null){
            basePresenter.destroy();
            basePresenter = null;
        }
    }
    //方法封装
    protected abstract int getLayoutId();
    protected abstract BasePresenter initPresenter();
    protected abstract void initView();
    protected abstract void initDestroy();
}
