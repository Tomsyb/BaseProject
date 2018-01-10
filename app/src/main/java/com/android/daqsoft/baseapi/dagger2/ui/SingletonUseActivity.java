package com.android.daqsoft.baseapi.dagger2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.daqsoft.baseapi.dagger2.di.component.DaggerSingletonUseComponent;
import com.android.daqsoft.baseapi.dagger2.entity.SingletonUser;
import com.android.daqsoft.baseapi.dagger2.entity.User;
import com.android.daqsoft.baseproject.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Dagger2之@Singleton用法
 * 单列
 * user构造方法没有参数需要传入就不用module
 */

public class SingletonUseActivity extends AppCompatActivity {
    @BindView(R.id.btn_1)
    Button mBtn1;
    @BindView(R.id.btn_2)
    Button mBtn2;
    @BindView(R.id.btn_3)
    Button mBtn3;
    @BindView(R.id.btn_4)
    Button mBtn4;
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;

    /**
     * 不使用module情况
     */
    @Inject
    User mUser;
    @Inject
    User mUser2;

    @Inject
    SingletonUser mSingletonUser1;
    @Inject
    SingletonUser mSingletonUser2;

    /**
     *使用module提供依赖
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_scopeuse);
        ButterKnife.bind(this);
        DaggerSingletonUseComponent
                .builder()
                .build()
                .inject(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1://创建两个对象
                method1();
                break;
            case R.id.btn_2://单列模式
                method2();
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
        }
    }


    /**
     * 演示两个不同的user,引用地址不一样(初始化了两个对象)
     */
    public void method1() {
        mTv1.setText(mUser.toString());
        mTv2.setText(mUser2.toString());
    }

    /**
     * 单列模式注解@Singleton运用（构造函数无参数传递情况无需modlue提供依赖情况）
     * 在实体类和component头部加@Singleton
     */
    public void method2(){
        mTv1.setText(mSingletonUser1.toString());
        mTv2.setText(mSingletonUser2.toString());
    }
}
