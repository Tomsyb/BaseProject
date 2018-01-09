package com.android.daqsoft.baseapi.dagger2.di.component;

import com.android.daqsoft.baseapi.dagger2.di.module.DaggerModule;
import com.android.daqsoft.baseapi.dagger2.ui.BaseUseActivity;

import dagger.Component;

/**
 * Created by yanbo on 2018/1/8.
 * modules参数代表我们要注入的@Module类，可以是多个
 */
@Component(modules = DaggerModule.class)
public interface BaseUseComponent {
    void inject(BaseUseActivity activity);//参数类寻找@Inject注解进行注入
}
