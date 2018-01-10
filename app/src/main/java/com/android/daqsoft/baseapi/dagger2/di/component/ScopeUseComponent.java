package com.android.daqsoft.baseapi.dagger2.di.component;

import com.android.daqsoft.baseapi.dagger2.di.module.ScopeUseModule;
import com.android.daqsoft.baseapi.dagger2.di.scope.ScopeUseScope;
import com.android.daqsoft.baseapi.dagger2.ui.scope.ScopeAActivity;
import com.android.daqsoft.baseapi.dagger2.ui.scope.ScopeBActivity;

import dagger.Component;

/**
 * Created by yanbo on 2018/1/10.
 * 使用自定义 的局部单列@ScopeUseScope
 */
@ScopeUseScope
@Component(modules = ScopeUseModule.class)
public interface ScopeUseComponent {
    /**
     *注入器可以向下面两个Activity里注入依赖
     */
    void inject(ScopeAActivity activity);
    void inject(ScopeBActivity aActivity);
}
