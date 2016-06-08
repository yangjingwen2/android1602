package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {

    public static void main(String[] args) {
        /**
         * 参数一：数据库版本号
         * 参数二：包名（用来存放自动生成的Java类）
         */
        Schema schema = new Schema(1,"com.androidxx.yangjw.greendaoproject.orm");
        /**
         * 参数：是一个Java Bean的类名称，并且也是一个数据库的表的名称
         */
        Entity user = schema.addEntity("User");
        //创建一个ID作为主键
        user.addIdProperty().primaryKey().autoincrement();
        //添加一个字段或者属性名称为username
        user.addStringProperty("userName").notNull();
        user.addIntProperty("userAge");
        user.addStringProperty("userSex");

        try {
            /**
             * 参数一：Schema对象--封装了要创建的java对象和表对象
             * 参数二：
             */
            new DaoGenerator().generateAll(schema,"../TopLevel1602/greendaoproject/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
