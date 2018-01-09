package com.android.daqsoft.baseapi.dagger2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.android.daqsoft.baseapi.dagger2.di.component.DaggerSingletonWithModuleComponent;
import com.android.daqsoft.baseapi.dagger2.di.module.SingletonWithModuleModule;
import com.android.daqsoft.baseapi.dagger2.entity.SingletonWithModule;
import com.android.daqsoft.baseproject.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 单列由module提供
 * 在实体类中不需要任何注解(属于inject的第二种方法)
 */
public class SingletonWithModuleActivity extends AppCompatActivity {

    @BindView(R.id.btn_module_1)
    Button mBtnModule1;
    @BindView(R.id.tv_module_1)
    TextView mTvModule1;
    @BindView(R.id.tv_module_2)
    TextView mTvModule2;

    @Inject
    SingletonWithModule mUser1;
    @Inject
    SingletonWithModule mUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_singletonwithmodule);
        ButterKnife.bind(this);

        DaggerSingletonWithModuleComponent
                .builder()
                .singletonWithModuleModule(new SingletonWithModuleModule())
                .build()
                .inject(this);

    }

    @OnClick(R.id.btn_module_1)
    public void onClick() {

    }
}
