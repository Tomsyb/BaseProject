package com.android.daqsoft.baseapi.dagger2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.daqsoft.adapter.recyclerview.CommonAdapter;
import com.android.daqsoft.adapter.recyclerview.base.ViewHolder;
import com.android.daqsoft.baseproject.R;

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
        mRv.setAdapter(new CommonAdapter<String>(this,R.layout.item_common_text,mDatas) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.item_tv,s);
                holder.setOnClickListener(R.id.item_ll, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

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
