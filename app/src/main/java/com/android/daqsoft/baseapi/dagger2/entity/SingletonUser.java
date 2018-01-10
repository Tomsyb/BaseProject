package com.android.daqsoft.baseapi.dagger2.entity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yanbo on 2018/1/9.
 * @Singleton 表示单列
 */
@Singleton
public class SingletonUser {
    private String name;

    @Inject
    public SingletonUser() {
    }
}
