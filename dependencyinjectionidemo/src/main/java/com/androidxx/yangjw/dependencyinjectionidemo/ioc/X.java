package com.androidxx.yangjw.dependencyinjectionidemo.ioc;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.androidxx.yangjw.dependencyinjectionidemo.R;

import java.lang.reflect.Field;

/**
 * Created by yangjw on 2016/5/30.
 */
public class X {

    private static final String TAG = "androidxx";

//    public  static void bind(String packagename) {
//        //反射
//        try {
//            Class<?> aClass = Class.forName(packagename);
//            Log.d(TAG, "bind: " + aClass.getName());
//            try {
//                MainActivity mainActivity = (MainActivity) aClass.newInstance();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static void bind(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        try {
            for (int i = 0; i < declaredFields.length; i++) {
                Log.d(TAG, "bind: " + declaredFields[i]);
                /**
                 * 参数一：指定一个对象（当前属性属于哪一个对象）
                 * 参数2：属性的值
                 */
               BindView annotation = declaredFields[i].getAnnotation(BindView.class);
                //如果不等于Null表示此属性有@BindView注解
                if (annotation != null) {
                    int id = annotation.value();
                    View view = activity.findViewById(id);
                    declaredFields[i].set(activity,view);
                }


            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
