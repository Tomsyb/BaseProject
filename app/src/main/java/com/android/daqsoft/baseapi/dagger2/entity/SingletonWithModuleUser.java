package com.android.daqsoft.baseapi.dagger2.entity;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by yanbo on 2018/1/9.
 *由Module提供依赖这里不需任何注解
 */
public class SingletonWithModuleUser {
    private String name;

    public SingletonWithModuleUser() {
    }
}
