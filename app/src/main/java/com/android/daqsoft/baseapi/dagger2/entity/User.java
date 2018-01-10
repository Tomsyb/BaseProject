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

    //标注在构造函数上使用的时候自动寻找到这里初始化不需module提供依赖，但是如果有参数传入就需要module提供了
    @Inject
    public User() {
    }

}
