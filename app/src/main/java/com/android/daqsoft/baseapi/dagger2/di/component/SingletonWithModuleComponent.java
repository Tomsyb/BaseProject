package com.android.daqsoft.baseapi.dagger2.di.component;

import com.android.daqsoft.baseapi.dagger2.di.module.SingletonWithModuleModule;
import com.android.daqsoft.baseapi.dagger2.entity.SingletonWithModule;
import com.android.daqsoft.baseapi.dagger2.ui.SingletonWithModuleActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yanbo on 2018/1/9.
 */
@Singleton
@Component(modules = SingletonWithModuleModule.class)
public interface SingletonWithModuleComponent {
    void  inject (SingletonWithModuleActivity activity);
}
