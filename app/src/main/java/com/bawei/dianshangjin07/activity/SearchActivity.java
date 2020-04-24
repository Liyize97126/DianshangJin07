package com.bawei.dianshangjin07.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshangjin07.R;
import com.bawei.dianshangjin07.adapter.ListDataAdapter;
import com.bawei.dianshangjin07.base.BaseActivity;
import com.bawei.dianshangjin07.bean.CommodityData;
import com.bawei.dianshangjin07.bean.DataBean;
import com.bawei.dianshangjin07.contact.IContact;
import com.bawei.dianshangjin07.dao.CommodityDataDao;
import com.bawei.dianshangjin07.dao.DaoMaster;
import com.bawei.dianshangjin07.dao.DaoSession;
import com.bawei.dianshangjin07.presenter.FindCommodityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索页面
 */
public class SearchActivity extends BaseActivity implements IContact.IView<List<CommodityData>> {
    //定义
    @BindView(R.id.edit_search_text)
    protected EditText editSearchText;
    @BindView(R.id.search_result_show)
    protected RecyclerView searchResultShow;
    @BindView(R.id.search_data_show)
    protected TextView searchDataShow;
    private ListDataAdapter listDataAdapter;
    private FindCommodityPresenter findCommodityPresenter;
    private CommodityDataDao commodityDataDao;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        //设置适配器
        searchResultShow.setLayoutManager(new GridLayoutManager(this,2));
        listDataAdapter = new ListDataAdapter();
        searchResultShow.setAdapter(listDataAdapter);
        //初始化Presenter
        findCommodityPresenter = new FindCommodityPresenter(this);
        //初始化数据库
        DaoSession daoSession = DaoMaster.newDevSession(this, CommodityDataDao.TABLENAME);
        commodityDataDao = daoSession.getCommodityDataDao();
        //查询
        List<CommodityData> commodityData = commodityDataDao.loadAll();
        //判断有无数据
        if(commodityData != null && commodityData.size() > 0){
            //有数据，直接展示
            String showInfo = commodityData.size() + "件商品";
            searchDataShow.setText("搜索到" + showInfo);
            //提示
            Toast.makeText(SearchActivity.this,"已加载缓存数据",Toast.LENGTH_LONG).show();
            //展示数据
            listDataAdapter.getList().addAll(commodityData);
            listDataAdapter.notifyDataSetChanged();
        }
    }
    @OnClick(R.id.submit_search)
    protected void search(){
        //获取文本
        String searchText = editSearchText.getText().toString();
        if(TextUtils.isEmpty(searchText)){
            //提示
            Toast.makeText(SearchActivity.this,"错误提示：搜索框不能为空！",Toast.LENGTH_LONG).show();
        } else {
            listDataAdapter.getList().clear();
            commodityDataDao.deleteAll();
            //请求
            findCommodityPresenter.request(searchText,1,100);
        }
    }
    @Override
    protected void initDestroy() {
        if(listDataAdapter != null){
            listDataAdapter.getList().clear();
            listDataAdapter.destroy();
            listDataAdapter = null;
        }
        if(findCommodityPresenter != null){
            findCommodityPresenter.destroy();
            findCommodityPresenter = null;
        }
    }
    @Override
    public void onViewSuccess(List<CommodityData> result, String message) {
        String showInfo = result.size() + "件商品";
        searchDataShow.setText("搜索到" + showInfo);
        //提示
        Toast.makeText(SearchActivity.this,message + "，共找到" + showInfo,Toast.LENGTH_LONG).show();
        //展示数据
        listDataAdapter.getList().addAll(result);
        listDataAdapter.notifyDataSetChanged();
        //添加数据到数据库
        commodityDataDao.insertOrReplaceInTx(result);
    }
    @Override
    public void onViewFail(DataBean dataBean) {
        //提示
        Toast.makeText(SearchActivity.this,"错误提示：" + dataBean.getMessage(),Toast.LENGTH_LONG).show();
    }
}
