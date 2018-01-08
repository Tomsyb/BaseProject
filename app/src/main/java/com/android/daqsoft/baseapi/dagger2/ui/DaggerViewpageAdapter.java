package com.android.daqsoft.baseapi.dagger2.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yanbo on 2018/1/8.
 *
 */

public class DaggerViewpageAdapter extends PagerAdapter {
    private List<String> mDatas;
    private Context mContext;

    /**
     * @Inject 注解
     * @param datas
     * @param context
     */
    @Inject
    public DaggerViewpageAdapter(List<String> datas, Context context) {
        this.mDatas = datas;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(mContext).load(mDatas.get(position)).into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
