package com.androidxx.yangjw.shoppingcartdemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.androidxx.cart.orm.DaoMaster;
import com.androidxx.cart.orm.DaoSession;

/**
 * Created by yangjw on 2016/6/8.
 */
public class DaoUtils {
    static DaoSession daoSession ;
    public static DaoSession getSession(Context context) {
        if (daoSession == null) {
            DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(context,"liwushuo",null);
            SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
            DaoMaster daoMaster = new DaoMaster(readableDatabase);
            daoSession = daoMaster.newSession();
        }

        return daoSession;
    }

}
