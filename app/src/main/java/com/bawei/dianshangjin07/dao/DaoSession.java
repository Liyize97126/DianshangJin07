package com.bawei.dianshangjin07.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.bawei.dianshangjin07.bean.CommodityData;

import com.bawei.dianshangjin07.dao.CommodityDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig commodityDataDaoConfig;

    private final CommodityDataDao commodityDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        commodityDataDaoConfig = daoConfigMap.get(CommodityDataDao.class).clone();
        commodityDataDaoConfig.initIdentityScope(type);

        commodityDataDao = new CommodityDataDao(commodityDataDaoConfig, this);

        registerDao(CommodityData.class, commodityDataDao);
    }
    
    public void clear() {
        commodityDataDaoConfig.clearIdentityScope();
    }

    public CommodityDataDao getCommodityDataDao() {
        return commodityDataDao;
    }

}