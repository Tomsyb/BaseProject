package com.android.daqsoft.ymvp.mvp;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;

import com.android.daqsoft.ymvp.utils.Preconditions;

/**
 * Created by yanbo on 2018/1/18.
 *
 *
 * 基类presenter
 */

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter,LifecycleObserver{

    protected M mModel;
    protected V mRootView;

    //-----------------------------------------------------------------------------------构造方法-------------
    /**
     * 需要View和数据层
     * @param model
     * @param rootView
     */
    public BasePresenter(M model, V rootView) {
        //检验是否为空
        Preconditions.checkNotNull(model,"%s cannot be null",IModel.class.getName());
        Preconditions.checkNotNull(rootView,"%s cannot be null",IView.class.getName());
        this.mModel = model;
        this.mRootView = rootView;
        onStart();
    }

    /**
     * 只要view
     * @param rootView
     */
    public BasePresenter(V rootView) {
        Preconditions.checkNotNull(rootView,"%s cannot be null",IView.class.getName());
        this.mRootView = rootView;
        onStart();
    }

    /**
     * 单纯的
     */
    public BasePresenter() {
        onStart();
    }

    /**
     * 初识化操作
     */
    @Override
    public void onStart() {
        if (mRootView !=null && mRootView instanceof LifecycleOwner){
            ((LifecycleOwner)mRootView).getLifecycle().addObserver(this);
        }
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroy() {

    }
}
