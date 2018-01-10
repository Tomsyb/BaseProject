package com.android.daqsoft.baseapi.dagger2.di.component;

import com.android.daqsoft.baseapi.dagger2.di.module.ScopeUseCModule;
import com.android.daqsoft.baseapi.dagger2.ui.scope.ScopeCActivity;

import dagger.Component;

/**
 * Created by yanbo on 2018/1/10.
 */
@Component(modules = ScopeUseCModule.class)
public interface ScopeUsecComponent {
    void inject(ScopeCActivity activity);
}
