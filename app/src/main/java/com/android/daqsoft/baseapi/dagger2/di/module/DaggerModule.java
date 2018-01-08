package com.android.daqsoft.baseapi.dagger2.di.module;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yanbo on 2018/1/8.
 * 提供依赖
 */
@Module
public class DaggerModule {
    //需向适配器提供一些依赖
    private Context mContext;

    public DaggerModule(Context context) {
        this.mContext = context;
    }

    /**
     * 提供context
     * @return
     */
    @Provides
    Context providesContext(){
        return mContext;
    }

    @Provides
    List<String> providesImagesList(){
        List<String> list = new ArrayList<>();
        list.add("http://img4.cache.netease.com/photo/0001/2016-08-13/900x600_BUBDM7GI00AO0001.jpg");
        list.add("http://img4.cache.netease.com/photo/0001/2016-08-13/900x600_BUBDM7JI00AO0001.jpg");
        list.add("http://img3.cache.netease.com/photo/0001/2016-08-13/900x600_BUBDM85900AO0001.jpg");
        list.add("http://img3.cache.netease.com/photo/0001/2016-08-13/900x600_BUBDM8F500AO0001.jpg");
        list.add("http://i0.sinaimg.cn/dy/slidenews/76_img/2016_32/76522_1882718_992616.jpg");
        return list;
    }
}
