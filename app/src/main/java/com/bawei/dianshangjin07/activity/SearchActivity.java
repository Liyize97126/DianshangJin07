package com.bawei.dianshangjin07.activity;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshangjin07.R;
import com.bawei.dianshangjin07.adapter.ListDataAdapter;
import com.bawei.dianshangjin07.base.BaseActivity;
import com.bawei.dianshangjin07.base.BasePresenter;
import com.bawei.dianshangjin07.bean.CommodityData;
import com.bawei.dianshangjin07.bean.DataBean;
import com.bawei.dianshangjin07.contact.IContact;
import com.bawei.dianshangjin07.presenter.FindCommodityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索页面
 */
public class SearchActivity extends BaseActivity {
    //定义
    @BindView(R.id.edit_search_text)
    protected EditText editSearchText;
    @BindView(R.id.search_result_show)
    protected RecyclerView searchResultShow;
    @BindView(R.id.search_data_show)
    protected TextView searchDataShow;
    private ListDataAdapter listDataAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }
    @Override
    protected BasePresenter initPresenter() {
        return new FindCommodityPresenter(new IContact.IView() {
            @Override
            public void onViewSuccess(Object result, String message) {
                List<CommodityData> listData = (List<CommodityData>) result;
                String showInfo = listData.size() + "件商品";
                searchDataShow.setText("搜索结果：" + showInfo);
                //提示
                Toast.makeText(SearchActivity.this,message + "，共找到" + showInfo,Toast.LENGTH_LONG).show();
                //展示数据
                listDataAdapter.getList().addAll(listData);
                listDataAdapter.notifyDataSetChanged();
            }
            @Override
            public void onViewFail(DataBean dataBean) {
                //提示
                Toast.makeText(SearchActivity.this,"错误提示：" + dataBean.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void initView() {
        //设置适配器
        searchResultShow.setLayoutManager(new GridLayoutManager(this,2));
        listDataAdapter = new ListDataAdapter();
        searchResultShow.setAdapter(listDataAdapter);
    }
    @OnClick(R.id.submit_search)
    protected void search(){
        //获取文本并转化
        String searchText = editSearchText.getText().toString();
        if(TextUtils.isEmpty(searchText)){
            //提示
            Toast.makeText(SearchActivity.this,"错误提示：搜索框不能为空！",Toast.LENGTH_LONG).show();
        } else {
            listDataAdapter.getList().clear();
            //请求
            getBasePresenter().request(searchText,1,100);
        }
    }
    @Override
    protected void initDestroy() {
        if(listDataAdapter != null){
            listDataAdapter.getList().clear();
            listDataAdapter.destroy();
            listDataAdapter = null;
        }
    }
}
