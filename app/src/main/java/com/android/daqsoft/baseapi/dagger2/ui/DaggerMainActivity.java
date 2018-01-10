package com.android.daqsoft.baseapi.dagger2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.android.daqsoft.adapter.recyclerview.CommonAdapter;
import com.android.daqsoft.adapter.recyclerview.base.ViewHolder;
import com.android.daqsoft.baseapi.dagger2.ui.scope.ScopeActivity;
import com.android.daqsoft.baseproject.R;
import com.android.daqsoft.yutils.YActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * dagger2首页
 */
public class DaggerMainActivity extends AppCompatActivity {

    @BindView(R.id.dagger_main_rv)
    RecyclerView mRv;

    List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_main);
        ButterKnife.bind(this);
        initDatas();
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(new CommonAdapter<String>(this,R.layout.item_common_text,mDatas) {

            @Override
            protected void convert(ViewHolder holder, String s, final int position) {
                holder.setText(R.id.item_btn,s);
                holder.setOnClickListener(R.id.item_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (position){
                            case 0://Dagger2基本使用
                                YActivityUtils.skipActivity(DaggerMainActivity.this,BaseUseActivity.class);
                                break;
                            case 1://Singleton单列模式
                                YActivityUtils.skipActivity(DaggerMainActivity.this,SingletonUseActivity.class);
                                break;
                            case 2://Singleton单列模式Module提供依赖
                                YActivityUtils.skipActivity(DaggerMainActivity.this,SingletonWithModuleActivity.class);
                                break;
                            case 3://Singleton单列模式Module提供依赖
                                YActivityUtils.skipActivity(DaggerMainActivity.this,ScopeActivity.class);
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.dagger2mainlist);
        for (int i = 0; i < stringArray.length; i++) {
            mDatas.add(stringArray[i]);
        }
    }
}
