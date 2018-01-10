package com.android.daqsoft.baseapi.dagger2.di.module;

import com.android.daqsoft.baseapi.dagger2.entity.SingletonWithModuleUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yanbo on 2018/1/9.
 */
@Module
public class SingletonWithModuleModule {

    /**
     * 单列注解  @Singleton
     */
    @Provides
    @Singleton
    SingletonWithModuleUser providesSingleton(){
        return new SingletonWithModuleUser();
    }
}
