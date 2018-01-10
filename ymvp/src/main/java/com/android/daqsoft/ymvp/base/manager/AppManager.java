package com.android.daqsoft.ymvp.base.manager;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Message;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Singleton;

/**
 * Created by yanbo on 2018/1/10.
 * 用于管理所有Activity类
 * 也可通过post(Message) 远程遥控执行对应的方法，用法和EventBus类似
 * API:
 *      appExit()        退出程序
 *      getActivityList()获取所有存活的Activity
 *      killAll()        杀掉所有Activity 通过Iterator实现
 *      KillAllStillOne()杀死所有Activity保留一个
 *
 *
 *
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
     * 管理所有的Activity,容器中的Activity是Activity的创建顺序，不能保证和Activity的任务栈一致
     */
    public List<Activity> mActivityList;

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
                dispathStart(msg);
                break;
            case KILLALL_ACTIVITY:
                break;
            case EXIT_APP:
                break;
            default:
                break;
        }
    }

    /**
     * @param msg 根据类型判断跳转
     */
    private void dispathStart(Message msg) {
        if (msg.obj instanceof Intent);

    }

    /**
     * 让栈顶的Activity打开指定的Activity
     * @param intent
     */
    public void startActivity(Intent intent){

    }
    /**
     * 获取所有存活的activity
     */
    public List<Activity> getActivityList(){
        if (mActivityList == null){
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }
    /**
     * 杀掉所有Activity
     */
    public void killAll(){
//        while (getActivityList().size() != 0) { //此方法只能兼容LinkedList
//            getActivityList().remove(0).finish();
//        }
        synchronized (AppManager.class){
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()){
                Activity next = iterator.next();
                iterator.remove();
                next.finish();
            }
        }
    }
    /**
     * 杀死所有Activity保留一个
     */
    public void killAllStillOne(Class<?>... excludeActivityClasses){
        List<Class<?>> encludeList = Arrays.asList(excludeActivityClasses);
        synchronized (AppManager.class){
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()){
                Activity next = iterator.next();
                if (encludeList.contains(next.getClass()))
                    continue;
                iterator.remove();
                next.finish();

            }
        }
    }

    /**
     * 退出程序
     */
    public void appExit(){
        try {
            //killAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 接口
     */
    public interface HandleListener{
        void handleMessage(AppManager appManager,Message message);
    }

}
