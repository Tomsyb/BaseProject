package com.android.daqsoft.app;

import android.app.Application;

import com.android.daqsoft.baseapi.dagger2.di.component.DaggerScopeUseComponent;
import com.android.daqsoft.baseapi.dagger2.di.component.ScopeUseComponent;
import com.android.daqsoft.baseapi.dagger2.di.module.ScopeUseModule;


/**
 * Created by yanbo on 2018/1/10.
 */

public class IApplication extends Application {
    ScopeUseComponent mScopeUseComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mScopeUseComponent = DaggerScopeUseComponent
                .builder()
                .scopeUseModule(new ScopeUseModule())
                .build();
    }
    public ScopeUseComponent getScopeUseComponent(){
        return mScopeUseComponent;
    }
}
