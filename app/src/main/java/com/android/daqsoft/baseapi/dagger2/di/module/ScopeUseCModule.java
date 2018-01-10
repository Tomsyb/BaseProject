package com.android.daqsoft.baseapi.dagger2.di.module;

import com.android.daqsoft.baseapi.dagger2.entity.ScopeUser;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yanbo on 2018/1/10.
 */
@Module
public class ScopeUseCModule {
    @Provides
    ScopeUser providesScopeUser(){
        return new ScopeUser();
    }
}
