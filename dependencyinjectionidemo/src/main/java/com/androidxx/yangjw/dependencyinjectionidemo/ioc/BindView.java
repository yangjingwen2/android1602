package com.androidxx.yangjw.dependencyinjectionidemo.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjw on 2016/5/30.
 * 元注解
 * Target：决定注解的类型，用于注解属性或者方法或者类等等
 * Retention：决定注解的生命周期：源码阶段、class阶段、runtime阶段
 *
 * BindView：表示是用于注解属性，并且在运行时有效。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    int value();
}
