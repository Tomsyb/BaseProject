package com.android.daqsoft.ymvp.di.component;

import android.app.Application;
import android.content.Context;

/**
 * Created by yanbo on 2018/1/10.
 * dagger2之app桥梁
 * 可通过 {@link ArmsUtils#obtainAppComponentFromContext(Context)} 拿到此接口的实现类
 * 拥有此接口的实现类即可调用对应的方法拿到 Dagger 提供的对应实例
 */

public interface AppComponent {
    //Application mApplication;

}
