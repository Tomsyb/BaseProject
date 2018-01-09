package com.android.daqsoft.baseapi.dagger2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.android.daqsoft.baseproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * dagger2首页
 */
public class DaggerMainActivity extends AppCompatActivity {

    @BindView(R.id.dagger_main_rv)
    RecyclerView mRv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_main);
        ButterKnife.bind(this);

    }
}
