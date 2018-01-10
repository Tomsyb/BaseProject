package com.android.daqsoft.baseapi.dagger2.ui.scope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.daqsoft.baseapi.dagger2.di.component.DaggerScopeUsecComponent;
import com.android.daqsoft.baseapi.dagger2.di.module.ScopeUseCModule;
import com.android.daqsoft.baseapi.dagger2.entity.ScopeUser;
import com.android.daqsoft.baseproject.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 这里我们不用由Iapplication提供的单列自己建一个
 */
public class ScopeCActivity extends AppCompatActivity {


    @BindView(R.id.tv_1)
    TextView mTv1;

    //使用类
    @Inject
    ScopeUser mUser1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_scopec);
        ButterKnife.bind(this);
        DaggerScopeUsecComponent
                .builder()
                .scopeUseCModule(new ScopeUseCModule())
                .build()
                .inject(this);
        mTv1.setText(mUser1.toString());
    }

}
