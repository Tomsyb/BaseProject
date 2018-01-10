package com.android.daqsoft.baseapi.dagger2.di.module;

import com.android.daqsoft.baseapi.dagger2.di.scope.ScopeUseScope;
import com.android.daqsoft.baseapi.dagger2.entity.ScopeUser;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yanbo on 2018/1/10.
 * 使用自定义的局部作用域@ScopeUseScope
 */
@Module
public class ScopeUseModule {
    @Provides
    @ScopeUseScope
    ScopeUser providesScopeUser(){
        return new ScopeUser();
    }
}
