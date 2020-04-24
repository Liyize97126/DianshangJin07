package com.bawei.dianshangjin07.presenter;

import com.bawei.dianshangjin07.base.BasePresenter;
import com.bawei.dianshangjin07.contact.IContact;

import io.reactivex.Observable;

/**
 * 搜索Presenter
 */
public class FindCommodityPresenter extends BasePresenter {
    //方法实现
    public FindCommodityPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected Observable getObservable(Object... args) {
        return getiRequest().findCommodityByKeyword((String) args[0],(int) args[1],(int) args[2]);
    }
}
