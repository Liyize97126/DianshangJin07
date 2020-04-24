package com.bawei.dianshangjin07.presenter;

import com.bawei.dianshangjin07.base.BasePresenter;
import com.bawei.dianshangjin07.contact.IContact;

import io.reactivex.Observable;

/**
 * 登录Presenter
 */
public class LoginPresenter extends BasePresenter {
    //方法封装
    public LoginPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected Observable getObservable(Object... args) {
        return getiRequest().login((String) args[0],(String) args[1]);
    }
}
