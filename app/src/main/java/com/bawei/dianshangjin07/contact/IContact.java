package com.bawei.dianshangjin07.contact;

import com.bawei.dianshangjin07.bean.CommodityData;
import com.bawei.dianshangjin07.bean.DataBean;
import com.bawei.dianshangjin07.bean.LoginInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 契约类
 */
public interface IContact {
    //视图
    interface IView<T> {
        void onViewSuccess(T result,String message);
        void onViewFail(DataBean dataBean);
    }
    //请求
    interface IRequest {
        //登录请求
        @POST("small/user/v1/login")
        @FormUrlEncoded
        Observable<DataBean<LoginInfo>> login(@Field("phone") String phone,@Field("pwd") String pwd);
        //搜索请求
        @GET("small/commodity/v1/findCommodityByKeyword")
        Observable<DataBean<List<CommodityData>>> findCommodityByKeyword(@Query("keyword") String keyword,@Query("page") int page,@Query("count") int count);
    }
}
