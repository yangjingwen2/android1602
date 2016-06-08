package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {

    public static void main(String[] args) {
        //创建Schema对象
        Schema schema = new Schema(1,"com.androidxx.cart.orm");
        //表示我想创建一个Product类
        Entity product = schema.addEntity("ShoppingCard");
        product.addIdProperty().primaryKey().autoincrement();
        product.addStringProperty("productName");
        product.addFloatProperty("productPrice");
        product.addIntProperty("productNum");
        product.addLongProperty("productId");

        try {
            new DaoGenerator().generateAll(schema,"../TopLevel1602/shoppingcartdemo/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
