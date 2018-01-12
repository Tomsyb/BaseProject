package com.android.daqsoft.ymvp.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    public Application providesApplication(){
        return mApplication;
    }
    @Singleton
    @Provides
    public Gson providesGson(Application application, @Nullable GsonConfig config){
        GsonBuilder builder = new GsonBuilder();
        if (builder !=null)
            config.configGson(application,builder);
        return builder.create();
    }

    interface GsonConfig{
        void configGson(Context context, GsonBuilder builder);
    }

}
