package com.android.daqsoft.ymvp.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yanbo on 2018/1/11.
 * 提供框架必须的实体
 */

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    /**
     * @return 单列提供 Application
     */
    @Singleton
    @Provides
    Application providesApplication(){
        return mApplication;
    }
}
