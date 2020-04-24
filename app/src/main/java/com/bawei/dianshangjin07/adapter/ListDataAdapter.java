package com.bawei.dianshangjin07.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshangjin07.R;
import com.bawei.dianshangjin07.bean.CommodityData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 列表适配器
 */
public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.MyViewHouler> {
    //定义
    private Unbinder bind;
    private List<CommodityData> list = new ArrayList<>();
    public List<CommodityData> getList() {
        return list;
    }
    //方法实现
    @NonNull
    @Override
    public ListDataAdapter.MyViewHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content, parent, false);
        return new MyViewHouler(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ListDataAdapter.MyViewHouler holder, int position) {
        //获取数据
        CommodityData commodityData = list.get(position);
        Glide.with(holder.masterPic.getContext())
                .load(commodityData.getMasterPic())
                .into(holder.masterPic);
        holder.commodityName.setText(commodityData.getCommodityName());
        holder.price.setText("￥" + commodityData.getPrice());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    //寄存器
    public class MyViewHouler extends RecyclerView.ViewHolder {
        @BindView(R.id.masterPic)
        protected ImageView masterPic;
        @BindView(R.id.commodityName)
        protected TextView commodityName;
        @BindView(R.id.price)
        protected TextView price;
        public MyViewHouler(@NonNull View itemView) {
            super(itemView);
            bind = ButterKnife.bind(this,itemView);
        }
    }
    //资源释放
    public void destroy(){
        if(bind != null){
            bind.unbind();
        }
    }
}
