package com.androidxx.cart.orm;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.androidxx.cart.orm.ShoppingCard;

import com.androidxx.cart.orm.ShoppingCardDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig shoppingCardDaoConfig;

    private final ShoppingCardDao shoppingCardDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        shoppingCardDaoConfig = daoConfigMap.get(ShoppingCardDao.class).clone();
        shoppingCardDaoConfig.initIdentityScope(type);

        shoppingCardDao = new ShoppingCardDao(shoppingCardDaoConfig, this);

        registerDao(ShoppingCard.class, shoppingCardDao);
    }
    
    public void clear() {
        shoppingCardDaoConfig.getIdentityScope().clear();
    }

    public ShoppingCardDao getShoppingCardDao() {
        return shoppingCardDao;
    }

}