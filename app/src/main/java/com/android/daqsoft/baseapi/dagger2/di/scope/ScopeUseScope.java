package com.android.daqsoft.baseapi.dagger2.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by yanbo on 2018/1/10.
 * 定义局部作用域
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ScopeUseScope {
}
