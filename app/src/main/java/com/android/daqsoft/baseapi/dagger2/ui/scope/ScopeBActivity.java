package com.android.daqsoft.baseapi.dagger2.ui.scope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.daqsoft.app.IApplication;
import com.android.daqsoft.baseapi.dagger2.entity.ScopeUser;
import com.android.daqsoft.baseproject.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScopeBActivity extends AppCompatActivity {
    @Inject
    ScopeUser mUser1;
    @Inject
    ScopeUser mUser2;

    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_scopeb);
        ButterKnife.bind(this);
        ((IApplication) getApplication()).getScopeUseComponent().inject(this);
        setData();
    }
    private void setData() {
        mTv1.setText(mUser1.toString());
        mTv1.setText(mUser2.toString());
    }
}
