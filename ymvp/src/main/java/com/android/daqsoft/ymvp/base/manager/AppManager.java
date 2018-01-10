package com.android.daqsoft.ymvp.base.manager;

import android.app.Application;
import android.os.Message;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

/**
 * Created by yanbo on 2018/1/10.
 * 用于管理所有Activity类
 * 也可通过post(Message) 远程遥控执行对应的方法，用法和EventBus类似
 *
 */

public class AppManager {
    private Application mApplication;
    /**
     * Evenbus TAG
     */
    public static final String APPMANAGER_MESSAGE = "appmanager_message";
    /**
     * 处理页面不同的消息标示
     */
    public static final int START_ACTIVITY = 5000;
    public static final int KILLALL_ACTIVITY = 5001;
    public static final int EXIT_APP = 5002;

    /**
     * 构造方法
     *  获取application来操作页面的添加与跳转
     *  远程操控
     */
    public AppManager(Application application) {
        this.mApplication = application;
        EventBus.getDefault().register(this);//注册
    }
    /**
     * 根据不同的message来操作不同的页面处理方法
     */
    @Subscriber(tag = APPMANAGER_MESSAGE,mode = ThreadMode.MAIN)
    public void onReceive(Message msg){
        switch (msg.what){
            case START_ACTIVITY:

                break;
            case KILLALL_ACTIVITY:
                break;
            case EXIT_APP:
                break;
            default:
                break;
        }
    }
}
