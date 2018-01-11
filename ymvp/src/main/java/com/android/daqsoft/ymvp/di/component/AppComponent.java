package com.android.daqsoft.ymvp.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yanbo on 2018/1/10.
 * dagger2之app桥梁
 */
@Singleton
@Component
public interface AppComponent {
    Application mApplication();

}
