package com.android.daqsoft.baseapi.dagger2.entity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yanbo on 2018/1/9.
 * 用户实体类测试
 */

public class User {
    private String name;
    private int age;

    @Inject
    public User() {
    }

}
