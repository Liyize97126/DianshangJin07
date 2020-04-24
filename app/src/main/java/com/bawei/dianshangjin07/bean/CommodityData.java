package com.bawei.dianshangjin07.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 搜索结果数据展示
 */
@Entity
public class CommodityData {
    @Id
    private long commodityId;
    private String commodityName;
    private String masterPic;
    private double price;
    private int saleNum;
    @Generated(hash = 2086659007)
    public CommodityData(long commodityId, String commodityName, String masterPic,
            double price, int saleNum) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.masterPic = masterPic;
        this.price = price;
        this.saleNum = saleNum;
    }
    @Generated(hash = 1223625643)
    public CommodityData() {
    }
    public long getCommodityId() {
        return commodityId;
    }
    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
        return commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getMasterPic() {
        return masterPic;
    }
    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getSaleNum() {
        return saleNum;
    }
    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
}
