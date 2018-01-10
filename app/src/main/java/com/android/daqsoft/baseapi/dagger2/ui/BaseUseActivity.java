package com.android.daqsoft.baseapi.dagger2.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.android.daqsoft.baseapi.dagger2.di.component.DaggerBaseUseComponent;
import com.android.daqsoft.baseapi.dagger2.di.module.DaggerModule;
import com.android.daqsoft.baseapi.dagger2.ui.adapter.DaggerViewpageAdapter;
import com.android.daqsoft.baseproject.R;

import javax.inject.Inject;

public class BaseUseActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    //标记在变量上，不用实例化，在moudle文件中实例
    @Inject
    DaggerViewpageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        /**
         * DaggerDaggerComponent
         *
         */
        DaggerBaseUseComponent
                .builder()
                .daggerModule(new DaggerModule(this))
                .build()
                .inject(this);
        mViewPager = (ViewPager)findViewById(R.id.dagger_viewpager);
        mViewPager.setAdapter(mAdapter);
    }
}
