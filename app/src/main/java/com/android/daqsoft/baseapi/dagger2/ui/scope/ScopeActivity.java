package com.android.daqsoft.baseapi.dagger2.ui.scope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.daqsoft.baseproject.R;
import com.android.daqsoft.yutils.YActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Scope局部单列模式
 * 场景：
 * A和B页面共享一个单列，C页面享另一个单列
 */
public class ScopeActivity extends AppCompatActivity {

    @BindView(R.id.btn_A)
    Button mBtnA;
    @BindView(R.id.btn_B)
    Button mBtnB;
    @BindView(R.id.btn_C)
    Button mBtnC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_scope);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_A, R.id.btn_B, R.id.btn_C})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_A:
                YActivityUtils.skipActivity(this,ScopeAActivity.class);
                break;
            case R.id.btn_B:
                YActivityUtils.skipActivity(this,ScopeBActivity.class);
                break;
            case R.id.btn_C:
                YActivityUtils.skipActivity(this,ScopeCActivity.class);
                break;
        }
    }
}
