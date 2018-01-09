package com.android.daqsoft.baseapi.dagger2.di.component;

import com.android.daqsoft.baseapi.dagger2.ui.SingletonUseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yanbo on 2018/1/9.
 */
@Singleton
@Component
public interface SingletonUseComponent {
    void inject(SingletonUseActivity activity);
}
